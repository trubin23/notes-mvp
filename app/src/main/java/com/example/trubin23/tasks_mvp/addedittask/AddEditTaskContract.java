package com.example.trubin23.tasks_mvp.addedittask;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.BasePresenter;
import com.example.trubin23.tasks_mvp.BaseView;

/**
 * Created by Andrey on 04.02.2018.
 */

public interface AddEditTaskContract {

    interface View extends BaseView<Presenter>{

        void setTitle(@NonNull String title);

        void setDescription(@NonNull String description);

        void showEmptyTaskError();
    }

    interface Presenter extends BasePresenter {

    }
}
