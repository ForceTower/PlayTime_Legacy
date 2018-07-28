package com.forcetower.playtime.ui.auth;

import android.os.Bundle;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.ActivityAuthBinding;
import com.forcetower.playtime.ui.BaseActivity;
import com.forcetower.playtime.ui.fragments.SplashFragment;

import javax.inject.Inject;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class AuthActivity extends BaseActivity implements HasSupportFragmentInjector {
    @Inject
    AuthNavigation navigation;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    private ActivityAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth);

        if (savedInstanceState == null) {
            navigation.loading();
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
