package com.example.trubin23.tasks_mvp.taskdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trubin23.tasks_mvp.R;
import com.example.trubin23.tasks_mvp.data.Task;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Andrey on 04.02.2018.
 */

public class TaskDetailFragment extends Fragment implements TaskDetailContract.View {

    private TaskDetailContract.Presenter mPresenter;

    @BindView(R.id.title)
    TextView mDetailTitle;

    @BindView(R.id.description)
    TextView mDetailDescription;

    @BindView(R.id.date_of_create)
    TextView mDetailDateOfCreate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.taskdetail_frag, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull TaskDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showTask(Task task) {
        mDetailTitle.setText(task.getTitle());
        mDetailDescription.setText(task.getDescription());
        mDetailDateOfCreate.setText(task.getDateOfCreation());
    }

    @Override
    public void showMissingTask() {
        mDetailTitle.setText("");
        mDetailDescription.setText(getString(R.string.no_data));
        mDetailDateOfCreate.setText("");
    }
}
