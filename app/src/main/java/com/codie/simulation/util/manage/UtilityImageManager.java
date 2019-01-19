package com.codie.simulation.util.manage;


import android.content.res.Resources;

import com.codie.simulation.ui.activity.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class UtilityImageManager {
    private static MainActivity mainActivity;


    public UtilityImageManager() {
    }

    public UtilityImageManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }



    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static String generateFileName() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getNow());
        stringBuffer.append("_vision.jpeg");

        return stringBuffer.toString();
    }

    public static String getNow() {
        Date presentTime_Date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
        return dateFormat.format(presentTime_Date);
    }
}
