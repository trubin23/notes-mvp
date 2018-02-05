package com.example.trubin23.tasks_mvp.taskdetail;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.trubin23.tasks_mvp.data.Task;
import com.example.trubin23.tasks_mvp.data.source.TasksDataSource;
import com.example.trubin23.tasks_mvp.data.source.TasksRepository;

/**
 * Created by Andrey on 04.02.2018.
 */

public class TaskDetailPresenter implements TaskDetailContract.Presenter {

    private TasksRepository mTasksRepository;

    private TaskDetailContract.View mTaskDetailView;

    private String mTaskId;

    TaskDetailPresenter(@NonNull TasksRepository tasksRepository,
                        @NonNull TaskDetailContract.View taskDetailView,
                        @Nullable String taskId) {
        mTasksRepository = tasksRepository;
        mTaskDetailView = taskDetailView;

        mTaskDetailView.setPresenter(this);

        mTaskId = taskId;
    }

    @Override
    public void start() {
        openTask();
    }

    private void openTask() {
        if (mTaskId == null || mTaskId.isEmpty()) {
            mTaskDetailView.showMissingTask();
            return;
        }

        mTasksRepository.getTask(mTaskId, new TasksDataSource.GetTaskCallback() {
            @Override
            public void onTaskLoaded(Task task) {
                if (null == task) {
                    mTaskDetailView.showMissingTask();
                } else {
                    mTaskDetailView.setTitle(task.getTitle());
                    mTaskDetailView.setDescription(task.getDescription());
                    mTaskDetailView.setDateOfCreate(task.getDateOfCreation());
                }
            }

            @Override
            public void onDataNotAvailable() {
                mTaskDetailView.showMissingTask();
            }
        });
    }
}
