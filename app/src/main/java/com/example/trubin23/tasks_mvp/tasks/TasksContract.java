package com.example.trubin23.tasks_mvp.tasks;

import com.example.trubin23.tasks_mvp.BasePresenter;
import com.example.trubin23.tasks_mvp.BaseView;

/**
 * Created by Andrey on 30.01.2018.
 */

public interface TasksContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

    }
}
