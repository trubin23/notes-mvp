package com.example.trubin23.tasks_mvp.data.source.local;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.data.Task;
import com.example.trubin23.tasks_mvp.data.source.TasksDataSource;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Andrey on 01.02.2018.
 */

public class TasksLocalRepository implements TasksLocalDataSource {

    private static volatile TasksLocalRepository INSTANCE;

    private final Executor mDiskIO;

    private TasksDao mTasksDao;

    private TasksLocalRepository(@NonNull TasksDao tasksDao,
                                 @NonNull Executor diskIO) {
        mTasksDao = tasksDao;
        mDiskIO = diskIO;
    }

    @NonNull
    public static TasksLocalRepository getInstance(@NonNull TasksDao tasksDao) {
        if (INSTANCE == null) {
            synchronized (TasksLocalRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TasksLocalRepository(tasksDao,
                            Executors.newSingleThreadExecutor());
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {
        mDiskIO.execute(() -> {
            List<Task> tasks = mTasksDao.getTasks();
            if (tasks.isEmpty()) {
                callback.onDataNotAvailable();
            } else {
                callback.onTasksLoaded(tasks);
            }
        });
    }

    @Override
    public void getTask(@NonNull String id, @NonNull GetTaskCallback callback) {
        mDiskIO.execute(() -> {
            Task task = mTasksDao.getTaskById(id);
            if (task == null) {
                callback.onDataNotAvailable();
            } else {
                callback.onTaskLoaded(task);
            }
        });
    }

    @Override
    public void saveTask(@NonNull Task task) {
        mDiskIO.execute(() -> mTasksDao.insertTask(task));
    }

    @Override
    public void updateTask(@NonNull Task task) {
        mDiskIO.execute(() -> mTasksDao.updateTask(task));
    }

    @Override
    public void deleteTask(@NonNull String id) {
        mDiskIO.execute(() -> mTasksDao.deleteTaskById(id));
    }

    @Override
    public void deleteAllTasks() {
        mDiskIO.execute(() -> mTasksDao.deleteTasks());
    }
}
