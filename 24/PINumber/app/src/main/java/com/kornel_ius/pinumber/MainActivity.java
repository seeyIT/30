package com.kornel_ius.pinumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mOutputTextView;
    private EditText mInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {
        mOutputTextView = (TextView) findViewById(R.id.outputTextView);
        mInputEditText = (EditText) findViewById(R.id.inputEditText);
    }

    public void show(View view) {
        int length = Integer.parseInt(mInputEditText.getText().toString());
        mOutputTextView.setText(Utilities.getPI(length));
    }
}

