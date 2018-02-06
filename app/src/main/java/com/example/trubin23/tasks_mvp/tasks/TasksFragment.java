package com.example.trubin23.tasks_mvp.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trubin23.tasks_mvp.R;
import com.example.trubin23.tasks_mvp.addedittask.AddEditTaskActivity;
import com.example.trubin23.tasks_mvp.data.Task;
import com.example.trubin23.tasks_mvp.taskdetail.TaskDetailActivity;
import com.example.trubin23.tasks_mvp.tasks.tasklist.TaskItemListener;
import com.example.trubin23.tasks_mvp.tasks.tasklist.TasksAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Andrey on 30.01.2018.
 */

public class TasksFragment extends Fragment implements TasksContract.View {

    private TasksContract.Presenter mPresenter;

    private TasksAdapter mTasksAdapter;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TaskItemListener taskItemListener = this::showTaskDetail;
        mTasksAdapter = new TasksAdapter(taskItemListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tasks_frag, container, false);
        ButterKnife.bind(this, root);

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(mTasksAdapter);

        FloatingActionButton fab = root.findViewById(R.id.fab_add_task);
        fab.setOnClickListener(v -> showAddTask());

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull TasksContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(active));
    }

    @Override
    public void showTasks(@NonNull List<Task> tasks) {
        mTasksAdapter.setTasks(tasks);
    }

    private void showTaskDetail(@NonNull String taskId) {
        Intent intent = new Intent(getContext(), TaskDetailActivity.class);
        intent.putExtra(TaskDetailActivity.SHOW_TASK_ID, taskId);
        startActivity(intent);
    }

    private void showAddTask() {
        Intent intent = new Intent(getContext(), AddEditTaskActivity.class);
        startActivityForResult(intent, AddEditTaskActivity.REQUEST_ADD_TASK);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.activityResult(requestCode, resultCode);
    }

    @Override
    public void showSuccessfullySavedMessage() {
        Snackbar.make(getView(), R.string.successfully_saved_task_message,
                Snackbar.LENGTH_LONG).show();
    }
}
