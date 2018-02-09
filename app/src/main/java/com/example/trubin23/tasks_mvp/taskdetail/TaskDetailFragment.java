package com.example.trubin23.tasks_mvp.taskdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trubin23.tasks_mvp.R;
import com.example.trubin23.tasks_mvp.addedittask.AddEditTaskActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Andrey on 04.02.2018.
 */

public class TaskDetailFragment extends Fragment implements TaskDetailContract.View {

    private TaskDetailContract.Presenter mPresenter;

    @BindView(R.id.task_detail_title)
    TextView mDetailTitle;

    @BindView(R.id.task_detail_description)
    TextView mDetailDescription;

    @BindView(R.id.task_detail_date)
    TextView mDetailDateOfCreate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.taskdetail_frag, container, false);
        ButterKnife.bind(this, root);

        setHasOptionsMenu(true);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_edit_task);
        fab.setOnClickListener(v -> mPresenter.editTask());

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.taskdetail_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                mPresenter.deleteTask();
                return true;
        }
        return false;
    }

    @Override
    public void setPresenter(@NonNull TaskDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setTitle(@NonNull String title) {
        mDetailTitle.setText(title);
    }

    @Override
    public void setDescription(@NonNull String description) {
        mDetailDescription.setText(description);
    }

    @Override
    public void setDateOfCreate(@NonNull String dateOfCreate) {
        mDetailDateOfCreate.setText(dateOfCreate);
    }

    @Override
    public void showMissingTask() {
        mDetailTitle.setText("");
        mDetailDescription.setText(getString(R.string.no_data));
        mDetailDateOfCreate.setText("");
    }

    @Override
    public void showEditTask(@NonNull String taskId) {
        Intent intent = new Intent(getContext(), AddEditTaskActivity.class);
        intent.putExtra(AddEditTaskActivity.EDIT_TASK_ID, taskId);
        startActivityForResult(intent, AddEditTaskActivity.REQUEST_ADD_TASK);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.activityResult(requestCode, resultCode);
    }

    @Override
    public void activityFinish() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }
}
