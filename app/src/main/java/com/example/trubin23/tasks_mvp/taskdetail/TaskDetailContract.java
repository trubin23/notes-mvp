package com.example.trubin23.tasks_mvp.taskdetail;

import com.example.trubin23.tasks_mvp.BasePresenter;
import com.example.trubin23.tasks_mvp.BaseView;
import com.example.trubin23.tasks_mvp.data.Task;

/**
 * Created by Andrey on 04.02.2018.
 */

public interface TaskDetailContract {

    interface View extends BaseView<Presenter> {

        void showTask(Task task);

        void showMissingTask();
    }

    interface Presenter extends BasePresenter {

    }
}
