package com.forcetower.playtime.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class AnimUtils {

    public static void fadeIn(Context context, View v) {
        if (v.getVisibility() == View.VISIBLE) return;
        Animation fadeInAnim = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        v.setVisibility(View.VISIBLE);
        v.startAnimation(fadeInAnim);
        v.requestLayout();
    }

    public static void fadeOut(Context context, View v) {
        if (v.getVisibility() == View.INVISIBLE) return;
        Animation fadeOutAnim = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
        v.startAnimation(fadeOutAnim);
        v.setVisibility(View.INVISIBLE);
        v.requestLayout();
    }

    public static void fadeOutGone(Context context, View v) {
        if (v.getVisibility() == View.GONE) return;
        Animation fadeOutAnim = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
        v.startAnimation(fadeOutAnim);
        fadeOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        v.requestLayout();
    }
}