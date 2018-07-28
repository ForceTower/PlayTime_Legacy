package com.forcetower.playtime.di.module;

import com.forcetower.playtime.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = AuthFragmentModule.class)
    abstract AuthActivity authActivity();
}