package com.kornel_ius.cipher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mInputEditText;
    private TextView mResultTextView;
    private RadioButton mMd5RadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mInputEditText = (EditText) findViewById(R.id.inputEditText);
        mResultTextView = (TextView) findViewById(R.id.resultTextView);
        mMd5RadioButton = (RadioButton) findViewById(R.id.md5RadioButton);
        mMd5RadioButton.setChecked(true);
    }


    public void encrypt(View view) {
        String toEncrypt = mInputEditText.getText().toString();
        String encrypted = "";

        if (mMd5RadioButton.isChecked()) {
            encrypted = Utilities.MD5(toEncrypt);
        } else {
            encrypted = Utilities.SHA1(toEncrypt);
        }
        mResultTextView.setText(encrypted);
        mResultTextView.setVisibility(View.VISIBLE);

    }
}
