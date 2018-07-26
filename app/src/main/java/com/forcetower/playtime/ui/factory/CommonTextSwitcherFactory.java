package com.forcetower.playtime.ui.factory;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class CommonTextSwitcherFactory implements ViewSwitcher.ViewFactory {
    private final Context context;

    public CommonTextSwitcherFactory(Context context) {
        this.context = context;
    }

    @Override
    public View makeView() {
        return new TextView(context);
    }
}
