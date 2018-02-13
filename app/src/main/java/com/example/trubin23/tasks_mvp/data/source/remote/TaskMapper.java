package com.example.trubin23.tasks_mvp.data.source.remote;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.data.Task;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 13.02.2018.
 */

@Mapper
abstract class TaskMapper {

    static final TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @NonNull
    Task networkTaskToTask(@NonNull NetworkTask networkTask){
        return new Task(networkTask.getTitle(), networkTask.getDescription(),
                networkTask.getId(), networkTask.getDateOfCreation());
    }

    @NonNull
    abstract NetworkTask taskToNetworkTask(@NonNull Task task);

    @NonNull
    List<Task> networkTaskListToTaskList(@NonNull List<NetworkTask> networkTasks){
        List<Task> tasks = new ArrayList<>();

        for (NetworkTask networkTask : networkTasks){
            Task task = networkTaskToTask(networkTask);
            tasks.add(task);
        }

        return tasks;
    }
}
