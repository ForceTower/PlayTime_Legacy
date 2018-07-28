package com.forcetower.playtime.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;

import com.forcetower.playtime.R;

public class UIAlphaFrame {
    private final View center;
    private final View bottom;
    private final View top;
    private final View start;
    private final View end;
    private final View main;

    private int current = Color.WHITE;

    public UIAlphaFrame(View root) {
        center = root.findViewById(R.id.frame_c);
        bottom = root.findViewById(R.id.frame_b);
        top    = root.findViewById(R.id.frame_t);
        start  = root.findViewById(R.id.frame_s);
        end    = root.findViewById(R.id.frame_e);
        main   = root;
    }

    public void changeAlphaTo(int color) {
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), current, color);
        colorAnimation.setDuration(250);

        colorAnimation.addUpdateListener(animator -> {
            ColorStateList tint = ColorStateList.valueOf((int) animator.getAnimatedValue());
            center.setBackgroundTintList(tint);
            bottom.setBackgroundTintList(tint);
            top.setBackgroundTintList(tint);
            start.setBackgroundTintList(tint);
            end.setBackgroundTintList(tint);
            main.setBackgroundTintList(tint);
        });

        colorAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                current = color;
            }
        });
        colorAnimation.start();
    }
}
