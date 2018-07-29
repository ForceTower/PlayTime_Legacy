package com.forcetower.playtime.ui;

import android.view.View;

import com.forcetower.playtime.db.model.Title;

import androidx.annotation.Nullable;

public interface TitleClickListener {
    void onTitleClick(Title title, int position, @Nullable View view);
}
