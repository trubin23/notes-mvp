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
                //TODO: realize the function
                //for (Task task : tasks){
                //    task.dateOfCreationToLong();
                //}
            }

            @Override
            public void onDataNotAvailable() {
                mStatisticsView.showLoadingIndicatorError();
            }
        });
    }
}
