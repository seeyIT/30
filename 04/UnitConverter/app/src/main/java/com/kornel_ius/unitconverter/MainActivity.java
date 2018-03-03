package com.kornel_ius.unitconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mBinaryTextView;
    private TextView mDecimalTextView;
    private TextView mHexTextView;
    private TextView mCurrentTextView;
    private ArrayList<Button> mButtonList = new ArrayList<>();
    private String mEquation = "";
    private int mModeFlag = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();

    }

    private void init() {
        mBinaryTextView = (TextView) findViewById(R.id.binaryTextView);
        mDecimalTextView = (TextView) findViewById(R.id.decimalTextView);
        mHexTextView = (TextView) findViewById(R.id.hexTextView);
        mButtonList.add((Button) findViewById(R.id.Button0));
        mButtonList.add((Button) findViewById(R.id.Button1));
        mButtonList.add((Button) findViewById(R.id.Button2));
        mButtonList.add((Button) findViewById(R.id.Button3));
        mButtonList.add((Button) findViewById(R.id.Button4));
        mButtonList.add((Button) findViewById(R.id.Button5));
        mButtonList.add((Button) findViewById(R.id.Button6));
        mButtonList.add((Button) findViewById(R.id.Button7));
        mButtonList.add((Button) findViewById(R.id.Button8));
        mButtonList.add((Button) findViewById(R.id.Button9));
        mButtonList.add((Button) findViewById(R.id.ButtonA));
        mButtonList.add((Button) findViewById(R.id.ButtonB));
        mButtonList.add((Button) findViewById(R.id.ButtonC));
        mButtonList.add((Button) findViewById(R.id.ButtonD));
        mButtonList.add((Button) findViewById(R.id.ButtonE));
        mButtonList.add((Button) findViewById(R.id.ButtonF));

        mBinaryTextView.setOnClickListener(textViewClick);
        mDecimalTextView.setOnClickListener(textViewClick);
        mHexTextView.setOnClickListener(textViewClick);
        mDecimalTextView.performClick();
    }

    private View.OnClickListener textViewClick = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            int id = view.getId();
            for (int i = 2; i < 16; ++i) {
                mButtonList.get(i).setEnabled(false);
            }
            int length = 2;
            if (id == R.id.binaryTextView) {
                mModeFlag = 1;
                mEquation = mBinaryTextView.getText().toString();
            } else if (id == R.id.decimalTextView) {
                length = 10;
                mModeFlag = 2;
                mEquation = mDecimalTextView.getText().toString();

            } else if (id == R.id.hexTextView) {
                length = 16;
                mModeFlag = 3;
                mEquation = mHexTextView.getText().toString();
            }
            for (int i = 2; i < length; ++i) {
                mButtonList.get(i).setEnabled(true);

            }
            mCurrentTextView = (TextView) view;
            mBinaryTextView.setBackgroundColor(getResources().getColor(R.color.gray));
            mDecimalTextView.setBackgroundColor(getResources().getColor(R.color.gray));
            mHexTextView.setBackgroundColor(getResources().getColor(R.color.gray));
            mCurrentTextView.setBackgroundColor(getResources().getColor(R.color.blueGray));
        }
    };

    public void buttonClick(View view) {
        Button button = (Button) view;
        mEquation = mEquation + ((Button) view).getText().toString();

        if (mModeFlag == 1) {
            int decimal = Integer.parseInt(mEquation, 2);
            mCurrentTextView.setText(mEquation);
            mDecimalTextView.setText(decimal + "");
            mHexTextView.setText(Integer.toHexString(decimal));
        } else if (mModeFlag == 2) {
            mBinaryTextView.setText(Integer.toBinaryString(Integer.valueOf(mEquation)));
            mCurrentTextView.setText(mEquation);
            mHexTextView.setText(Integer.toHexString(Integer.valueOf(mEquation)));
        } else if (mModeFlag == 3) {
            int decimal = Integer.parseInt(mEquation, 16);
            mBinaryTextView.setText(Integer.toBinaryString(decimal));
            mDecimalTextView.setText(decimal + "");
            mCurrentTextView.setText(mEquation);

        }
    }

    public void clearScreens(View view) {
        mEquation = "";
        mBinaryTextView.setText("");
        mDecimalTextView.setText("");
        mHexTextView.setText("");
    }
}
