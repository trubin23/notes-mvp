package com.example.trubin23.tasks_mvp.addedittask;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.trubin23.tasks_mvp.data.source.TasksRepository;

/**
 * Created by Andrey on 04.02.2018.
 */

public class AddEditTaskPresenter implements AddEditTaskContract.Presenter {

    private TasksRepository mTasksRepository;

    private AddEditTaskContract.View mAddTaskView;

    private String mTaskId;

    AddEditTaskPresenter(@NonNull TasksRepository tasksRepository,
                         @NonNull AddEditTaskContract.View addTaskView,
                         @Nullable String taskId) {
        mTasksRepository = tasksRepository;
        mAddTaskView = addTaskView;
        mTaskId = taskId;
    }

    @Override
    public void start() {

    }
}