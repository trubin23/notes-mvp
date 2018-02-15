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

        void showTasks(@NonNull List<Task> tasks);

        void showSuccessfullySavedMessage();
    }

    interface Presenter extends BasePresenter {

        void activityResult(int requestCode, int resultCode);

        void refreshTasks();
    }
}
