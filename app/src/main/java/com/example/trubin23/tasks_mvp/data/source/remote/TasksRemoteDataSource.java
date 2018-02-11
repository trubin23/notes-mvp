package com.example.trubin23.tasks_mvp.data.source.remote;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.data.Task;
import com.example.trubin23.tasks_mvp.data.source.TasksDataSource;

/**
 * Created by Andrey on 10.02.2018.
 */

public class TasksRemoteDataSource implements TasksDataSource {

    private static TasksRemoteDataSource INSTANCE;

    public static TasksRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (TasksRemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TasksRemoteDataSource();
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

    @Override
    public void deleteTask(@NonNull String id) {

    }
}
