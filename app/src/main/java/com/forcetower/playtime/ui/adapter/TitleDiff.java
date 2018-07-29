package com.forcetower.playtime.ui.adapter;

import com.forcetower.playtime.db.model.Title;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class TitleDiff extends DiffUtil.ItemCallback<Title> {
    @Override
    public boolean areItemsTheSame(@NonNull Title oldItem, @NonNull Title newItem) {
        return oldItem.getUid() == newItem.getUid();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Title oldItem, @NonNull Title newItem) {
        return oldItem.equals(newItem);
    }
}
