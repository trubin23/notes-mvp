package com.example.trubin23.tasks_mvp.data.source.remote;

import com.example.trubin23.tasks_mvp.data.Task;

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

    @GET("/tasks")
    Call<List<Task>> getTasks();

    @GET("/tasks/{id}")
    Call<Task> getTask(@Path("id") String id);

    @POST("/tasks")
    void addTask(@Body Task task);

    @PUT("/tasks/{id}")
    void updateTask(@Path("id") String id, @Body Task task);

    @DELETE("tasks/{id}")
    void deleteTask(@Path("id") String id);
}
