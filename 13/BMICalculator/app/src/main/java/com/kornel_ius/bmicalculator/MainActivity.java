package com.kornel_ius.bmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mResultTextView;
    private EditText mHeightEditText;
    private EditText mWeightEditText;
    private int mGreenColor;
    private int mRedColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mResultTextView = (TextView) findViewById(R.id.resultTextView);
        mHeightEditText = (EditText) findViewById(R.id.heightEditText);
        mWeightEditText = (EditText) findViewById(R.id.weightEditText);
        mGreenColor = getResources().getColor(R.color.green);
        mRedColor = getResources().getColor(R.color.red);
    }

    public void calculate(View view) {
        if (mWeightEditText.getText().toString().length() == 0 || mHeightEditText.getText().toString().length() == 0) {
            return;
        }
        float weight = Float.parseFloat(mWeightEditText.getText().toString());
        float height = Float.parseFloat(mHeightEditText.getText().toString());
        float result = weight / (height * height / 10000);
        if (result < 18.5) {
            mResultTextView.setTextColor(mRedColor);
            mResultTextView.setText(getResources().getString(R.string.to_low_weight) + result);
        } else if (result > 25) {
            mResultTextView.setTextColor(mRedColor);
            mResultTextView.setText(getResources().getString(R.string.to_high_weight) + result);

        } else {
            mResultTextView.setTextColor(mGreenColor);
            mResultTextView.setText(getResources().getString(R.string.perfect_weight) + result);

        }
    }
}
