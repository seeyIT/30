package com.kornel_ius.caesarcipher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static String SMALL_LETTERS = "qwertyuiopasdfghjklzxcvbnm";
    private final static String CAPITAL_LETTERS = "QWERTYUIOPLKJHGFDSAZXCVBNM";
    private EditText mInputEditText;
    private NumberPicker mNumberPicker;
    private TextView mOutputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();
    }

    private void init() {
        mInputEditText = (EditText) findViewById(R.id.inputEditText);
        TextView mToCaesarTextView = (TextView) findViewById(R.id.toCaesarTextView);
        TextView mFromCaesarTextView = (TextView) findViewById(R.id.fromCaesarTextView);
        mNumberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        mOutputTextView = (TextView) findViewById(R.id.outputTextView);

        mToCaesarTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convert(mNumberPicker.getValue());
            }
        });

        mFromCaesarTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convert(-mNumberPicker.getValue());
            }
        });

        mNumberPicker.setMinValue(0);
        mNumberPicker.setMaxValue(26);
    }

    private void convert(int shift) {
        String input = mInputEditText.getText().toString();
        String output = "";

        for (int i = 0; i < input.length(); ++i) {

            char letter = input.charAt(i);
            for (int j = 0; j < 26; ++j) {
                if (letter == SMALL_LETTERS.charAt(j)) {
                    output = output + SMALL_LETTERS.charAt((j + shift) % 26);
                    break;
                } else if (letter == CAPITAL_LETTERS.charAt(j)) {
                    output = output + CAPITAL_LETTERS.charAt((j + shift) % 26);
                    break;
                }

            }
        }
        mOutputTextView.setText(output);
    }

}
