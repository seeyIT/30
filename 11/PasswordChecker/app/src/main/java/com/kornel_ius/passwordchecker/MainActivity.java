package com.kornel_ius.passwordchecker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static String SYMBOLS = "!@#$%^&*()[];',./{}:\"<>?`~";

    private EditText mInputEditText;
    private TextView mLengthTextView;
    private TextView mUpperCaseTextView;
    private TextView mLowerCaseTextView;
    private TextView mNumberTextView;
    private TextView mSymbolTextView;
    private boolean mLengthState;
    private boolean mUpperState;
    private boolean mLowerState;
    private boolean mNumberState;
    private boolean mSymbolState;
    private int mGreenColor;
    private int mRedColor;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            check();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mInputEditText = (EditText) findViewById(R.id.inputTextView);
        mLengthTextView = (TextView) findViewById(R.id.lengthPasswordTextView);
        mUpperCaseTextView = (TextView) findViewById(R.id.upperCasePasswordTextView);
        mLowerCaseTextView = (TextView) findViewById(R.id.lowerCasePasswordTextView);
        mNumberTextView = (TextView) findViewById(R.id.numberPasswordTextView);
        mSymbolTextView = (TextView) findViewById(R.id.symbolPasswordTextView);
        mGreenColor = getResources().getColor(R.color.green);
        mRedColor = getResources().getColor(R.color.red);
        resetStates();
        mInputEditText.addTextChangedListener(textWatcher);

    }

    private void check() {
        resetStates();

        String password = mInputEditText.getText().toString();
        mLengthState = password.length() >= 13;
        for (int i = 0; i < password.length(); ++i) {
            char letter = password.charAt(i);
            if (SYMBOLS.indexOf(letter) != -1) {
                mSymbolState = true;
                continue;
            }
            int letter2 = (int) letter;
            if (letter2 >= 48 && letter2 <= 57) {
                mNumberState = true;
                continue;
            }
            if (letter2 >= 97 && letter2 <= 122) {
                mLowerState = true;
                continue;
            }
            if (letter2 >= 65 && letter2 <= 90) {
                mUpperState = true;
            }
        }
        colorTexts();
    }


    private void colorTexts() {
        if (mLengthState) {
            mLengthTextView.setTextColor(mGreenColor);
        } else {
            mLengthTextView.setTextColor(mRedColor);
        }
        if (mUpperState) {
            mUpperCaseTextView.setTextColor(mGreenColor);
        } else {
            mUpperCaseTextView.setTextColor(mRedColor);
        }
        if (mLowerState) {
            mLowerCaseTextView.setTextColor(mGreenColor);
        } else {
            mLowerCaseTextView.setTextColor(mRedColor);
        }
        if (mNumberState) {
            mNumberTextView.setTextColor(mGreenColor);
        } else {
            mNumberTextView.setTextColor(mRedColor);
        }
        if (mSymbolState) {
            mSymbolTextView.setTextColor(mGreenColor);
        } else {
            mSymbolTextView.setTextColor(mRedColor);
        }
    }

    private void resetStates() {
        mLengthState = false;
        mUpperState = false;
        mLowerState = false;
        mNumberState = false;
        mSymbolState = false;
    }

    public void clear(View view) {
        mInputEditText.setText("");
        resetStates();
    }


}
