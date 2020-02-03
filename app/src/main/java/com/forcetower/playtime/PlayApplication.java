package com.forcetower.playtime;

import android.app.Activity;
import android.app.Application;

import com.forcetower.playtime.di.PlayInjector;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import timber.log.Timber;

public class PlayApplication extends Application implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());

        PlayInjector.init(this);

        Picasso.Builder builder = new Picasso.Builder(this);
        Picasso.setSingletonInstance(builder.build());
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return activityInjector;
    }
}
