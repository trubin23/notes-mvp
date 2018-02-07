package com.example.trubin23.tasks_mvp.data.source.local;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.data.Task;
import com.example.trubin23.tasks_mvp.data.source.TasksDataSource;

import java.util.List;

/**
 * Created by Andrey on 01.02.2018.
 */

public class TasksLocalDataSource implements TasksDataSource {

    private static volatile TasksLocalDataSource INSTANCE;

    private TasksDao mTasksDao;

    private TasksLocalDataSource(@NonNull TasksDao tasksDao) {
        mTasksDao = tasksDao;
    }

    @NonNull
    public static TasksLocalDataSource getInstance(@NonNull TasksDao tasksDao) {
        if (INSTANCE == null) {
            synchronized (TasksLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TasksLocalDataSource(tasksDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {
        List<Task> tasks = mTasksDao.getTasks();
        if (tasks.isEmpty()){
            callback.onDataNotAvailable();
        } else {
            callback.onTasksLoaded(tasks);
        }
    }

    @Override
    public void getTask(@NonNull String id, @NonNull GetTaskCallback callback) {
        Task task = mTasksDao.getTaskById(id);
        if (task == null){
            callback.onDataNotAvailable();
        } else {
            callback.onTaskLoaded(task);
        }
    }

    @Override
    public void saveTask(@NonNull Task task) {
        mTasksDao.insertTask(task);
    }

    @Override
    public void updateTask(@NonNull Task task) {
        mTasksDao.updateTask(task);
    }
}
