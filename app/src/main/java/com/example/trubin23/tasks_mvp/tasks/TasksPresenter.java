package com.example.trubin23.tasks_mvp.tasks;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.data.Task;
import com.example.trubin23.tasks_mvp.data.source.TasksDataSource;
import com.example.trubin23.tasks_mvp.data.source.TasksRepository;

import java.util.List;

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
        loadTasks();
    }

    private void loadTasks() {
        mTasksView.setLoadingIndicator(true);

        mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                mTasksView.setLoadingIndicator(false);
                mTasksView.showTask(tasks);
            }

            @Override
            public void onDataNotAvailable() {

                mTasksView.setLoadingIndicator(false);
            }
        });
    }
}
