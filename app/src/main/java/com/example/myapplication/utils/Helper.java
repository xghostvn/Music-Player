package com.example.myapplication.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class Helper {
    public static int dpToPx(Context context, float dp) {
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }

    public static String toTimeFormat(int millisecond){
            int duration = millisecond/1000;
            int hours = duration/3600;
            int remainder = duration - hours*3600;
            int minute = remainder / 60;
            remainder = remainder - minute * 60;
            int second = remainder ;

            String strhour = String.valueOf(hours);
            String strminute = String.valueOf(minute);
            String strsecond = String.valueOf(second);

            if(strminute.length() < 2){
                strminute = "0"+strminute;
            }
            if(strsecond.length() < 2){
                strsecond="0"+strsecond;
            }

           if(hours == 0 ){
               return strminute + ":" +strsecond;
           }else {
               return strhour + ":"+strminute+":"+strsecond;
           }





    }
}
