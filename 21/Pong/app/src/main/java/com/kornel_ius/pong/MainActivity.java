package com.kornel_ius.pong;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

    private int mScreenWidth;
    private int mScreenHeight;
    private float mXDirection;
    private float mYDirection;
    private ImageView mLeftPaddle;
    private ImageView mRightPaddle;
    private ImageView mBall;
    private TextView mLoseTextView;
    private RelativeLayout mScreenRelativeLayout;
    private Handler mHandler;
    private Runnable mRunnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        calcScreenSizes();
        init();
        moveBall();
    }

    private void init() {
        mLeftPaddle = (ImageView) findViewById(R.id.leftPaddle);
        mRightPaddle = (ImageView) findViewById(R.id.rightPaddle);
        mBall = (ImageView) findViewById(R.id.ballImageView);
        mLoseTextView = (TextView) findViewById(R.id.loseInfo);
        mBall.setX(mScreenWidth / 2);
        mBall.setY(mScreenHeight / 2);
        mLeftPaddle.setX(10);
        mRightPaddle.setX(mScreenWidth - 10 - 20);
        mScreenRelativeLayout = (RelativeLayout) findViewById(R.id.mainRelativeLayout);
        mXDirection = 1f;
        mYDirection = 1f;
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                mBall.setX(mBall.getX() + 20 * mXDirection);
                mBall.setY(mBall.getY() + 20 * mYDirection);
                detectRightCollistion();
                if (detectRightCollistion()) {
                    mHandler.postDelayed(this, 50);

                }
                else
                {
                    lose();
                }
            }
        };


        mScreenRelativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    mRightPaddle.setY(motionEvent.getY()-100);
                    mLeftPaddle.setY(motionEvent.getY()-100);
                }
                return false;
            }
        });
    }

    private void moveBall() {
        mHandler.postDelayed(mRunnable, 10);

    }

    private void stopBall() {
        mHandler.removeCallbacks(mRunnable);
        mHandler = new Handler();
    }

    private boolean detectRightCollistion() {
        int ballX = (int) mBall.getX();
        int ballY = (int) mBall.getY();
        int paddleRightX = (int) mRightPaddle.getX();
        int paddleRightY = (int) mRightPaddle.getY();
        int paddleLeftX = (int) mLeftPaddle.getX();
        int paddleLeftY = (int) mLeftPaddle.getY();
        Log.e("ballX", ballX + "");
        Log.e("paddleX", paddleRightX + "");
        if (paddleRightX - (ballX  + mBall.getWidth()) < 10) {

            if (ballY + mBall.getHeight() / 2 > paddleRightY && ballY + mBall.getHeight() / 2 < paddleRightY + mRightPaddle.getHeight()) {
                Toast.makeText(this, "cc", Toast.LENGTH_SHORT);
                mXDirection = -1f;

            }

        }
        if(ballX - (paddleLeftX +mLeftPaddle.getWidth()) < 10)
        {
            if (ballY + mBall.getHeight() / 2 > paddleRightY && ballY + mBall.getHeight() / 2 < paddleRightY + mRightPaddle.getHeight()) {
                Toast.makeText(this, "cc", Toast.LENGTH_SHORT);
                mXDirection = 1f;

            }
        }
        if(mScreenHeight - (ballY + mBall.getHeight()) - 50 < 10 )
        {
            mYDirection = -1f;

        }
        if(ballY < 10)
        {
            mYDirection = 1f;

        }
        if(ballX < 0 || (ballX + mBall.getWidth()) > mScreenWidth)
        {
            return false;
        }
        return true;

    }

    private void lose()
    {
        mLoseTextView.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoseTextView.setVisibility(GONE);
                mBall.setX(mScreenWidth/2);
                mBall.setY(mScreenHeight/2);
                moveBall();
            }
        },5000);
    }

    private void calcScreenSizes() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mScreenWidth = size.x;
        mScreenHeight = size.y;
    }

}
