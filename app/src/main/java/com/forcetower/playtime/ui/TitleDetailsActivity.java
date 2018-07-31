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
import com.forcetower.playtime.api.adapter.Resource;
import com.forcetower.playtime.databinding.ActivityTitleDetailsBinding;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.ui.fragments.SeriesSeasonsFragment;
import com.forcetower.playtime.ui.fragments.TitleAlikeFragment;
import com.forcetower.playtime.ui.fragments.TitleCastFragment;
import com.forcetower.playtime.ui.fragments.TitleCommentsFragment;
import com.forcetower.playtime.ui.fragments.TitleDetailsOverviewFragment;
import com.forcetower.playtime.utils.AnimUtils;
import com.forcetower.playtime.vm.PlayViewModelFactory;
import com.forcetower.playtime.vm.TitleViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.palette.graphics.Palette;
import androidx.viewpager.widget.ViewPager;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import timber.log.Timber;

import static com.forcetower.playtime.utils.SnackbarHelper.configSnackbar;

public class TitleDetailsActivity extends BaseActivity implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;
    @Inject
    PlayViewModelFactory viewModelFactory;

    private ActivityTitleDetailsBinding binding;
    private TitleDetailsAdapter adapter;
    private UIAlphaFrame frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_title_details);
        frame = new UIAlphaFrame(binding.getRoot(), 250);

        prepareInterface();
        setupViewPager();

        TitleViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(TitleViewModel.class);
        long titleId = getIntent().getLongExtra("title_id", 0);
        boolean isMovie = getIntent().getBooleanExtra("is_movie", true);
        viewModel.getTitle(titleId, isMovie).observe(this, this::onTitleUpdate);
    }

    private void onTitleUpdate(Resource<Title> resource) {
        if (resource.data != null)
            setupTitle(resource.data);

        Timber.d("Resource Status: " + resource.status);
        Timber.d("Resource Code: " + resource.code);
        Timber.d("Resource message: " + resource.message);
    }

    private void prepareInterface() {
        binding.toolbar.setNavigationOnClickListener(v -> finishAfterTransition());
    }

    private void setupViewPager() {
        adapter = new TitleDetailsAdapter(getSupportFragmentManager());
        binding.detailsPager.setAdapter(adapter);
        binding.detailsPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.spacing_normal));
        binding.detailsPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                binding.currentPage.setText(getString(R.string.details_current_page, position + 1));
            }
        });
    }

    private void setupTitle(Title title) {
        binding.setTitle(title);
        prepareImageAndColors(title);
        populateViewPager(title);
    }

    private void populateViewPager(Title title) {
        boolean movie = title.isMovie();
        binding.totalPages.setText(getString(R.string.out_of_something, (movie ? 4 : 5)));
        binding.currentPage.setText(getString(R.string.details_current_page, 1));

        List<Fragment> fragments = new ArrayList<>();

        Bundle bundle = new Bundle();
        bundle.putLong("title_id", title.getUid());
        bundle.putBoolean("is_movie", title.isMovie());

        Fragment overview = new TitleDetailsOverviewFragment();
        overview.setArguments(bundle);
        fragments.add(overview);

        Fragment cast = new TitleCastFragment();
        cast.setArguments(bundle);
        fragments.add(cast);

        Fragment comments = new TitleCommentsFragment();
        comments.setArguments(bundle);
        fragments.add(comments);

        if (!movie) {
            Fragment seasons = new SeriesSeasonsFragment();
            seasons.setArguments(bundle);
            fragments.add(seasons);
        }

        Fragment alike = new TitleAlikeFragment();
        alike.setArguments(bundle);
        fragments.add(alike);

        adapter.submitList(fragments);
    }

    private void prepareImageAndColors(Title title) {
        String url = title.getImageHorizontal();
        if (url == null) url = title.getImage();
        if (url == null) return;

        if (url.startsWith("/")) url = "https://image.tmdb.org/t/p/w780" + url;
        Glide.with(this).load(url)
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

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
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

    @Override
    public void showSnack(String string) {
        Snackbar snackbar = Snackbar.make(binding.snack, string, Snackbar.LENGTH_SHORT);
        configSnackbar(this, snackbar);
        snackbar.show();
    }
}
