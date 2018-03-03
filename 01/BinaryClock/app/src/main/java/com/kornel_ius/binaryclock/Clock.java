package com.kornel_ius.binaryclock;

import android.graphics.Color;
import android.os.Handler;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kornel_ius.
 */

public class Clock {

    private MainActivity mContext;

    private ArrayList<LinearLayout> mSecondsUnitArray = new ArrayList<LinearLayout>();
    private ArrayList<LinearLayout> mSecondsTensArray = new ArrayList<LinearLayout>();

    private ArrayList<LinearLayout> mMinutesUnitArray = new ArrayList<LinearLayout>();
    private ArrayList<LinearLayout> mMinutesTensArray = new ArrayList<LinearLayout>();

    private ArrayList<LinearLayout> mHoursUnitArray = new ArrayList<LinearLayout>();
    private ArrayList<LinearLayout> mHoursTensArray = new ArrayList<LinearLayout>();

    private ClockHelper mClock;

    public Clock(MainActivity context) {
        mContext = context;
        init();
        mClock = new ClockHelper();
    }

    private void init() {
        mSecondsTensArray.add((LinearLayout)mContext.findViewById(R.id.secondsTens4));
        mSecondsTensArray.add((LinearLayout) mContext.findViewById(R.id.secondsTens2));
        mSecondsTensArray.add((LinearLayout) mContext.findViewById(R.id.secondsTens1));

        mSecondsUnitArray.add((LinearLayout) mContext.findViewById(R.id.secondsUnit8));
        mSecondsUnitArray.add((LinearLayout) mContext.findViewById(R.id.secondsUnit4));
        mSecondsUnitArray.add((LinearLayout) mContext.findViewById(R.id.secondsUnit2));
        mSecondsUnitArray.add((LinearLayout) mContext.findViewById(R.id.secondsUnit1));

        mMinutesTensArray.add((LinearLayout) mContext.findViewById(R.id.minutesTens4));
        mMinutesTensArray.add((LinearLayout) mContext.findViewById(R.id.minutesTens2));
        mMinutesTensArray.add((LinearLayout) mContext.findViewById(R.id.minutesTens1));

        mMinutesUnitArray.add((LinearLayout) mContext.findViewById(R.id.minutesUnit8));
        mMinutesUnitArray.add((LinearLayout) mContext.findViewById(R.id.minutesUnit4));
        mMinutesUnitArray.add((LinearLayout) mContext.findViewById(R.id.minutesUnit2));
        mMinutesUnitArray.add((LinearLayout) mContext.findViewById(R.id.minutesUnit1));

        mHoursTensArray.add((LinearLayout) mContext.findViewById(R.id.hoursTens2));
        mHoursTensArray.add((LinearLayout) mContext.findViewById(R.id.hoursTens1));

        mHoursUnitArray.add((LinearLayout) mContext.findViewById(R.id.hoursUnit8));
        mHoursUnitArray.add((LinearLayout) mContext.findViewById(R.id.hoursUnit4));
        mHoursUnitArray.add((LinearLayout) mContext.findViewById(R.id.hoursUnit2));
        mHoursUnitArray.add((LinearLayout) mContext.findViewById(R.id.hoursUnit1));

    }

    public void startClock() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mClock.setTime(new Date());
                handler.postDelayed(this, 1000);
                updateSecondsUnit(mClock.getSecondsUnit());
                updateSecondsTens(mClock.getSecondsTens());
                updateMinutesUnit(mClock.getMinutesUnit());
                updateMinutesTens(mClock.getMinutesTens());
                updateHoursUnit(mClock.getHoursUnit());
                updateHoursTens(mClock.getHoursTens());
            }
        }, 1000);
    }

    private void updateSecondsUnit(String seconds) {
        for (int i = 0; i < 4; ++i) {
            if (seconds.charAt(i) == '1') {
                mSecondsUnitArray.get(i).setBackground(mContext.getResources().getDrawable(R.drawable.blue_circle_on));
            } else {
                mSecondsUnitArray.get(i).setBackground(mContext.getResources().getDrawable(R.drawable.blue_circle_off));
            }
        }
    }

    private void updateSecondsTens(String seconds) {
        for (int i = 0; i < 3; ++i) {
            if (seconds.charAt(i) == '1') {
                mSecondsTensArray.get(i).setBackground(mContext.getResources().getDrawable(R.drawable.blue_circle_on));
            } else {
                mSecondsTensArray.get(i).setBackground(mContext.getResources().getDrawable(R.drawable.blue_circle_off));
            }
        }
    }

    private void updateMinutesUnit(String minutes) {
        for (int i = 0; i < 4; ++i) {
            if (minutes.charAt(i) == '1') {
                mMinutesUnitArray.get(i).setBackground(mContext.getResources().getDrawable(R.drawable.green_circle_on));
            } else {
                mMinutesUnitArray.get(i).setBackground(mContext.getResources().getDrawable(R.drawable.green_circle_off));
            }
        }
    }

    private void updateMinutesTens(String minutes) {
        for (int i = 0; i < 3; ++i) {
            if (minutes.charAt(i) == '1') {
                mMinutesTensArray.get(i).setBackground(mContext.getResources().getDrawable(R.drawable.green_circle_on));
            } else {
                mMinutesTensArray.get(i).setBackground(mContext.getResources().getDrawable(R.drawable.green_circle_off));
            }
        }
    }

    private void updateHoursUnit(String hours) {
        for (int i = 0; i < 4; ++i) {
            if (hours.charAt(i) == '1') {
                mHoursUnitArray.get(i).setBackground(mContext.getResources().getDrawable(R.drawable.red_circle_on));
            } else {
                mHoursUnitArray.get(i).setBackground(mContext.getResources().getDrawable(R.drawable.red_circle_off));
            }
        }
    }

    private void updateHoursTens(String hours) {
        for (int i = 0; i < 2; ++i) {
            if (hours.charAt(i) == '1') {
                mHoursTensArray.get(i).setBackground(mContext.getResources().getDrawable(R.drawable.red_circle_on));
            } else {
                mHoursTensArray.get(i).setBackground(mContext.getResources().getDrawable(R.drawable.red_circle_off));
            }
        }
    }
}
