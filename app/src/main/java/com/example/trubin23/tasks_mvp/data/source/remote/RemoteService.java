package com.example.trubin23.tasks_mvp.data.source.remote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Andrey on 10.02.2018.
 */

public interface RemoteService {

    @GET("/api/tasks")
    Call<List<NetworkTask>> getTasks();

    @GET("/api/tasks/{id}")
    Call<NetworkTask> getTask(@Path("id") String id);

    @POST("/api/tasks")
    Call<Integer> addTask(@Body NetworkTask task);

    @PUT("/api/tasks/{id}")
    Call<Integer> updateTask(@Path("id") String id, @Body NetworkTask task);

    @DELETE("/api/tasks/{id}")
    Call<Integer> deleteTask(@Path("id") String id);
}
