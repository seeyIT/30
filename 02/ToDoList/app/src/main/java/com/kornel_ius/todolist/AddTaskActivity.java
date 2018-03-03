package com.kornel_ius.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    private EditText mEditText;
    private RadioGroup mRadioGroup;
    private FileManager mFileManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getSupportActionBar().hide();
        init();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mFileManager = new FileManager();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void init() {
        mEditText = (EditText) findViewById(R.id.taskEditText);
        mRadioGroup = (RadioGroup) findViewById(R.id.priorityRadioGroup);
    }

    public void createNewTask(View view) {
        String taskText = mEditText.getText().toString();
        if (taskText.length() == 0) {
            Toast.makeText(this, getString(R.string.name_new_task), Toast.LENGTH_SHORT).show();
            return;
        }
        int radioButtonID = mRadioGroup.getCheckedRadioButtonId();
        View radioButton = mRadioGroup.findViewById(radioButtonID);
        int idx = mRadioGroup.indexOfChild(radioButton);
        RadioButton r = (RadioButton) mRadioGroup.getChildAt(idx);
        String selectedtext = r.getText().toString();

        mFileManager.addTask(taskText + ";" + selectedtext);
        Toast.makeText(this, getString(R.string.task_added), Toast.LENGTH_SHORT).show();
        mEditText.setText("");
    }
}
