package com.example.trubin23.tasks_mvp.addedittask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.trubin23.tasks_mvp.R;
import com.example.trubin23.tasks_mvp.util.ActivityUtils;
import com.example.trubin23.tasks_mvp.util.Injection;

public class AddEditTaskActivity extends AppCompatActivity {

    public static final String EDIT_TASK_ID = "EDIT_TASK_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtask_act);

        AddEditTaskFragment addEditTaskFragment =
                (AddEditTaskFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (addEditTaskFragment == null) {
            addEditTaskFragment = new AddEditTaskFragment();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), addEditTaskFragment, R.id.contentFrame);
        }

        String taskId = getIntent().getStringExtra(EDIT_TASK_ID);

        new AddEditTaskPresenter(
                Injection.provideTasksRepository(getApplicationContext()),
                addEditTaskFragment,
                taskId);
    }
}
