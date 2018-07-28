package com.forcetower.playtime.di.module;

import android.content.Context;

import com.forcetower.playtime.PlayApplication;

import dagger.Module;

@Module
public class AppModule {

    public Context provideContext(PlayApplication application) {
        return application.getApplicationContext();
    }
}
