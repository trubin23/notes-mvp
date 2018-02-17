package com.example.trubin23.tasks_mvp.statistics;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trubin23.tasks_mvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Andrey on 16.02.2018.
 */

public class StatisticsFragment extends Fragment implements StatisticsContract.View {

    private StatisticsContract.Presenter mPresenter;

    @BindView(R.id.statistics)
    TextView mStatisticsTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.statistics_frag, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull StatisticsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoadingIndicator() {
        mStatisticsTV.setText(R.string.loading);
    }

    @Override
    public void showLoadingIndicatorError() {
        mStatisticsTV.setText(R.string.statistics_error);
    }

    @Override
    public void showStatistics(@NonNull String mostOldTask, @NonNull String mostNewTask) {
        mStatisticsTV.setText(String.format("%s %s\n%s %s",
                getString(R.string.most_old_task), mostOldTask,
                getString(R.string.most_new_task), mostNewTask));
    }
}
