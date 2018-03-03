package com.kornel_ius.timer;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

    private DatePicker mDatePicker;
    private TimePicker mTimePicker;
    private TextView mTimerTextView;
    private int mState;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        reset();

    }

    private void init()
    {
        mDatePicker = (DatePicker)findViewById(R.id.datePicker);
        mTimePicker = (TimePicker)findViewById(R.id.timePicker);
        mTimerTextView = (TextView)findViewById(R.id.timerTextView);

    }

    private void reset()
    {
        mState = 0;
    }

    private void startTimer()
    {
        final int[] hours = {mDay * 24 + mMonth * 720 + mYear * 8640 + mHour + 1};
        final Handler h = new Handler();
        h.post(new Runnable() {
            @Override
            public void run() {
                hours[0] -= 1;
                h.postDelayed(this,3600000);
                mTimerTextView.setText(hours[0]+"");
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void click(View view)
    {
        if(mState == 0 )
        {
            mYear = mDatePicker.getYear() - Calendar.getInstance().get(Calendar.YEAR);
            if(mYear < 0 )
            {
                return;
            }
            mMonth = mDatePicker.getMonth() - Calendar.getInstance().get(Calendar.MONTH);
            if(mMonth < 0)
            {
                return;
            }
            mDay = mDatePicker.getDayOfMonth() - Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            if(mDay < 0 )
            {
                return;
            }
            mDatePicker.setVisibility(GONE);
            mTimePicker.setVisibility(View.VISIBLE);
            mState = 1;
        }
        else if(mState == 1)
        {
            mHour = mTimePicker.getHour() - Calendar.getInstance().get(Calendar.HOUR);
            if(mHour < 0 )
            {
                return;
            }

            mState = 2;
            mTimePicker.setVisibility(GONE);
            mTimerTextView.setVisibility(View.VISIBLE);
            startTimer();
            ((Button)view).setText("STOP");
        }
        else
        {
            ((Button)view).setText("NEXT");

            mState = 0;
            mTimerTextView.setVisibility(GONE);
            mDatePicker.setVisibility(View.VISIBLE);
        }
    }

}
