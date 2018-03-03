package com.kornel_ius.temperatureconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String mDisplay = "";
    private TextView mDisplayTextView;
    private int mSourceTemp = -1;
    private int mDestTemp = -1;
    private boolean mTempTrigger = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();
    }

    private void init() {
        mDisplayTextView = (TextView) findViewById(R.id.displayTextView);
    }

    private void refreshDisplay() {
        mDisplayTextView.setText(mDisplay);
    }

    private void clear() {
        mSourceTemp = -1;
        mDestTemp = -1;
        mDisplay = "";
        refreshDisplay();
    }

    public void numberPress(View view) {
        mDisplay = mDisplay + ((Button) view).getText().toString();
        refreshDisplay();
    }


    public void clearPress(View view) {
        clear();
    }

    public void execute(View view) {
        if (mDestTemp == -1 || mSourceTemp == -1) {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.pick_temp), Toast.LENGTH_SHORT).show();
            return;
        }

        if (mDestTemp == mSourceTemp) {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.same_scales), Toast.LENGTH_SHORT).show();
            return;
        }
        double temperature = Integer.valueOf(mDisplay);
        double converted;
        if (mSourceTemp == 1) {
            if (mDestTemp == 2) {
                converted = temperature - 272.15;
            } else {
                converted = temperature * 9 / 5 - 459.67;
            }
        } else if (mSourceTemp == 2) {
            if (mDestTemp == 1) {
                converted = temperature + 272.15;
            } else {
                converted = temperature * 9 / 5 + 32;
            }
        } else {
            if (mDestTemp == 1) {
                converted = (temperature + 459.67) * 5 / 9;
            } else {
                converted = (temperature - 32) * 5 / 9;
            }
        }
        mDisplay = "";
        mDisplayTextView.setText(String.valueOf(converted));
    }

    public void tempPress(View view) {
        int temp = Integer.valueOf(((Button) view).getTag().toString());
        if (mTempTrigger) {
            mSourceTemp = temp;
            Toast.makeText(MainActivity.this, getResources().getString(R.string.source_set), Toast.LENGTH_SHORT).show();
        } else {
            mDestTemp = temp;
            Toast.makeText(MainActivity.this, getResources().getString(R.string.dest_set), Toast.LENGTH_SHORT).show();
        }

        mTempTrigger = !mTempTrigger;
    }
}
