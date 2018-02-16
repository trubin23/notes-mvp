package com.example.trubin23.tasks_mvp.statistics;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.data.source.TasksRepository;

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

    }
}
