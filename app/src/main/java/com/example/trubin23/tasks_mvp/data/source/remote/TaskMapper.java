package com.example.trubin23.tasks_mvp.data.source.remote;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.data.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 13.02.2018.
 */

class TaskMapper {

    @NonNull
    static Task networkTaskToTask(@NonNull NetworkTask networkTask) {
        return new Task(networkTask.getTitle(), networkTask.getDescription(),
                networkTask.getId(), networkTask.getDateOfCreation());
    }

    @NonNull
    static NetworkTask taskToNetworkTask(@NonNull Task task) {
        return new NetworkTask(task.getTitle(), task.getDescription(),
                task.getId(), task.getDateOfCreation());
    }

    @NonNull
    static List<Task> networkTaskListToTaskList(@NonNull List<NetworkTask> networkTasks) {
        List<Task> tasks = new ArrayList<>();

        for (NetworkTask networkTask : networkTasks) {
            Task task = networkTaskToTask(networkTask);
            tasks.add(task);
        }

        return tasks;
    }
}
