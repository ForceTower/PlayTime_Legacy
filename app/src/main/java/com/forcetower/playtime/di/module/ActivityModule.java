package com.forcetower.playtime.di.module;

import com.forcetower.playtime.ui.MainActivity;
import com.forcetower.playtime.ui.TitleDetailsActivity;
import com.forcetower.playtime.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = AuthFragmentModule.class)
    abstract AuthActivity authActivity();
    @ContributesAndroidInjector(modules = MainFragmentModule.class)
    abstract MainActivity mainActivity();
    @ContributesAndroidInjector(modules = DetailsFragmentModule.class)
    abstract TitleDetailsActivity titleDetailsActivity();
}
