package com.kornel_ius.todolist;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private static int viewHolderCount;

    private ArrayList<Task> mTaskArrayList;

    private FileManager mFileManager;

    public TaskAdapter(ArrayList<Task> taskArrayList) {
        viewHolderCount = 0;
        mTaskArrayList = taskArrayList;
        mFileManager = new FileManager();
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.task_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        TaskViewHolder viewHolder = new TaskViewHolder(view);

        int backgroundColorForViewHolder;
        if (mTaskArrayList.get(viewHolderCount).getPriority().equals("High")) {
            backgroundColorForViewHolder = ContextCompat.getColor(context, R.color.high_priority);
        } else if (mTaskArrayList.get(viewHolderCount).getPriority().equals("Medium")) {
            backgroundColorForViewHolder = ContextCompat.getColor(context, R.color.medium_priority);
        } else {
            backgroundColorForViewHolder = ContextCompat.getColor(context, R.color.low_priority);
        }
        viewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);

        viewHolderCount++;
        return viewHolder;
    }


    public void delete(int index) {
        mTaskArrayList.remove(index);
        this.notifyDataSetChanged();
        viewHolderCount = 0;
        mFileManager.saveTasks(mTaskArrayList);
    }

    public void onBindViewHolder(TaskViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mTaskArrayList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        private TextView mTaskTextView;

        public TaskViewHolder(View itemView) {
            super(itemView);
            mTaskTextView = (TextView) itemView.findViewById(R.id.taskTextView);
        }

        void bind(int listIndex) {
            mTaskTextView.setText(mTaskArrayList.get(listIndex).getTask());
        }
    }
}
