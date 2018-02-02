package com.example.trubin23.tasks_mvp.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.trubin23.tasks_mvp.data.source.TasksRepository;
import com.example.trubin23.tasks_mvp.data.source.local.TasksDatabase;
import com.example.trubin23.tasks_mvp.data.source.local.TasksLocalDataSource;

/**
 * Created by Andrey on 01.02.2018.
 */

public class Injection {

    @NonNull
    public static TasksRepository provideTasksRepository(@NonNull Context context) {
        TasksDatabase database = TasksDatabase.getInstance(context);

        return TasksRepository.getInstance(new AppExecutors(),
                TasksLocalDataSource.getInstance(database.taskDao()));
    }
}
