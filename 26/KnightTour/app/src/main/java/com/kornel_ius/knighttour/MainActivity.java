package com.kornel_ius.knighttour;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mJumpIterator = 1;
    private GridLayout mGridLayout;
    private TextView[][] mBoard = new TextView[8][8];
    private int mWhiteColor;
    private int mBrownColor;
    private int mPreviousI = -1;
    private int mPreviousJ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();
    }

    private void init() {
        mGridLayout = (GridLayout) findViewById(R.id.mainGridLayout);
        mWhiteColor = Color.WHITE;
        mBrownColor = getResources().getColor(R.color.brown);

        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                final TextView textView = new TextView(this);
                LinearLayout.LayoutParams viewGroup = new LinearLayout.LayoutParams(128, 128);
                viewGroup.setMargins(2, 2, 2, 2);
                textView.setLayoutParams(viewGroup);
                textView.setTextSize(32);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.BLACK);
                mBoard[i][j] = textView;
                mGridLayout.addView(textView);

                final int finalI = i;
                final int finalJ = j;
                textView.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View view) {
                        if (mPreviousI == -1 ||
                                (Math.abs(mPreviousI - finalI) == 2 && Math.abs(mPreviousJ - finalJ) == 1) ||
                                (Math.abs(mPreviousI - finalI) == 1 && Math.abs(mPreviousJ - finalJ) == 2)) {
                            if (!textView.getText().toString().equals("")) {
                                return;
                            }
                            checkWin();
                            mPreviousI = finalI;
                            mPreviousJ = finalJ;
                            clearKnight();
                            textView.setBackgroundResource(R.drawable.knight);
                            textView.setText(mJumpIterator + "");
                            mJumpIterator++;

                        }

                    }
                });
            }
        }
        restart();
    }

    private void checkWin() {
        if (mJumpIterator == 64) {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.solved), Toast.LENGTH_LONG).show();
        }
    }

    private void clearKnight() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if ((i + j) % 2 == 0) {
                    mBoard[i][j].setBackgroundColor(mWhiteColor);
                } else {
                    mBoard[i][j].setBackgroundColor(mBrownColor);
                }
            }
        }
    }

    private void restart() {
        clearKnight();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                mBoard[i][j].setText("");
            }
        }
        mPreviousI = -1;
        mJumpIterator = 1;
    }

    public void restartClick(View view) {
        restart();
    }

}
