package com.example.trubin23.tasks_mvp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Created by Andrey on 30.01.2018.
 */

@Entity(tableName = "tasks")
public final class Task {

    @Ignore
    private static final DateFormat sDateFormat =
            new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final String mId;

    @NonNull
    @ColumnInfo(name = "title")
    private final String mTitle;

    @NonNull
    @ColumnInfo(name = "description")
    private final String mDescription;

    @NonNull
    @ColumnInfo(name = "date-of-creation")
    private final String mDateOfCreation;

    public Task(@NonNull String title, @NonNull String description,
                @NonNull String id, @Nullable String dateOfCreation) {
        mId = id;
        mTitle = title;
        mDescription = description;
        if (dateOfCreation == null) {
            mDateOfCreation = sDateFormat.format(new Date());
        } else {
            mDateOfCreation = dateOfCreation;
        }
    }

    @Ignore
    public Task(@NonNull String title, @NonNull String description) {
        this(title, description, UUID.randomUUID().toString(), null);
    }

    @Ignore
    public Task(@NonNull String title, @NonNull String description, @NonNull String id) {
        this(title, description, id, null);
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    @NonNull
    public String getDescription() {
        return mDescription;
    }

    @NonNull
    public String getDateOfCreation() {
        return mDateOfCreation;
    }

    public boolean isEmpty(){
        return (mTitle==null || mTitle.isEmpty()) &&
                (mDescription==null || mDescription.isEmpty());
    }
}
