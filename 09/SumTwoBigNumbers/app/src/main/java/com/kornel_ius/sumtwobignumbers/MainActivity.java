package com.kornel_ius.sumtwobignumbers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private EditText mFirstNumber;
    private EditText mSecondNumber;
    private TextView mOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mFirstNumber = (EditText) findViewById(R.id.firstNumber);
        mSecondNumber = (EditText) findViewById(R.id.secondNumber);
        mOutput = (TextView) findViewById(R.id.outputTextView);
    }

    public void calculate(View view) {
        String first = mFirstNumber.getText().toString();
        String second = mSecondNumber.getText().toString();
        int firstArray[] = new int[first.length()];
        int secondArray[] = new int[second.length()];
        for (int i = 0; i < first.length(); ++i) {
            firstArray[i] = (int) first.charAt(i) - 48;
        }
        for (int i = 0; i < second.length(); ++i) {
            secondArray[i] = (int) second.charAt(i) - 48;

        }
        int buffor = 0;

        int output[] = null;
        if (first.length() > second.length()) {
            output = new int[first.length() + 1];
            Arrays.fill(output, 0);
            for (int i = 0; i < second.length(); ++i) {
                int value = secondArray[secondArray.length - 1 - i] + firstArray[firstArray.length - 1 - i] + buffor;
                if (value >= 10) {
                    value -= 10;

                    buffor = 1;

                } else {
                    buffor = 0;
                }
                output[output.length - i - 1] = value;

            }
            for (int i = firstArray.length - secondArray.length - 1; i >= 0; --i) {
                output[i + 1] = firstArray[i] + buffor;
                buffor = 0;
            }
        } else if (first.length() < second.length()) {
            output = new int[second.length() + 1];
            Arrays.fill(output, 0);
            for (int i = 0; i < first.length(); ++i) {
                int value = firstArray[firstArray.length - 1 - i] + secondArray[secondArray.length - 1 - i] + buffor;
                if (value >= 10) {
                    value -= 10;

                    buffor = 1;

                } else {
                    buffor = 0;
                }
                output[output.length - i - 1] = value;

            }
            for (int i = secondArray.length - firstArray.length - 1; i >= 0; --i) {
                output[i + 1] = secondArray[i] + buffor;
                buffor = 0;
            }
        } else {
            output = new int[second.length() + 1];
            Arrays.fill(output, 0);
            for (int i = 0; i < first.length(); ++i) {
                int value = firstArray[firstArray.length - 1 - i] + secondArray[secondArray.length - 1 - i] + buffor;
                if (value >= 10) {
                    value -= 10;

                    buffor = 1;

                } else {
                    buffor = 0;
                }
                output[output.length - i - 1] = value;

            }
            output[0] = buffor;
        }
        String out = "";
        if (output[0] != 0) {
            out += output[0];

        }
        for (int i = 1; i < output.length; ++i) {
            out += output[i];
        }
        mOutput.setText(out);
    }
}
