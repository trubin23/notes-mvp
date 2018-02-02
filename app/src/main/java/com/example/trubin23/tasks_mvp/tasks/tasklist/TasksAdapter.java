package com.example.trubin23.tasks_mvp.tasks.tasklist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Andrey on 02.02.2018.
 */

public class TasksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TaskHolder extends RecyclerView.ViewHolder {

        public TaskHolder(View itemView) {
            super(itemView);
        }
    }
}
