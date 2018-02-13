package com.example.trubin23.tasks_mvp.data.source.remote;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.data.Task;
import com.example.trubin23.tasks_mvp.data.source.TasksDataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Andrey on 10.02.2018.
 */

public class TasksRemoteDataSource implements TasksDataSource {

    private static TasksRemoteDataSource INSTANCE;

    public static TasksRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (TasksRemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TasksRemoteDataSource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {
        RetrofitClient.getTasks(new Callback<List<NetworkTask>>() {
            @Override
            public void onResponse(Call<List<NetworkTask>> call, Response<List<NetworkTask>> response) {
                if (response.isSuccessful()) {
                    List<NetworkTask> networkTasks = response.body();
                    List<Task> tasks = TaskMapper.INSTANCE.networkTaskListToTaskList(networkTasks);
                    callback.onTasksLoaded(tasks);
                } else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<List<NetworkTask>> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getTask(@NonNull String id, @NonNull GetTaskCallback callback) {
        RetrofitClient.getTask(id, new Callback<NetworkTask>() {
            @Override
            public void onResponse(Call<NetworkTask> call, Response<NetworkTask> response) {
                if (response.isSuccessful()) {
                    NetworkTask networkTask = response.body();
                    Task task = TaskMapper.INSTANCE.networkTaskToTask(networkTask);
                    callback.onTaskLoaded(task);
                } else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<NetworkTask> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void saveTask(@NonNull Task task) {
        NetworkTask networkTask = TaskMapper.INSTANCE.taskToNetworkTask(task);

        RetrofitClient.addTask(networkTask, new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
            }
        });
    }

    @Override
    public void updateTask(@NonNull Task task) {
        NetworkTask networkTask = TaskMapper.INSTANCE.taskToNetworkTask(task);

        RetrofitClient.updateTask(networkTask, new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
            }
        });
    }

    @Override
    public void deleteTask(@NonNull String id) {
        RetrofitClient.deleteTask(id, new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
            }
        });
    }
}
