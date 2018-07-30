package com.forcetower.playtime.vm;

import com.forcetower.playtime.api.adapter.Resource;
import com.forcetower.playtime.api.adapter.Status;
import com.forcetower.playtime.db.model.AccessToken;
import com.forcetower.playtime.rep.AuthRepository;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

public class AuthViewModel extends ViewModel {
    private final AuthRepository repository;
    private LiveData<AccessToken> accessSrc;
    private final MediatorLiveData<Resource<AccessToken>> loginSrc;
    private boolean connecting = false;

    @Inject
    public AuthViewModel(AuthRepository repository) {
        this.repository = repository;
        loginSrc = new MediatorLiveData<>();
    }

    public LiveData<AccessToken> getAccessToken() {
        if (accessSrc == null) accessSrc = repository.getAccessToken();
        return accessSrc;
    }

    public MediatorLiveData<Resource<AccessToken>> getLogin() {
        return loginSrc;
    }

    public void login(String username, String password) {
        if (!connecting) {
            connecting = true;
            LiveData<Resource<AccessToken>> login = repository.login(username, password);
            loginSrc.addSource(login, resource -> {
                if (resource.status != Status.LOADING) connecting = false;
                loginSrc.postValue(resource);
            });
        }
    }

    public void loginFacebook(String token, String userId) {

    }
}
