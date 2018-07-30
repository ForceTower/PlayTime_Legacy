package com.forcetower.playtime.rep;

import com.forcetower.playtime.AppExecutors;
import com.forcetower.playtime.api.PlayService;
import com.forcetower.playtime.api.adapter.ApiResponse;
import com.forcetower.playtime.api.adapter.Resource;
import com.forcetower.playtime.db.PlayDatabase;
import com.forcetower.playtime.db.model.User;
import com.forcetower.playtime.rep.res.NetworkBoundResource;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

@Singleton
public class AccountRepository {
    private final PlayService service;
    private final PlayDatabase database;
    private final AppExecutors executors;

    @Inject
    public AccountRepository(PlayService service, PlayDatabase database, AppExecutors executors) {
        this.service = service;
        this.database = database;
        this.executors = executors;
    }

    public LiveData<Resource<User>> getMe() {
        return new NetworkBoundResource<User, User>(executors) {
            @Override
            protected void saveCallResult(@NonNull User item) {
                item.setMe(true);
                database.userDao().setAllNotMe();
                database.userDao().insert(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable User data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<User> loadFromDb() {
                return database.userDao().getMeUser();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<User>> createCall() {
                return service.me();
            }
        }.asLiveData();
    }
}
