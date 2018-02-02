package com.example.trubin23.tasks_mvp.tasks;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.data.source.TasksRepository;

/**
 * Created by Andrey on 30.01.2018.
 */

public class TasksPresenter implements TasksContract.Presenter {

    private TasksRepository mTasksRepository;

    private TasksContract.View mTasksView;

    TasksPresenter(@NonNull TasksRepository tasksRepository, @NonNull TasksContract.View tasksView) {
        mTasksRepository = tasksRepository;
        mTasksView = tasksView;

        mTasksView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
