package com.kornel_ius.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mInputEditText;
    private TextView mOutputTextView;
    private int mNumberOne;
    private String mSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mInputEditText = (EditText) findViewById(R.id.inputEditText);
        mOutputTextView = (TextView) findViewById(R.id.outputTextView);
    }

    public void signClick(View view) {
        mSign = ((Button) view).getText().toString();
        try {
            mNumberOne = Integer.parseInt(mInputEditText.getText().toString());

        } catch (Exception e) {
        }
        mInputEditText.setText("");
    }

    public void calculate(View view) {
        int numberTwo = Integer.parseInt(mInputEditText.getText().toString());
        int result = 0;
        if (mSign.equals("+")) {
            result = mNumberOne + numberTwo;
        } else if (mSign.equals("-")) {
            result = mNumberOne - numberTwo;
        } else if (mSign.equals("*")) {
            result = mNumberOne * numberTwo;
        } else if (mSign.equals("/")) {
            result = mNumberOne / numberTwo;
        } else if (mSign.equals("%")) {
            result = mNumberOne % numberTwo;
        }
        mInputEditText.setText("");
        mNumberOne = result;
        mOutputTextView.setText(result + "");
    }
}
