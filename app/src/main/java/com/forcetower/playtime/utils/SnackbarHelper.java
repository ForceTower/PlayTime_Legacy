package com.forcetower.playtime.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.forcetower.playtime.R;
import com.google.android.material.snackbar.Snackbar;

import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;

public class SnackbarHelper {

    public static void configSnackbar(Context context, Snackbar snack) {
        setRoundBordersBg(context, snack);
        ViewCompat.setElevation(snack.getView(), 6f);

        Typeface font = ResourcesCompat.getFont(context, R.font.product_sans_regular);

        View snackbarView = snack.getView();
        TextView tv = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setTypeface(font);

        TextView at = snackbarView.findViewById(com.google.android.material.R.id.snackbar_action);
        at.setTypeface(font, Typeface.BOLD);
    }


    private static void setRoundBordersBg(Context context, Snackbar snackbar) {
        snackbar.getView().setBackground(context.getDrawable(R.drawable.snackbar_background));
    }
}