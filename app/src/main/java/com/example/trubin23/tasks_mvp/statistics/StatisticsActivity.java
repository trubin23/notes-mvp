package com.example.trubin23.tasks_mvp.statistics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.trubin23.tasks_mvp.R;
import com.example.trubin23.tasks_mvp.util.ActivityUtils;
import com.example.trubin23.tasks_mvp.util.Injection;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_act);

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
}
