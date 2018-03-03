package com.kornel_ius.primarynumberchecker;

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

    public void check(View view) {
        long number = Long.parseLong(mInputEditText.getText().toString());
        mOutputTextView.setVisibility(View.VISIBLE);
        boolean isPrime = true;

        for (long i = 2; i < Math.sqrt(number); ++i) {
            if (number % i == 0) {
                isPrime = false;
            }
        }

        if (isPrime) {
            mOutputTextView.setText(getResources().getText(R.string.its_prime));
        } else {
            mOutputTextView.setText(getResources().getText(R.string.its_not_prime));
        }
    }
}
