package com.forcetower.playtime.di.component;

import com.bumptech.glide.module.AppGlideModule;
import com.forcetower.playtime.PlayApplication;
import com.forcetower.playtime.di.module.ActivityModule;
import com.forcetower.playtime.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityModule.class
})
public interface PlayComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        PlayComponent.Builder application(PlayApplication application);
        PlayComponent build();
    }

    void inject(PlayApplication application);
}
