package com.forcetower.playtime.di.module;

import com.forcetower.playtime.ui.fragments.TitleListFragment;
import com.forcetower.playtime.ui.fragments.WatchlistFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@SuppressWarnings("WeakerAccess")
@Module
public abstract class MainFragmentModule {
    @ContributesAndroidInjector
    abstract TitleListFragment titleListFragment();
    @ContributesAndroidInjector
    abstract WatchlistFragment watchlistFragment();
}
