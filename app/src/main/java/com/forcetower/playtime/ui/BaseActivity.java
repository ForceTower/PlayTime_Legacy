package com.forcetower.playtime.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import timber.log.Timber;

import static com.forcetower.playtime.utils.ColorUtils.isColorDark;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void setStatusBarColor(@ColorInt int color) {
        View view = getWindow().getDecorView();
        int flags = view.getSystemUiVisibility();
        if (isColorDark(color)) {
            flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            Timber.d("Color is dark");
        } else {
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            Timber.d("Color is Light");
        }

        getWindow().setStatusBarColor(color);
        view.setSystemUiVisibility(flags);
    }

    public void showSnack(String string) {}
}
