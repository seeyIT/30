package com.kornel_ius.bubblesort;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mNumbers[] = null;
    private EditText mInputEditText;
    private TextView mOutputTextView;

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

    private void sort(int newNumber) {
        if (mNumbers == null) {
            mNumbers = new int[1];
            mNumbers[0] = newNumber;
            return;
        }
        int temp[] = new int[mNumbers.length + 1];
        int length = mNumbers.length;
        System.arraycopy(mNumbers, 0, temp, 0, length);
        temp[temp.length - 1] = newNumber;


        for (int i = 0; i < temp.length; ++i) {
            for (int j = 1; j < temp.length - i; ++j) {
                if (temp[j] > temp[j - 1]) {
                    int t = temp[j];
                    temp[j] = temp[j - 1];
                    temp[j - 1] = t;
                }
            }
        }
        mNumbers = temp;
    }

    private void display() {
        String output = "";

        for (int i = 0; i < mNumbers.length; ++i) {
            output += mNumbers[i] + ", ";
        }
        mOutputTextView.setText(output);
    }

    public void addNumber(View view) {
        String input = mInputEditText.getText().toString();
        if (input.length() == 0) {
            return;
        }
        int number = Integer.parseInt(input);
        mInputEditText.setText("");
        sort(number);
        display();
    }

    public void clear(View view) {
        mNumbers = null;
        display();
    }

}
