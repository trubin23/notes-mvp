package com.example.trubin23.tasks_mvp.tasks;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.addedittask.AddEditTaskActivity;
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

    private boolean mFirstLoad = true;

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
        if (!mFirstLoad) {
            mTasksRepository.refreshTasks();
            mFirstLoad = true;
        }

        mTasksView.setLoadingIndicator(true);

        mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                mTasksView.setLoadingIndicator(false);
                mTasksView.showTasks(tasks);
            }

            @Override
            public void onDataNotAvailable() {
                mTasksView.setLoadingIndicator(false);
            }
        });
    }

    @Override
    public void activityResult(int requestCode, int resultCode) {
        if (AddEditTaskActivity.REQUEST_ADD_TASK == requestCode
                && Activity.RESULT_OK == resultCode) {
            mTasksView.showSuccessfullySavedMessage();
        }
    }
}
