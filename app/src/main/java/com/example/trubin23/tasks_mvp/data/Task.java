package com.example.trubin23.tasks_mvp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Andrey on 30.01.2018.
 */

@Entity(tableName = "tasks")
public final class Task {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final String mId;

    @NonNull
    @ColumnInfo(name = "title")
    private String mTitle;

    @NonNull
    @ColumnInfo(name = "description")
    private String mDescription;

    @NonNull
    @ColumnInfo(name = "date-of-creation")
    private String mDateOfCreation;

    public Task(String id, String title, String description, String dateOfCreation) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mDateOfCreation = dateOfCreation;
    }
}
