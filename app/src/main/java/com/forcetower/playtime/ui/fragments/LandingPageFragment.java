package com.forcetower.playtime.ui.fragments;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.FragmentLandingPageBinding;
import com.forcetower.playtime.ui.MainActivity;
import com.forcetower.playtime.ui.UIAlphaFrame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;
import timber.log.Timber;

public class LandingPageFragment extends Fragment {

    private FragmentLandingPageBinding binding;
    private UIAlphaFrame frame;

    private String[] urls = {
            "https://image.tmdb.org/t/p/original/q2BrsPEztd0L1cueuFIZakHObl7.jpg",
            "https://image.tmdb.org/t/p/original/1oUVoqtAft3yU8PPHDRhsm0yR5T.jpg",
            "https://image.tmdb.org/t/p/original/9xDD53hohCMp5VoFtqNPfzTsnLJ.jpg",
            "https://image.tmdb.org/t/p/original/2L28gWqCahNRqAMd7WRm7FtQABh.jpg",
            "https://image.tmdb.org/t/p/original/yeRW8JhHqQznEdOUe97dLWnyeEq.jpg",
            "https://image.tmdb.org/t/p/original/oCBq1lqg9Q1PwvVediz8Lq7k6jj.jpg",
            "https://image.tmdb.org/t/p/original/v1QQKq8M0fWxMgSdGOX1aCv8qMB.jpg",
            "http://digitalspyuk.cdnds.net/16/11/768x384/landscape-1457913087-spiderman-ohhai-no-text.gif"
    };

    private boolean image = true;
    private int index = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_landing_page, container, false);
        frame = new UIAlphaFrame(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareGrid(urls[0]);
    }

    private void prepareGrid(String url) {
        Glide.with(this)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (resource instanceof BitmapDrawable) {
                            Palette palette = Palette.from(((BitmapDrawable) resource).getBitmap()).generate();
                            int vibrant  = palette.getVibrantColor(Color.WHITE);
                            frame.changeAlphaTo(vibrant);
                            setStatusBarColor(vibrant);

                            if (image) {
                                fade(binding.titleImage, binding.titleImageTrue);
                            } else {
                                fade(binding.titleImageTrue, binding.titleImage);
                            }

                            image = !image;
                            startRunner(++index);
                        } else {
                            frame.changeAlphaTo(Color.BLACK);
                            setStatusBarColor(Color.BLACK);
                            Timber.d("Not an bitmap drawable");
                            if (image) {
                                fade(binding.titleImage, binding.titleImageTrue);
                            } else {
                                fade(binding.titleImageTrue, binding.titleImage);
                            }

                            image = !image;
                            startRunner(++index);
                        }
                        return false;
                    }
                })
                .into(new DrawableImageViewTarget(image ? binding.titleImageTrue : binding.titleImage));
    }

    private void setStatusBarColor(int color) {
        ((MainActivity)requireActivity()).setStatusBarCor(color);
    }

    private void startRunner(int index) {
        new Handler(Looper.getMainLooper())
                .postDelayed(() -> {
                    int real = index % urls.length;
                    prepareGrid(urls[real]);
                }, 5000);
    }

    private void fade(ImageView v1, ImageView v2) {
        v1.animate().alpha(0f).setDuration(1000);
        v2.animate().alpha(1f).setDuration(1000);
    }
}
