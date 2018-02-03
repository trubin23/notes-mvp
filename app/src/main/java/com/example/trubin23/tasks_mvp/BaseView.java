package com.example.trubin23.tasks_mvp;

import android.support.annotation.NonNull;

/**
 * Created by Andrey on 30.01.2018.
 */

public interface BaseView<T> {

    void setPresenter(@NonNull T presenter);

}
