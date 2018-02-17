package com.example.trubin23.tasks_mvp.statistics;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.data.Task;
import com.example.trubin23.tasks_mvp.data.source.TasksDataSource;
import com.example.trubin23.tasks_mvp.data.source.TasksRepository;

import java.util.List;

/**
 * Created by Andrey on 16.02.2018.
 */

public class StatisticsPresenter implements StatisticsContract.Presenter {

    private TasksRepository mTasksRepository;

    private StatisticsContract.View mStatisticsView;

    StatisticsPresenter(@NonNull TasksRepository tasksRepository,
                        @NonNull StatisticsContract.View statisticsView) {
        mTasksRepository = tasksRepository;
        mStatisticsView = statisticsView;

        mStatisticsView.setPresenter(this);
    }

    @Override
    public void start() {
        loadStatistics();
    }

    private void loadStatistics() {
        mStatisticsView.showLoadingIndicator();

        mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                if (tasks.isEmpty()) {
                    mStatisticsView.showLoadingIndicatorError();
                    return;
                }

                Task mostOldTask = null, mostNewTask = null;
                for (Task task : tasks) {
                    Long currentTaskTime = task.dateOfCreationToLong();
                    if (currentTaskTime == null) {
                        continue;
                    }

                    if (mostOldTask == null) {
                        mostOldTask = task;
                    }
                    if (mostNewTask == null) {
                        mostNewTask = task;
                    }

                    Long oldTaskTime = mostOldTask.dateOfCreationToLong();
                    Long newTaskTime = mostNewTask.dateOfCreationToLong();

                    if (oldTaskTime != null && oldTaskTime > currentTaskTime) {
                        mostOldTask = task;
                    }
                    if (newTaskTime != null && newTaskTime < currentTaskTime) {
                        mostNewTask = task;
                    }
                }

                if (mostOldTask == null) {
                    mStatisticsView.showLoadingIndicatorError();
                } else {
                    mStatisticsView.showStatistics(mostOldTask.getDateOfCreation(),
                            mostNewTask.getDateOfCreation());
                }
            }

            @Override
            public void onDataNotAvailable() {
                mStatisticsView.showLoadingIndicatorError();
            }
        });
    }
}
