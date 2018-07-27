package com.forcetower.playtime.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class PixelUtils {
    public static int getPixelsFromDp(Context context, int dp) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }
}
