package com.forcetower.playtime.di.module;

import com.forcetower.playtime.ui.fragments.LandingPageFragment;
import com.forcetower.playtime.ui.fragments.LoginFragment;
import com.forcetower.playtime.ui.fragments.SplashFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AuthFragmentModule {
    @ContributesAndroidInjector
    abstract SplashFragment splashFragment();
    @ContributesAndroidInjector
    abstract LandingPageFragment landingPageFragment();
    @ContributesAndroidInjector
    abstract LoginFragment loginFragment();
}
