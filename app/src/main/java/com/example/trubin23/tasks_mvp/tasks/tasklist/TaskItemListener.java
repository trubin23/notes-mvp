package com.example.trubin23.tasks_mvp.tasks.tasklist;

import android.support.annotation.NonNull;

/**
 * Created by Andrey on 03.02.2018.
 */

public interface TaskItemListener {

    void onTaskClick(@NonNull String taskId);
}
