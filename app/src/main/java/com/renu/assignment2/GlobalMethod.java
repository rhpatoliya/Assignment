package com.renu.assignment2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

public class GlobalMethod {

    public static final int REQUEST_RETURN_TO_MANAGER = 101;
    public static final int RESULT_RETURN_TO_MANAGER = 102;

    public static String getHistoryDate() {
        SimpleDateFormat format = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy" );
        Date date = Calendar.getInstance().getTime();
        String currentdate = format.format(date);
        return currentdate;

    }
}
