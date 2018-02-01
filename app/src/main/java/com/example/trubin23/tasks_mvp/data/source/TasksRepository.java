package com.example.trubin23.tasks_mvp.data.source;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.data.Task;

/**
 * Created by Andrey on 31.01.2018.
 */

public class TasksRepository implements TasksDataSource {
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
