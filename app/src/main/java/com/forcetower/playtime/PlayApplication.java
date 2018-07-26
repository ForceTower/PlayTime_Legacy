package com.forcetower.playtime;

import android.app.Application;

import com.squareup.picasso.Picasso;

import timber.log.Timber;

public class PlayApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());

        Picasso.Builder builder = new Picasso.Builder(this);
        Picasso.setSingletonInstance(builder.build());
    }
}
