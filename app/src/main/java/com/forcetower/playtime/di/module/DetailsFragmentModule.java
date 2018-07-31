package com.forcetower.playtime.di.module;

import com.forcetower.playtime.ui.fragments.TitleCastFragment;
import com.forcetower.playtime.ui.fragments.TitleDetailsOverviewFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class DetailsFragmentModule {
    @ContributesAndroidInjector
    abstract TitleDetailsOverviewFragment titleDetailsOverviewFragment();
    @ContributesAndroidInjector
    abstract TitleCastFragment titleCastFragment();
}
