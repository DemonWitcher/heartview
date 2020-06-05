package com.example.myapplication;

import android.content.Context;
import android.util.Log;

public class L {

    public static void i(String s) {
        Log.i("witcher", s);
    }

    public static int dp2px(Context context, int dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
