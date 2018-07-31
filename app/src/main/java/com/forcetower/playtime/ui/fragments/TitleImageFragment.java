package com.forcetower.playtime.ui.fragments;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.FragmentTitleImageBinding;
import com.forcetower.playtime.ui.BaseActivity;
import com.forcetower.playtime.ui.TitleDetailsActivity;
import com.forcetower.playtime.ui.UIAlphaFrame;
import com.forcetower.playtime.utils.AnimUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;

public class TitleImageFragment extends Fragment {
    private FragmentTitleImageBinding binding;
    private UIAlphaFrame frame;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title_image,
                container, false);
        frame = new UIAlphaFrame(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //noinspection ConstantConditions
        String url = getArguments().getString("url");
        bindImage(url);
    }

    private void bindImage(String url) {
        if (url == null) return;

        if (url.startsWith("/")) url = "https://image.tmdb.org/t/p/original" + url;
        Glide.with(this).load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        AnimUtils.fadeIn(requireContext(), binding.image);
                        AnimUtils.fadeOut(requireContext(), binding.progress);
                        BaseActivity activity = (BaseActivity) requireActivity();
                        if (resource instanceof BitmapDrawable) {
                            Palette palette = Palette.from(((BitmapDrawable) resource).getBitmap()).generate();
                            int vibrant = palette.getVibrantColor(Color.WHITE);
                            activity.setStatusBarColor(vibrant);
                            frame.changeAlphaTo(vibrant);
                        } else {
                            frame.changeAlphaTo(Color.BLACK);
                            activity.setStatusBarColor(Color.BLACK);
                        }
                        return false;
                    }
                })
                .into(binding.image);
    }
}
