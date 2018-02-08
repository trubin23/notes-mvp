package com.example.trubin23.tasks_mvp.data.source;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.data.Task;
import com.example.trubin23.tasks_mvp.util.AppExecutors;

import java.util.List;

/**
 * Created by Andrey on 31.01.2018.
 */

public class TasksRepository implements TasksDataSource {

    private static TasksRepository INSTANCE;

    private AppExecutors mAppExecutors;

    private final TasksDataSource mTasksLocalDataSource;

    private TasksRepository(@NonNull AppExecutors appExecutors,
                            @NonNull TasksDataSource tasksLocalDataSource) {
        mAppExecutors = appExecutors;
        mTasksLocalDataSource = tasksLocalDataSource;
    }

    @NonNull
    public static TasksRepository getInstance(@NonNull AppExecutors appExecutors,
                                              @NonNull TasksDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(appExecutors, tasksLocalDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {
        Runnable runnable = () -> mTasksLocalDataSource.getTasks(new LoadTasksCallback() {
            @Override
            public void onTasksLoaded(final List<Task> tasks) {
                mAppExecutors.getMainThread().execute(() -> callback.onTasksLoaded(tasks));
            }

            @Override
            public void onDataNotAvailable() {
                mAppExecutors.getMainThread().execute(callback::onDataNotAvailable);
            }
        });

        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void getTask(@NonNull String id, @NonNull GetTaskCallback callback) {
        Runnable runnable = () -> mTasksLocalDataSource.getTask(id, new GetTaskCallback() {
            @Override
            public void onTaskLoaded(Task task) {
                mAppExecutors.getMainThread().execute(() -> callback.onTaskLoaded(task));
            }

            @Override
            public void onDataNotAvailable() {
                mAppExecutors.getMainThread().execute(callback::onDataNotAvailable);
            }
        });

        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void saveTask(@NonNull Task task) {
        mAppExecutors.getDiskIO().execute(() -> mTasksLocalDataSource.saveTask(task));
    }

    @Override
    public void updateTask(@NonNull Task task) {
        mAppExecutors.getDiskIO().execute(() -> mTasksLocalDataSource.updateTask(task));
    }

    @Override
    public void deleteTask(@NonNull String id) {
        mAppExecutors.getNetworkIO().execute(() -> mTasksLocalDataSource.deleteTask(id));
    }
}
