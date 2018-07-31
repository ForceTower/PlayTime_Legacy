package com.forcetower.playtime.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.ItemTitleCardBinding;
import com.forcetower.playtime.db.model.Title;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

public class RecommendationsAdapter extends ArrayAdapter<Title> {

    public RecommendationsAdapter(Context context) {
        super(context, 0);
    }

    @NonNull
    @Override
    public View getView(int position, View contentView, @NonNull ViewGroup parent) {
        ItemTitleCardBinding binding;
        Title item = getItem(position);
        if (contentView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_title_card, parent, false);
            binding.setRecommendation(item);
            return binding.getRoot();
        } else {
            if (item != null) {
                ImageView imageView = contentView.findViewById(R.id.image);
                Picasso.with(imageView.getContext()).load(item.getImage()).into(imageView);
            }

            return contentView;
        }
    }

}
