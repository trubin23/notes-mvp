package com.example.trubin23.tasks_mvp.data.source;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.data.Task;

import java.util.List;

/**
 * Created by Andrey on 31.01.2018.
 */

public interface TasksDataSource {

    interface LoadTasksCallback{

        void onTasksLoaded(@NonNull List<Task> tasks);

        void onDataNotAvailable();
    }

    interface GetTaskCallback{

        void onTaskLoaded(@NonNull Task task);

        void onDataNotAvailable();
    }

    void getTasks(@NonNull LoadTasksCallback callback);

    void getTask(@NonNull String id, @NonNull GetTaskCallback callback);

    void saveTask(@NonNull Task task);

    void updateTask(@NonNull Task task);

    void deleteTask(@NonNull String id);
}
