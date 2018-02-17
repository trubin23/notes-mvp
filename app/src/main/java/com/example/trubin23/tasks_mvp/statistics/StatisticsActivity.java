package com.example.trubin23.tasks_mvp.statistics;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.trubin23.tasks_mvp.R;
import com.example.trubin23.tasks_mvp.util.ActivityUtils;
import com.example.trubin23.tasks_mvp.util.Injection;

public class StatisticsActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_act);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        StatisticsFragment statisticsFragment =
                (StatisticsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (statisticsFragment == null) {
            statisticsFragment = new StatisticsFragment();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), statisticsFragment, R.id.contentFrame);
        }

        new StatisticsPresenter(Injection.provideTasksRepository(getApplicationContext()),
                statisticsFragment);
    }

    private void setupDrawerContent(@NonNull NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.list_nav_menu_item:
                            NavUtils.navigateUpFromSameTask(StatisticsActivity.this);
                            break;
                        case R.id.statistics_nav_menu_item:
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
