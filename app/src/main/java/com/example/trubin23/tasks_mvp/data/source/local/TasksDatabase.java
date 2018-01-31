package com.example.trubin23.tasks_mvp.data.source.local;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.trubin23.tasks_mvp.data.Task;

/**
 * Created by Andrey on 31.01.2018.
 */

@Database(entities = {Task.class}, version = 1)
public abstract class TasksDatabase extends RoomDatabase {

    private static TasksDatabase INSTANCE;

    public abstract TasksDao taskDao();

    private static final Object sLock = new Object();

    public static TasksDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        TasksDatabase.class, "Tasks.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}
