package com.forcetower.playtime.ui;

import android.os.Bundle;

import com.forcetower.playtime.R;
import com.forcetower.playtime.api.adapter.Resource;
import com.forcetower.playtime.databinding.ActivityImagesBinding;
import com.forcetower.playtime.db.model.TitleImage;
import com.forcetower.playtime.ui.fragments.TitleImageFragment;
import com.forcetower.playtime.ui.widget.ZoomOutPageTransformer;
import com.forcetower.playtime.vm.PlayViewModelFactory;
import com.forcetower.playtime.vm.TitleViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ImagesActivity extends BaseActivity implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;
    @Inject
    PlayViewModelFactory viewModelFactory;

    private ActivityImagesBinding binding;
    private ImagePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_images);

        prepareViewPager();

        TitleViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(TitleViewModel.class);
        long titleId = getIntent().getLongExtra("title_id", -1);
        boolean isMovie = getIntent().getBooleanExtra("is_movie", true);
        if (titleId != -1) {
            viewModel.getTitleImages(titleId, isMovie).observe(this, this::onImagesChange);
        }
    }

    private void prepareViewPager() {
        adapter = new ImagePagerAdapter(getSupportFragmentManager());
        binding.viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        binding.viewPager.setAdapter(adapter);
    }

    private void onImagesChange(Resource<List<TitleImage>> resource) {
        if (resource.data != null) {
            adapter.setImages(resource.data);
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {
        private final List<TitleImage> images;

        private ImagePagerAdapter(FragmentManager fm) {
            super(fm);
            images = new ArrayList<>();
        }

        void setImages(List<TitleImage> items) {
            this.images.clear();
            this.images.addAll(items);
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            TitleImageFragment imageFragment = new TitleImageFragment();

            Bundle bundle = new Bundle();
            bundle.putString("url", images.get(position).getImage());
            imageFragment.setArguments(bundle);

            return imageFragment;
        }

        @Override
        public int getCount() {
            return images.size();
        }
    }
}
