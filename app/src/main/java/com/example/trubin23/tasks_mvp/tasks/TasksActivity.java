package com.example.trubin23.tasks_mvp.tasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.trubin23.tasks_mvp.R;

public class TasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_act);

        new TasksPresenter();
    }
}
