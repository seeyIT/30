package com.kornel_ius.todolist;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private TaskAdapter mTaskAdapter;
    private RecyclerView mRecyclerView;
    private FileManager mFileManager;
    private ArrayList<Task> mTaskArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        verifyStoragePermissions(this);
        init();
    }

    private void init() {
        mFileManager = new FileManager();
        mTaskArrayList = mFileManager.getTasks();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(false);

        mTaskAdapter = new TaskAdapter(mTaskArrayList);
        mRecyclerView.setAdapter(mTaskAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(new SwipeToDelete(mTaskAdapter));
        helper.attachToRecyclerView(mRecyclerView);
    }

    public void addTask(View view) {
        startActivity(new Intent(this, AddTaskActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTaskArrayList = mFileManager.getTasks();
        mTaskAdapter.notifyDataSetChanged();
    }

    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        ActivityCompat.requestPermissions(
                activity,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
        );
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}
