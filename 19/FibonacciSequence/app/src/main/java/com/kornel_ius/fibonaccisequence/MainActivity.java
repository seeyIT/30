package com.kornel_ius.fibonaccisequence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mResultTextView;
    private EditText mInputEditText;
    private ArrayList<Long> mFibNumbersArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mResultTextView = (TextView) findViewById(R.id.resultTextView);
        mInputEditText = (EditText) findViewById(R.id.inputEditText);
    }

    private void fibonacci(int number) {

        for (int i = 2; i < number; ++i) {
            mFibNumbersArrayList.add(mFibNumbersArrayList.get(i - 1) + mFibNumbersArrayList.get(i - 2));
        }
    }

    private String fibListToString() {
        String result = "";
        for (int i = 0; i < mFibNumbersArrayList.size(); ++i) {
            result += mFibNumbersArrayList.get(i) + ", ";
        }
        return result;
    }

    private String cutTwoSingsFromFib(String fib) {
        return fib.substring(0, fib.length() - 2);
    }

    public void show(View view) {
        int fibLen = Integer.parseInt(mInputEditText.getText().toString());
        mFibNumbersArrayList = new ArrayList<>();
        mFibNumbersArrayList.add(0l);
        mFibNumbersArrayList.add(1l);
        fibonacci(fibLen);
        mResultTextView.setText(cutTwoSingsFromFib(fibListToString()));
    }


}
