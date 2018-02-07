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
            new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

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

    public Task(@NonNull String id, @NonNull String title, @NonNull String description,
                @Nullable String dateOfCreation) {
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
        this(UUID.randomUUID().toString(), title, description, null);
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
}
