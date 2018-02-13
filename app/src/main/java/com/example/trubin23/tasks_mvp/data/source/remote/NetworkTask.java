package com.example.trubin23.tasks_mvp.data.source.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrey on 13.02.2018.
 */

class NetworkTask {

    @SerializedName("id")
    @Expose
    private String mId;

    @SerializedName("title")
    @Expose
    private String mTitle;

    @SerializedName("description")
    @Expose
    private String mDescription;

    @SerializedName("dateOfCreation")
    @Expose
    private String mDateOfCreation;

    NetworkTask(@NonNull String title, @NonNull String description,
                @NonNull String id, @Nullable String dateOfCreation) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mDateOfCreation = dateOfCreation;
    }

    String getId() {
        return mId;
    }

    void setId(String id) {
        mId = id;
    }

    String getTitle() {
        return mTitle;
    }

    void setTitle(String title) {
        mTitle = title;
    }

    String getDescription() {
        return mDescription;
    }

    void setDescription(String description) {
        mDescription = description;
    }

    String getDateOfCreation() {
        return mDateOfCreation;
    }

    void setDateOfCreation(String dateOfCreation) {
        mDateOfCreation = dateOfCreation;
    }

}
