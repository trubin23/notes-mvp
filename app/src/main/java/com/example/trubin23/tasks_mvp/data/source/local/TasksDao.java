package com.example.trubin23.tasks_mvp.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.trubin23.tasks_mvp.data.Task;

import java.util.List;

/**
 * Created by Andrey on 31.01.2018.
 */

@Dao
public interface TasksDao {

    @Query("SELECT * FROM tasks")
    List<Task> getTasks();

    @Query("SELECT * FROM tasks WHERE id = :id")
    Task getTaskById(String id);

    @Update
    int updateTask(Task task);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Task task);

    @Query("DELETE FROM tasks WHERE id = :id")
    void deleteTaskById(String id);
}
