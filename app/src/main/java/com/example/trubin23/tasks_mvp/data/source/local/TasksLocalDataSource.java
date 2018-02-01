package com.example.trubin23.tasks_mvp.data.source.local;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.data.Task;
import com.example.trubin23.tasks_mvp.data.source.TasksDataSource;
import com.example.trubin23.tasks_mvp.util.AppExecutors;

/**
 * Created by Andrey on 01.02.2018.
 */

public class TasksLocalDataSource implements TasksDataSource {

    private static volatile TasksLocalDataSource INSTANCE;

    private AppExecutors mAppExecutors;

    private TasksDao mTasksDao;

    private TasksLocalDataSource(@NonNull AppExecutors appExecutors, @NonNull TasksDao tasksDao) {
        mAppExecutors = appExecutors;
        mTasksDao = tasksDao;
    }

    @NonNull
    public static TasksLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                   @NonNull TasksDao tasksDao) {
        if (INSTANCE == null) {
            synchronized (TasksLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TasksLocalDataSource(appExecutors, tasksDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {

    }

    @Override
    public void getTask(@NonNull String id, @NonNull GetTaskCallback callback) {

    }

    @Override
    public void saveTask(@NonNull Task task) {

    }

    @Override
    public void updateTask(@NonNull Task task) {

    }
}
