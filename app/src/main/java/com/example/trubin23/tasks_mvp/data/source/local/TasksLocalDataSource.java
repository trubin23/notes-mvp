package com.example.trubin23.tasks_mvp.data.source.local;

import com.example.trubin23.tasks_mvp.data.source.TasksDataSource;

/**
 * Created by Andrey on 14.02.2018.
 */

public interface TasksLocalDataSource extends TasksDataSource {

    void deleteAllTasks();
}
