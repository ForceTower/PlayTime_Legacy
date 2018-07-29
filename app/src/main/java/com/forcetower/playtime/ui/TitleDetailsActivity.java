package com.forcetower.playtime.ui;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.ActivityTitleDetailsBinding;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.ui.fragments.TitleDetailsOverviewFragment;
import com.forcetower.playtime.utils.AnimUtils;
import com.forcetower.playtime.utils.MockUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.palette.graphics.Palette;

public class TitleDetailsActivity extends BaseActivity {
    private ActivityTitleDetailsBinding binding;
    private TitleDetailsAdapter adapter;
    private UIAlphaFrame frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_title_details);
        frame = new UIAlphaFrame(binding.getRoot(), 250);

        setupViewPager();
        setupTitle(MockUtils.getTitle());
    }

    private void setupViewPager() {
        adapter = new TitleDetailsAdapter(getSupportFragmentManager());
        binding.detailsPager.setAdapter(adapter);
    }

    private void setupTitle(Title title) {
        binding.setTitle(title);
        prepareImageAndColors(title);
        populateViewPager(title);
    }

    private void populateViewPager(Title title) {
        boolean movie = title.isMovie();
        binding.totalPages.setText(getString(R.string.out_of_something, (movie ? 3 : 4)));
        binding.currentPage.setText(getString(R.string.details_current_page, 1));

        Bundle bundle = new Bundle();
        bundle.putLong("title_id", title.getUid());

        Fragment overview = new TitleDetailsOverviewFragment();
        overview.setArguments(bundle);

        adapter.submitList(Arrays.asList(overview));
    }

    private void prepareImageAndColors(Title title) {
        Glide.with(this).load(title.getImageHorizontal())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        AnimUtils.fadeIn(TitleDetailsActivity.this, binding.titleImageHorizontal);
                        if (title.getHasColorPalette()) {
                            int palette = title.getColorPalette();
                            frame.changeAlphaTo(palette);
                            setStatusBarColor(palette);
                        } else {
                            if (resource instanceof BitmapDrawable) {
                                Palette palette = Palette.from(((BitmapDrawable) resource).getBitmap()).generate();
                                int vibrant = palette.getVibrantColor(Color.WHITE);
                                frame.changeAlphaTo(vibrant);
                                setStatusBarColor(vibrant);
                            } else {
                                frame.changeAlphaTo(Color.BLACK);
                                setStatusBarColor(Color.BLACK);
                            }
                        }
                        return false;
                    }
                })
                .into(binding.titleImageHorizontal);
    }

    private class TitleDetailsAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragments;

        private TitleDetailsAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            fragments = new ArrayList<>();
        }

        void submitList(List<Fragment> fragments) {
            this.fragments.clear();
            this.fragments.addAll(fragments);
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
