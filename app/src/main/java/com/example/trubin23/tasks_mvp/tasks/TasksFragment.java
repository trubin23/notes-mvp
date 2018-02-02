package com.example.trubin23.tasks_mvp.tasks;

import android.support.v4.app.Fragment;

/**
 * Created by Andrey on 30.01.2018.
 */

public class TasksFragment extends Fragment implements TasksContract.View {

    TasksContract.Presenter mPresenter;

    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
