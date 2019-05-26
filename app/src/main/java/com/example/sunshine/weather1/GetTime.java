package com.example.sunshine.weather1;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Leila on 4/11/2019.
 */

public class GetTime {



    public void showTime(){
        Date date = new Date();
        String strDateFormat = "hh:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        Log.i("time","formttedDate");
    }
}
