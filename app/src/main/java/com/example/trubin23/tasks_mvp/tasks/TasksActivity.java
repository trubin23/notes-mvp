package com.example.trubin23.tasks_mvp.tasks;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.trubin23.tasks_mvp.R;
import com.example.trubin23.tasks_mvp.util.ActivityUtils;
import com.example.trubin23.tasks_mvp.util.Injection;

public class TasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_act);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);

        TasksFragment tasksFragment =
                (TasksFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (tasksFragment == null){
            tasksFragment = new TasksFragment();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), tasksFragment, R.id.contentFrame);
        }

        new TasksPresenter(Injection.provideTasksRepository(getApplicationContext()),
                tasksFragment);
    }
}
