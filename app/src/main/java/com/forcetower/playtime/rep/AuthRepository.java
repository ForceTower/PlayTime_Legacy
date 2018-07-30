package com.forcetower.playtime.rep;

import com.forcetower.playtime.AppExecutors;
import com.forcetower.playtime.api.PlayService;
import com.forcetower.playtime.api.adapter.ApiResponse;
import com.forcetower.playtime.api.adapter.Resource;
import com.forcetower.playtime.db.PlayDatabase;
import com.forcetower.playtime.db.helpers.Credentials;
import com.forcetower.playtime.db.model.AccessToken;
import com.forcetower.playtime.rep.res.NetworkBoundResource;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

@Singleton
public class AuthRepository {
    private final PlayService service;
    private final PlayDatabase database;
    private final AppExecutors executors;

    @Inject
    public AuthRepository(PlayService service, PlayDatabase database, AppExecutors executors) {
        this.service = service;
        this.database = database;
        this.executors = executors;
    }

    public LiveData<Resource<AccessToken>> login(String username, String password) {
        return new NetworkBoundResource<AccessToken, AccessToken>(executors) {
            @Override
            protected void saveCallResult(@NonNull AccessToken item) {
                database.accessTokenDao().insert(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable AccessToken data) {
                return data == null || data.isTimeExpired();
            }

            @NonNull
            @Override
            protected LiveData<AccessToken> loadFromDb() {
                return database.accessTokenDao().getAccessToken();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<AccessToken>> createCall() {
                return service.login(new Credentials.LoginCredentials(username, password));
            }
        }.asLiveData();
    }

}
