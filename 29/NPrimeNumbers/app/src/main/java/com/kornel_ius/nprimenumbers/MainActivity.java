package com.kornel_ius.nprimenumbers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mResultTextView;
    private EditText mInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mInputEditText = (EditText) findViewById(R.id.inputEditText);
        mResultTextView = (TextView) findViewById(R.id.resultTextView);
    }

    private String getPrimes(int amount) {
        String result = "";
        boolean prime = true;
        int i = 0;
        int[] primes = new int[amount];

        for (int j = 2; j > 0; ++j) {
            prime = true;
            for (int k = 2; k < j; ++k) {
                if (j % k == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                primes[i] = j;
                result = result + j + ", ";
                ++i;

                if (i == amount) {
                    return result.substring(0, result.length() - 2);
                }
            }
        }

        return result;
    }

    public void calculate(View view) {
        int length = Integer.parseInt(mInputEditText.getText().toString());
        String primes = getPrimes(length);
        mResultTextView.setText(primes);
    }
}
