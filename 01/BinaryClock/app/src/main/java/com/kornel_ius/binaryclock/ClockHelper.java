package com.kornel_ius.binaryclock;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kornel_ius.
 */

public class ClockHelper {

    private SimpleDateFormat mDateFormat = new SimpleDateFormat("HHmmss");
    private String mTime;

    public ClockHelper() {

    }

    public void setTime(Date date) {
        mTime = mDateFormat.format(date);
    }

    private String addZeros(String time, int length) {
        while (time.length() < length) {
            time = "0" + time;
        }
        return time;
    }

    public String getSecondsUnit() {
        return addZeros(Integer.toBinaryString(((int) mTime.charAt(5)) - 48), 4);
    }

    public String getSecondsTens() {
        return addZeros(Integer.toBinaryString(((int) mTime.charAt(4)) - 48), 3);
    }

    public String getMinutesUnit() {
        return addZeros(Integer.toBinaryString(((int) mTime.charAt(3)) - 48), 4);
    }

    public String getMinutesTens() {
        return addZeros(Integer.toBinaryString(((int) mTime.charAt(2)) - 48), 3);
    }

    public String getHoursUnit() {
        return addZeros(Integer.toBinaryString(((int) mTime.charAt(1)) - 48), 4);
    }

    public String getHoursTens() {
        return addZeros(Integer.toBinaryString(((int) mTime.charAt(0)) - 48), 2);
    }
}
