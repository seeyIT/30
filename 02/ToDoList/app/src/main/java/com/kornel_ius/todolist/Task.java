package com.kornel_ius.todolist;

/**
 * Created by Kornel_ius.
 */

public class Task {

    private String mTask;
    private String mPriority;

    public Task(String task, String priority) {
        mTask = task;
        mPriority = priority;
    }

    public String getTask() {
        return mTask;
    }

    public String getPriority() {
        return mPriority;
    }
}
