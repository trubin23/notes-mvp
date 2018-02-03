package com.example.trubin23.tasks_mvp.tasks;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.BasePresenter;
import com.example.trubin23.tasks_mvp.BaseView;
import com.example.trubin23.tasks_mvp.data.Task;

import java.util.List;

/**
 * Created by Andrey on 30.01.2018.
 */

public interface TasksContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showTask(@NonNull List<Task> tasks);
    }

    interface Presenter extends BasePresenter {

    }
}
