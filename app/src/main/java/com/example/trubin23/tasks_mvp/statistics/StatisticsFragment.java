package com.example.trubin23.tasks_mvp.statistics;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trubin23.tasks_mvp.R;

import butterknife.ButterKnife;

/**
 * Created by Andrey on 16.02.2018.
 */

public class StatisticsFragment extends Fragment implements StatisticsContract.View {

    private StatisticsContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.statistics_frag, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void setPresenter(@NonNull StatisticsContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
