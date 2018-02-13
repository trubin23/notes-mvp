package com.example.trubin23.tasks_mvp.data.source.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrey on 13.02.2018.
 */

public class NetworkTask {

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

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDateOfCreation() {
        return mDateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        mDateOfCreation = dateOfCreation;
    }

}
