package com.example.trubin23.tasks_mvp.taskdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.trubin23.tasks_mvp.R;
import com.example.trubin23.tasks_mvp.util.ActivityUtils;
import com.example.trubin23.tasks_mvp.util.Injection;

/**
 * Created by Andrey on 03.02.2018.
 */

public class TaskDetailActivity extends AppCompatActivity {

    public static final String SHOW_TASK_ID = "TASK_ID";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdetail_act);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        TaskDetailFragment taskDetailFragment =
                (TaskDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (taskDetailFragment == null) {
            taskDetailFragment = new TaskDetailFragment();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), taskDetailFragment, R.id.contentFrame);
        }

        String taskId = getIntent().getStringExtra(SHOW_TASK_ID);

        new TaskDetailPresenter(
                Injection.provideTasksRepository(getApplicationContext()),
                taskDetailFragment,
                taskId);
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return true;
    }
}
