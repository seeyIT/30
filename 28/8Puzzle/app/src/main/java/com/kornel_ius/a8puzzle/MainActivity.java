package com.kornel_ius.a8puzzle;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int[] mBoardInt = new int[9];
    private ImageView[] mBoardImage = new ImageView[9];
    private Drawable[] mBoardDrawable = new Drawable[9];
    private GridLayout mGridLayout;
    private int mFreePlace = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initDrawables();
        init();
        shuffle();

    }

    private void initDrawables() {
        mBoardDrawable[0] = getResources().getDrawable(R.drawable.land1);
        mBoardDrawable[1] = getResources().getDrawable(R.drawable.land2);
        mBoardDrawable[2] = getResources().getDrawable(R.drawable.land3);
        mBoardDrawable[3] = getResources().getDrawable(R.drawable.land4);
        mBoardDrawable[4] = getResources().getDrawable(R.drawable.land5);
        mBoardDrawable[5] = getResources().getDrawable(R.drawable.land6);
        mBoardDrawable[6] = getResources().getDrawable(R.drawable.land7);
        mBoardDrawable[7] = getResources().getDrawable(R.drawable.land8);
        mBoardDrawable[8] = getResources().getDrawable(R.drawable.land9);
    }

    private void init() {
        mGridLayout = (GridLayout) findViewById(R.id.mainGridLayout);

        for (int i = 0; i < 9; ++i) {
            mBoardInt[i] = i;

            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams viewGroup = new LinearLayout.LayoutParams(256, 256);
            viewGroup.setMargins(2, 2, 2, 2);
            imageView.setLayoutParams(viewGroup);
            imageView.setBackground(mBoardDrawable[i]);
            mBoardImage[i] = imageView;
            mGridLayout.addView(imageView);

            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                    if (!canMove(finalI, mFreePlace)) {
                        return;
                    }
                    int temp = mBoardInt[finalI];
                    mBoardInt[finalI] = mBoardInt[mFreePlace];
                    mBoardInt[mFreePlace] = temp;
                    mBoardImage[finalI].setBackground(mBoardDrawable[mBoardInt[finalI]]);
                    mBoardImage[mFreePlace].setBackground(mBoardDrawable[mBoardInt[mFreePlace]]);
                    mFreePlace = finalI;
                    checkWin();
                }
            });
        }

    }

    private boolean canMove(int click, int free) {
        if (Math.abs(click - free) == 3) {
            return true;
        }
        if ((click == 2 || click == 5) && (free == 3 || free == 6)) {
            return false;
        }
        if ((click == 6 || click == 3) && (free == 2 || free == 5)) {
            return false;
        }
        if (Math.abs(click - free) == 1) {
            return true;
        }
        return false;
    }

    private void checkWin() {
        for (int i = 0; i < 9; ++i) {
            if (mBoardInt[i] != i) {
                return;
            }
        }
        Toast.makeText(MainActivity.this, getResources().getString(R.string.won), Toast.LENGTH_SHORT).show();
    }

    private void restart() {
        for (int i = 0; i < 9; ++i) {
            mBoardInt[i] = 1;
            mBoardImage[i].setBackground(mBoardDrawable[i]);
        }
    }

    private void shuffle() {
        Random r = new Random();
        for (int i = 0; i < 10000; ++i) {
            int random = r.nextInt(9);
            mBoardImage[random].performClick();
        }
    }

    public void restart(View view) {
        restart();
        shuffle();
    }
}
