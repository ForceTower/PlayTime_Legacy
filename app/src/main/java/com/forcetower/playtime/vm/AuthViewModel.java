package com.forcetower.playtime.vm;

import com.forcetower.playtime.api.adapter.Resource;
import com.forcetower.playtime.db.model.AccessToken;
import com.forcetower.playtime.rep.AuthRepository;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class AuthViewModel extends ViewModel {
    private final AuthRepository repository;
    private LiveData<AccessToken> accessSrc;

    @Inject
    public AuthViewModel(AuthRepository repository) {
        this.repository = repository;
    }

    public LiveData<AccessToken> getAccessToken() {
        if (accessSrc == null) accessSrc = repository.getAccessToken();
        return accessSrc;
    }

    public LiveData<Resource<AccessToken>> login(String username, String password) {
        return repository.login(username, password);
    }
}