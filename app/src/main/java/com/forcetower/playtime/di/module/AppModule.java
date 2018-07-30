package com.forcetower.playtime.di.module;

import android.content.Context;

import com.forcetower.playtime.BuildConfig;
import com.forcetower.playtime.Constants;
import com.forcetower.playtime.PlayApplication;
import com.forcetower.playtime.api.PlayService;
import com.forcetower.playtime.api.adapter.LiveDataCallAdapterFactory;
import com.forcetower.playtime.db.PlayDatabase;
import com.forcetower.playtime.db.model.AccessToken;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module(includes = ViewModelModule.class)
public class AppModule {
    @Provides
    @Singleton
    Context provideContext(PlayApplication application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    PlayDatabase provideDatabase(PlayApplication application) {
        int value = (int) (Math.random() * 3500);
        String random = "play_database_" + value + ".db";
        Timber.d("Database: " + random);
        return Room.databaseBuilder(application, PlayDatabase.class, BuildConfig.DEBUG ? random : "play_database.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    PlayService providePlayService(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(Constants.PLAY_TIME_SERVICE_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(client)
                .build()
                .create(PlayService.class);
    }

    @Singleton
    @Provides
    OkHttpClient provideHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .followRedirects(true)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    Interceptor provideInterceptor(PlayDatabase database) {
        return chain -> {
            Request oRequest = chain.request();
            if (oRequest.url().host().contains(Constants.PLAY_TIME_URL)) {
                Headers.Builder builder = oRequest.headers().newBuilder()
                        .add("Accept", "application/json");

                AccessToken token = database.accessTokenDao().getAccessTokenDirect();
                if (token != null && token.isValid()) {
                    builder.add("Authorization", token.getTokenType() + " " + token.getAccessToken());
                }

                Headers newHeaders = builder.build();
                Request request = oRequest.newBuilder().headers(newHeaders).build();

                return chain.proceed(request);
            }

            return chain.proceed(oRequest);
        };
    }

}
