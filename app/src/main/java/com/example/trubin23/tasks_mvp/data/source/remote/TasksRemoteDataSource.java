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
        RetrofitClient.getTasks(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (response.isSuccessful()) {
                    callback.onTasksLoaded(response.body());
                } else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getTask(@NonNull String id, @NonNull GetTaskCallback callback) {
        RetrofitClient.getTask(id, new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.isSuccessful()) {
                    callback.onTaskLoaded(response.body());
                } else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void saveTask(@NonNull Task task) {
        RetrofitClient.addTask(task, new Callback<Integer>() {
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
        RetrofitClient.updateTask(task, new Callback<Integer>() {
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
