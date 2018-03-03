package com.kornel_ius.todolist;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Kornel_ius.
 */

public class SwipeToDelete extends ItemTouchHelper.SimpleCallback {

    private TaskAdapter mTaskAdapter;

    public SwipeToDelete(TaskAdapter taskAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, ItemTouchHelper.START | ItemTouchHelper.END);
        mTaskAdapter = taskAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mTaskAdapter.delete(viewHolder.getAdapterPosition());

    }
}
