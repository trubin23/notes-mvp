package com.example.trubin23.tasks_mvp.data.source.remote;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.data.Task;
import com.example.trubin23.tasks_mvp.data.source.TasksDataSource;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Andrey on 10.02.2018.
 */

public class TasksRemoteRepository implements TasksDataSource {

    private static TasksRemoteRepository INSTANCE;

    private static final int THREAD_COUNT = 3;

    private final Executor mNetworkIO;

    private TasksRemoteRepository(@NonNull Executor networkIO) {
        mNetworkIO = networkIO;
    }

    public static TasksRemoteRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (TasksRemoteRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TasksRemoteRepository(
                            Executors.newFixedThreadPool(THREAD_COUNT));
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {
        RetrofitClient.getTasks(new Callback<List<NetworkTask>>() {
            @Override
            public void onResponse(Call<List<NetworkTask>> call,
                                   Response<List<NetworkTask>> response) {
                if (response.isSuccessful()) {
                    List<NetworkTask> networkTasks = response.body();
                    List<Task> tasks = TaskMapper.networkTaskListToTaskList(networkTasks);
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
                    Task task = TaskMapper.networkTaskToTask(networkTask);
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
        NetworkTask networkTask = TaskMapper.taskToNetworkTask(task);

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
        NetworkTask networkTask = TaskMapper.taskToNetworkTask(task);

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
