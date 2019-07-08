package com.example.myapplication.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class Helper {
    public static int dpToPx(Context context, float dp) {
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }
}
