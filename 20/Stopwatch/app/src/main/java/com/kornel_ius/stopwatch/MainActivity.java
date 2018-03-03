package com.kornel_ius.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    private final int INTERVAL = 10;
    private long mTime = 0;
    private long mLapTime = 0;
    private boolean mLapStarted = false;

    private TextView mMainTextView;
    private TextView mSmallTimerTextView;
    private TextView mStartTextView;
    private TextView mStopTextView;
    private TextView mLapTextView;
    private TextView mResumeTextView;
    private TextView mRestartTextView;
    private ListView mListView;
    private Handler mMainHandler;
    private Handler mLapHandler;
    private Runnable mMainRunnable;
    private Runnable mLapRunnable;
    private ArrayAdapter<String> mLapArrayAdapter;
    private ArrayList<String> mLapsTime = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initListeners();
    }

    private void init() {
        mMainTextView = (TextView) findViewById(R.id.mainTextView);
        mSmallTimerTextView = (TextView) findViewById(R.id.smallTimerTextView);
        mStartTextView = (TextView) findViewById(R.id.startTextView);
        mStopTextView = (TextView) findViewById(R.id.stopTextView);
        mLapTextView = (TextView) findViewById(R.id.lapTextView);
        mResumeTextView = (TextView) findViewById(R.id.resumeTextView);
        mRestartTextView = (TextView) findViewById(R.id.restartTextView);
        mListView = (ListView) findViewById(R.id.listView);


        mLapArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mLapsTime);
        mListView.setAdapter(mLapArrayAdapter);


        mMainHandler = new Handler();
        mLapHandler = new Handler();

        mMainRunnable = new Runnable() {
            @Override
            public void run() {
                mTime += INTERVAL;
                displayMainTime();
                mMainHandler.postDelayed(this, INTERVAL);
            }
        };
        mLapRunnable = new Runnable() {
            @Override
            public void run() {
                mLapTime += INTERVAL;
                displayLapTime();
                mLapHandler.postDelayed(this, INTERVAL);
            }
        };
    }

    private void initListeners() {
        mStartTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });

        mStopTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();
            }
        });

        mLapTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lap();
            }
        });

        mResumeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resume();
            }
        });

        mRestartTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart();
            }
        });
    }


    private void displayMainTime() {
        mMainTextView.setText(timeToString(mTime));
    }

    private void displayLapTime() {
        mSmallTimerTextView.setText(timeToString(mLapTime));
    }

    private String timeToString(long time) {
        long minutes = time / 6000;
        String minutesString;
        if (minutes < 10) {
            minutesString = "0" + minutes;
        } else {
            minutesString = String.valueOf(minutes);
        }
        String secondsString;
        time %= 6000;
        long second = time / 100;
        if (second < 10) {
            secondsString = "0" + second;
        } else {
            secondsString = String.valueOf(second);

        }
        time %= 100;
        time /= 10;
        String finalTime = minutesString + ":" + secondsString + ":" + time;
        return finalTime;
    }

    private void start() {
        mMainHandler.postDelayed(mMainRunnable, INTERVAL);
        mStartTextView.setVisibility(GONE);
        mStopTextView.setVisibility(VISIBLE);
        mLapTextView.setVisibility(VISIBLE);
    }

    private void stop() {
        mMainHandler.removeCallbacks(mMainRunnable);
        mLapHandler.removeCallbacks(mLapRunnable);
        mStopTextView.setVisibility(GONE);
        mLapTextView.setVisibility(GONE);
        mResumeTextView.setVisibility(VISIBLE);
        mRestartTextView.setVisibility(VISIBLE);
    }

    private void resume() {
        mMainHandler.postDelayed(mMainRunnable, INTERVAL);
        mResumeTextView.setVisibility(GONE);
        mRestartTextView.setVisibility(GONE);
        mStopTextView.setVisibility(VISIBLE);
        mLapTextView.setVisibility(VISIBLE);
    }

    private void lap() {

        if (!mLapStarted) {
            mLapStarted = true;
            mLapHandler.postDelayed(mLapRunnable, INTERVAL);
            mLapTime = mTime;
        }
        addNewLap();
    }

    private void addNewLap() {
        mLapsTime.add(timeToString(mTime) + " --- " + timeToString(mLapTime));
        mLapArrayAdapter.notifyDataSetChanged();
        mLapTime = 0;

    }

    private void restart() {
        mTime = 0;
        mLapTime = 0;
        mLapStarted = false;
        displayMainTime();
        displayLapTime();
        mStartTextView.setVisibility(VISIBLE);
        mRestartTextView.setVisibility(GONE);
        mResumeTextView.setVisibility(GONE);
    }


}
