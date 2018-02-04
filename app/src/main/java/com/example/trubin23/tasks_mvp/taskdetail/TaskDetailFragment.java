package com.example.trubin23.tasks_mvp.taskdetail;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * Created by Andrey on 04.02.2018.
 */

public class TaskDetailFragment extends Fragment implements TaskDetailContract.View {

    private TaskDetailContract.Presenter mPresenter;

    @Override
    public void setPresenter(@NonNull TaskDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
