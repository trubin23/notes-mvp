package com.example.trubin23.tasks_mvp.data.source.remote;

import android.support.annotation.NonNull;

import com.example.trubin23.tasks_mvp.data.Task;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by Andrey on 11.02.2018.
 */

class RetrofitClient {

    private static final String BASE_URL = "https://trubin23.ru/api";

    private static RemoteService sRemoteService = null;

    @NonNull
    private static RemoteService getRemoteService() {
        if (sRemoteService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .build();

            sRemoteService = retrofit.create(RemoteService.class);
        }
        return sRemoteService;
    }

    static void getTasks(@NonNull Callback<List<Task>> callback) {
        RemoteService remoteService = getRemoteService();
        remoteService.getTasks().enqueue(callback);
    }

    static void getTask(@NonNull String id, @NonNull Callback<Task> callback) {
        RemoteService remoteService = getRemoteService();
        remoteService.getTask(id).enqueue(callback);
    }

    static void addTask(@NonNull Task task, @NonNull Callback<Integer> callback) {
        RemoteService remoteService = getRemoteService();
        remoteService.addTask(task).enqueue(callback);
    }

    static void updateTask(@NonNull Task task, @NonNull Callback<Integer> callback) {
        RemoteService remoteService = getRemoteService();
        remoteService.updateTask(task).enqueue(callback);
    }

    static void deleteTask(@NonNull String id, @NonNull Callback<Integer> callback) {
        RemoteService remoteService = getRemoteService();
        remoteService.deleteTask(id).enqueue(callback);
    }
}
