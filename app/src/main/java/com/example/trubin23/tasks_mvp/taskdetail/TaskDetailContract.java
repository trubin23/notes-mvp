package com.example.trubin23.tasks_mvp.taskdetail;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.BasePresenter;
import com.example.trubin23.tasks_mvp.BaseView;

/**
 * Created by Andrey on 04.02.2018.
 */

public interface TaskDetailContract {

    interface View extends BaseView<Presenter> {

        void setTitle(@NonNull String title);

        void setDescription(@NonNull String description);

        void setDateOfCreate(@NonNull String dateOfCreate);

        void showMissingTask();

        void showEditTask(@NonNull String taskId);

        void activityFinish();
    }

    interface Presenter extends BasePresenter {

        void editTask();

        void activityResult(int requestCode, int resultCode);
    }
}
