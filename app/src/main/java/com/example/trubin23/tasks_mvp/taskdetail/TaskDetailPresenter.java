package com.example.trubin23.tasks_mvp.taskdetail;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.trubin23.tasks_mvp.data.source.TasksRepository;

/**
 * Created by Andrey on 04.02.2018.
 */

public class TaskDetailPresenter implements TaskDetailContract.Presenter {

    private TasksRepository mTasksRepository;

    private TaskDetailContract.View mTaskDetailView;

    public TaskDetailPresenter(@Nullable String taskId,
                               @NonNull TasksRepository tasksRepository,
                               @NonNull TaskDetailContract.View taskDetailView) {
        mTasksRepository = tasksRepository;
        mTaskDetailView = taskDetailView;

        mTaskDetailView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
