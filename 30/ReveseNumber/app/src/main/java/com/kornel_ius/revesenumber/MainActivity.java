package com.kornel_ius.revesenumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mInputEditText;
    private TextView mResultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();
    }

    private void init() {
        mInputEditText = (EditText) findViewById(R.id.inputEditText);
        mResultTextView = (TextView) findViewById(R.id.resultTextView);
    }

    private String mathReverse(long number) {
        String result = "";

        while (number > 0) {
            result = result + (number % 10);
            number /= 10;
        }
        return result;
    }

    public void calculate(View view) {
        long number = Long.parseLong(mInputEditText.getText().toString());
        String reversed = mathReverse(number);
        mResultTextView.setText(reversed);
    }
}
