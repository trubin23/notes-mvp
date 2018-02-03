package com.example.trubin23.tasks_mvp.tasks.tasklist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trubin23.tasks_mvp.R;
import com.example.trubin23.tasks_mvp.data.Task;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Andrey on 02.02.2018.
 */

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskHolder> {

    private List<Task> mTasks;
    private TaskItemListener mTaskItemListener;

    @Override
    public TasksAdapter.TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TasksAdapter.TaskHolder holder, int position) {
        Task task = mTasks.get(position);
        holder.setTask(task);
        holder.itemView.setOnClickListener(v -> mTaskItemListener.onTaskClick(task));
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    class TaskHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView mTitleTV;

        @BindView(R.id.description)
        TextView mDescriptionTV;

        @BindView(R.id.title)
        TextView mDateOfCreateTV;

        TaskHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setTask(@NonNull Task task){
            mTitleTV.setText(task.getTitle());
            mDescriptionTV.setText(task.getDescription());
            mDateOfCreateTV.setText(task.getDateOfCreation());
        }
    }
}
