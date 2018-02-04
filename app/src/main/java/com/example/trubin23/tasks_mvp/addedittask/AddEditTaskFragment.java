package com.example.trubin23.tasks_mvp.addedittask;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * Created by Andrey on 04.02.2018.
 */

public class AddEditTaskFragment extends Fragment implements AddEditTaskContract.View {

    AddEditTaskContract.Presenter mPresenter;

    @Override
    public void setPresenter(@NonNull AddEditTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
