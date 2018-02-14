package com.example.trubin23.tasks_mvp.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.trubin23.tasks_mvp.data.Task;
import com.example.trubin23.tasks_mvp.data.source.local.TasksLocalDataSource;
import com.example.trubin23.tasks_mvp.util.AppExecutors;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey on 31.01.2018.
 */

public class TasksRepository implements TasksDataSource {

    private static TasksRepository INSTANCE;

    private AppExecutors mAppExecutors;

    private final TasksDataSource mTasksRemoteDataSource;

    private final TasksLocalDataSource mTasksLocalDataSource;

    private Map<String, Task> mCachedTasks;

    private boolean mCacheIsDirty = false;

    private TasksRepository(@NonNull AppExecutors appExecutors,
                            @NonNull TasksDataSource tasksRemoteDataSource,
                            @NonNull TasksLocalDataSource tasksLocalDataSource) {
        mAppExecutors = appExecutors;
        mTasksRemoteDataSource = tasksRemoteDataSource;
        mTasksLocalDataSource = tasksLocalDataSource;
    }

    @NonNull
    public static TasksRepository getInstance(@NonNull AppExecutors appExecutors,
                                              @NonNull TasksDataSource tasksRemoteDataSource,
                                              @NonNull TasksLocalDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(appExecutors,
                    tasksRemoteDataSource, tasksLocalDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {
        if (mCachedTasks != null && !mCacheIsDirty) {
            callback.onTasksLoaded(new ArrayList<>(mCachedTasks.values()));
            return;
        }

        if (mCacheIsDirty) {
            getTasksFromRemoteDateSource(callback);
        } else {
            Runnable runnable = () -> mTasksLocalDataSource.getTasks(new LoadTasksCallback() {
                @Override
                public void onTasksLoaded(final List<Task> tasks) {
                    refreshCache(tasks);
                    mAppExecutors.getMainThread().execute(() -> callback.onTasksLoaded(tasks));
                }

                @Override
                public void onDataNotAvailable() {
                    getTasksFromRemoteDateSource(callback);
                }
            });

            mAppExecutors.getDiskIO().execute(runnable);
        }
    }

    @Override
    public void getTask(@NonNull String id, @NonNull GetTaskCallback callback) {
        Task cachedTask = getTaskWithId(id);

        if (cachedTask != null) {
            callback.onTaskLoaded(cachedTask);
            return;
        }

        Runnable runnable = () -> mTasksLocalDataSource.getTask(id, new GetTaskCallback() {
            @Override
            public void onTaskLoaded(Task task) {
                mAppExecutors.getMainThread().execute(() -> callback.onTaskLoaded(task));
            }

            @Override
            public void onDataNotAvailable() {
                getTaskFromRemoteDateSource(id, callback);
            }
        });

        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void saveTask(@NonNull Task task) {
        mAppExecutors.getNetworkIO().execute(()-> mTasksRemoteDataSource.saveTask(task));
        mAppExecutors.getDiskIO().execute(() -> mTasksLocalDataSource.saveTask(task));

        if (mCachedTasks == null){
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.put(task.getId(), task);
    }

    @Override
    public void updateTask(@NonNull Task task) {
        mAppExecutors.getNetworkIO().execute(()-> mTasksRemoteDataSource.updateTask(task));
        mAppExecutors.getDiskIO().execute(() -> mTasksLocalDataSource.updateTask(task));

        if (mCachedTasks == null){
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.put(task.getId(), task);
    }

    @Override
    public void deleteTask(@NonNull String id) {
        mAppExecutors.getNetworkIO().execute(()-> mTasksRemoteDataSource.deleteTask(id));
        mAppExecutors.getDiskIO().execute(() -> mTasksLocalDataSource.deleteTask(id));

        if (mCachedTasks != null){
            mCachedTasks.remove(id);
        }
    }

    public void refreshTasks() {
        mCacheIsDirty = true;
    }

    private void getTasksFromRemoteDateSource(@NonNull LoadTasksCallback callback) {
        Runnable runnable = () -> mTasksRemoteDataSource.getTasks(new LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                refreshCache(tasks);
                refreshLocalDataSource(tasks);
                mAppExecutors.getMainThread().execute(() ->
                        callback.onTasksLoaded(new ArrayList<>(mCachedTasks.values())));
            }

            @Override
            public void onDataNotAvailable() {
                mAppExecutors.getMainThread().execute(callback::onDataNotAvailable);
            }
        });

        mAppExecutors.getNetworkIO().execute(runnable);
    }

    private void refreshCache(List<Task> tasks) {
        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.clear();
        for (Task task : tasks) {
            mCachedTasks.put(task.getId(), task);
        }
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(List<Task> refreshTasks) {
        Runnable runnable = () -> mTasksLocalDataSource.getTasks(new LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                mAppExecutors.getDiskIO().execute(() -> {
                    for (Task task : tasks) {
                        mTasksLocalDataSource.deleteTask(task.getId());
                    }
                    for (Task task : refreshTasks) {
                        mTasksLocalDataSource.saveTask(task);
                    }
                });
            }

            @Override
            public void onDataNotAvailable() {
            }
        });

        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Nullable
    private Task getTaskWithId(@NonNull String id) {
        if (mCachedTasks == null || mCachedTasks.isEmpty()) {
            return null;
        } else {
            return mCachedTasks.get(id);
        }
    }

    private void getTaskFromRemoteDateSource(@NonNull String id,
                                             @NonNull GetTaskCallback callback) {
        Runnable runnable = () -> mTasksRemoteDataSource.getTask(id, new GetTaskCallback() {
            @Override
            public void onTaskLoaded(Task task) {
                if (mCachedTasks == null) {
                    mCachedTasks = new LinkedHashMap<>();
                }
                mCachedTasks.put(task.getId(), task);

                mAppExecutors.getDiskIO().execute(() -> mTasksLocalDataSource.saveTask(task));

                mAppExecutors.getMainThread().execute(() -> callback.onTaskLoaded(task));
            }

            @Override
            public void onDataNotAvailable() {
                mAppExecutors.getMainThread().execute(callback::onDataNotAvailable);
            }
        });

        mAppExecutors.getNetworkIO().execute(runnable);
    }
}
