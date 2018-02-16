package com.example.trubin23.tasks_mvp.statistics;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.BasePresenter;
import com.example.trubin23.tasks_mvp.BaseView;

/**
 * Created by Andrey on 16.02.2018.
 */

public interface StatisticsContract {

    interface View extends BaseView<Presenter> {

        void showLoadingIndicator();

        void showLoadingIndicatorError();

        void showStatistics(@NonNull String text);
    }

    interface Presenter extends BasePresenter {

    }
}
