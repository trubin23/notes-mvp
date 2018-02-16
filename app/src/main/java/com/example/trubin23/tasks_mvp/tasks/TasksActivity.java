package com.example.trubin23.tasks_mvp.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.trubin23.tasks_mvp.R;
import com.example.trubin23.tasks_mvp.statistics.StatisticsActivity;
import com.example.trubin23.tasks_mvp.util.ActivityUtils;
import com.example.trubin23.tasks_mvp.util.Injection;

public class TasksActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_act);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        TasksFragment tasksFragment =
                (TasksFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (tasksFragment == null) {
            tasksFragment = new TasksFragment();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), tasksFragment, R.id.contentFrame);
        }

        new TasksPresenter(Injection.provideTasksRepository(getApplicationContext()),
                tasksFragment);
    }

    private void setupDrawerContent(@NonNull NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.list_nav_menu_item:
                            break;
                        case R.id.statistics_nav_menu_item:
                            Intent intent = new Intent(
                                    TasksActivity.this, StatisticsActivity.class);
                            startActivity(intent);
                            break;
                        default:
                            break;
                    }

                    mDrawerLayout.closeDrawers();
                    return true;
                }
        );
    }
}
