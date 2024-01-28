package com.view.menu.mydropdown;

import android.content.Context;
import android.util.DisplayMetrics;

public class Utils {

    public static int dPtoPixel(Context context, int dp) {

        return (dp * (context.getResources().getDisplayMetrics().densityDpi))/ DisplayMetrics.DENSITY_DEFAULT;


    }

    public static int PixeltoDp(Context context, int px) {

        return (px / (context.getResources().getDisplayMetrics().densityDpi));
    }
}
