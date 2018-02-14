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
        mNetworkIO.execute(() ->
                RetrofitClient.getTasks(new ProcessingResponse<List<NetworkTask>>() {
                    @Override
                    public void responseBody(@NonNull List<NetworkTask> body) {
                        List<Task> tasks = TaskMapper.networkTaskListToTaskList(body);
                        callback.onTasksLoaded(tasks);
                    }

                    @Override
                    public void dataNotAvailable() {
                        callback.onDataNotAvailable();
                    }
                })
        );
    }

    @Override
    public void getTask(@NonNull String id, @NonNull GetTaskCallback callback) {
        mNetworkIO.execute(() ->
                RetrofitClient.getTask(id, new ProcessingResponse<NetworkTask>() {
                    @Override
                    public void responseBody(@NonNull NetworkTask body) {
                        Task task = TaskMapper.networkTaskToTask(body);
                        callback.onTaskLoaded(task);
                    }

                    @Override
                    public void dataNotAvailable() {
                        callback.onDataNotAvailable();
                    }
                })
        );
    }

    @Override
    public void saveTask(@NonNull Task task) {
        NetworkTask networkTask = TaskMapper.taskToNetworkTask(task);

        mNetworkIO.execute(() ->
                RetrofitClient.addTask(networkTask, new ProcessingResponse<>()));
    }

    @Override
    public void updateTask(@NonNull Task task) {
        NetworkTask networkTask = TaskMapper.taskToNetworkTask(task);

        mNetworkIO.execute(() ->
                RetrofitClient.updateTask(networkTask, new ProcessingResponse<>()));
    }

    @Override
    public void deleteTask(@NonNull String id) {
        mNetworkIO.execute(() ->
                RetrofitClient.deleteTask(id, new ProcessingResponse<>()));
    }
}
