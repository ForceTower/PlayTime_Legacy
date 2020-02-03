package com.forcetower.playtime.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.forcetower.playtime.R;
import com.forcetower.playtime.api.adapter.Resource;
import com.forcetower.playtime.databinding.ActivityMainBinding;
import com.forcetower.playtime.db.model.User;
import com.forcetower.playtime.vm.AccountViewModel;
import com.forcetower.playtime.vm.PlayViewModelFactory;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.ui.NavigationUI;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements ToolbarActivity, HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> fragmentInjector;
    @Inject
    PlayViewModelFactory viewModelFactory;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.scale_up_and_alpha, R.anim.scale_down_and_alpha);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setStatusBarColor(Color.WHITE);

        NavigationUI.setupWithNavController(binding.bottomNavigation, getNavController());
        binding.toolbarInclude.userImage.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    binding.toolbarInclude.userImage,
                    getString(R.string.user_image_transition)).toBundle();
            startActivity(intent, bundle);
        });

        prepareViewModel();
    }

    private void prepareViewModel() {
        AccountViewModel accountViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(AccountViewModel.class);
        accountViewModel.getMe().observe(this, this::onReceiveProfile);
    }

    private void onReceiveProfile(Resource<User> resource) {
        if (resource.data != null) {
            Timber.d("Connected as " + resource.data.getName());
            String image = resource.data.getImage();
            Picasso.get().load(image).into(binding.toolbarInclude.userImage);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return getNavController().navigateUp();
    }

    @Override
    public Toolbar getToolbar() {
        return binding.toolbarInclude.toolbar;
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return fragmentInjector;
    }
}
