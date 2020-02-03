package com.forcetower.playtime.binding;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import androidx.databinding.BindingAdapter;

/**
 * Created by João Paulo on 15/06/2018.
 */
public class GeneralBindings {
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        if (url != null) {
            if (url.startsWith("/")) url = "https://image.tmdb.org/t/p/w780" + url;
            Picasso.get()
                    .load(url)
                    .into(imageView);
        }
    }
}
