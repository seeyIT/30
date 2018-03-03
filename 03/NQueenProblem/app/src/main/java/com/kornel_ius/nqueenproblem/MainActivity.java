package com.kornel_ius.nqueenproblem;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GridLayout mGridLayout;
    private ImageView[][] mBoard = new ImageView[8][8];
    private int mWhiteColor;
    private int mGreenColor;
    private int mGreyColor;
    private int mQueens = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();
    }

    private void init() {
        mGridLayout = (GridLayout) findViewById(R.id.mainGridLayout);
        mWhiteColor = getResources().getColor(R.color.white);
        mGreenColor = getResources().getColor(R.color.green);
        mGreyColor = getResources().getColor(R.color.grey);

        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                ImageView imageView = new ImageView(this);
                LinearLayout.LayoutParams viewGroup = new LinearLayout.LayoutParams(128, 128);
                viewGroup.setMargins(2, 2, 2, 2);
                imageView.setLayoutParams(viewGroup);
                mBoard[i][j] = imageView;
                mGridLayout.addView(imageView);

                final int finalI = i;
                final int finalJ = j;
                imageView.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View view) {
                        int color = Color.TRANSPARENT;
                        Drawable background = mBoard[finalI][finalJ].getBackground();
                        if (background instanceof ColorDrawable) {
                            color = ((ColorDrawable) background).getColor();
                        }
                        if (color == mGreyColor) {
                            Toast.makeText(MainActivity.this, getResources().getString(R.string.cannot_place_queen), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        for (int i = 0; i < 8; ++i) {
                            darken(mBoard[finalI][i]);
                            darken(mBoard[i][finalJ]);

                        }
                        int i = finalI;
                        int j = finalJ;

                        while (i != -1 && j != -1) {
                            darken(mBoard[i][j]);
                            --j;
                            --i;
                        }
                        i = finalI;
                        j = finalJ;
                        while (i != 8 && j != -1) {
                            darken(mBoard[i][j]);
                            --j;
                            ++i;
                        }
                        i = finalI;
                        j = finalJ;
                        while (i != 8 && j != 8) {
                            darken(mBoard[i][j]);
                            ++j;
                            ++i;
                        }
                        i = finalI;
                        j = finalJ;
                        while (i != -1 && j != 8) {
                            darken(mBoard[i][j]);
                            ++j;
                            --i;
                        }
                        mBoard[finalI][finalJ].setImageDrawable(getDrawable(R.drawable.queen));
                        ++mQueens;
                        checkWin();
                    }
                });
            }
        }
        restart();
    }

    private void darken(ImageView imageView) {
        imageView.setBackgroundColor(mGreyColor);
    }

    private void checkWin() {
        if (mQueens == 8) {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.win_message), Toast.LENGTH_LONG).show();
        }
    }

    private void restart() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if ((i + j) % 2 == 0) {
                    mBoard[i][j].setBackgroundColor(mWhiteColor);
                } else {
                    mBoard[i][j].setBackgroundColor(mGreenColor);
                }
                mBoard[i][j].setImageDrawable(null);
            }
        }
        mQueens = 0;
    }

    public void restartClick(View view) {
        restart();
    }

}
