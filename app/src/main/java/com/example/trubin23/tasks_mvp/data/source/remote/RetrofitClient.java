package com.example.trubin23.tasks_mvp.data.source.remote;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andrey on 11.02.2018.
 */

class RetrofitClient {

    private static final String BASE_URL = "https://trubin23.ru";

    private static RemoteService sRemoteService = null;

    @NonNull
    private static RemoteService getRemoteService() {
        if (sRemoteService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            sRemoteService = retrofit.create(RemoteService.class);
        }
        return sRemoteService;
    }

    static void getTasks(@NonNull Callback<List<NetworkTask>> callback) {
        RemoteService remoteService = getRemoteService();
        remoteService.getTasks().enqueue(callback);
    }

    static void getTask(@NonNull String id, @NonNull Callback<NetworkTask> callback) {
        RemoteService remoteService = getRemoteService();
        remoteService.getTask(id).enqueue(callback);
    }

    static void addTask(@NonNull NetworkTask task, @NonNull Callback<Integer> callback) {
        RemoteService remoteService = getRemoteService();
        remoteService.addTask(task).enqueue(callback);
    }

    static void updateTask(@NonNull NetworkTask task, @NonNull Callback<Integer> callback) {
        RemoteService remoteService = getRemoteService();
        remoteService.updateTask(task).enqueue(callback);
    }

    static void deleteTask(@NonNull String id, @NonNull Callback<Integer> callback) {
        RemoteService remoteService = getRemoteService();
        remoteService.deleteTask(id).enqueue(callback);
    }
}
