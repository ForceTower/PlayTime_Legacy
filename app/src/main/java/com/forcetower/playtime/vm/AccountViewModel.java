package com.forcetower.playtime.vm;

import com.forcetower.playtime.api.adapter.Resource;
import com.forcetower.playtime.db.model.User;
import com.forcetower.playtime.rep.AccountRepository;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class AccountViewModel extends ViewModel {
    private final AccountRepository repository;
    private LiveData<Resource<User>> meSrc;

    @Inject
    public AccountViewModel(AccountRepository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<User>> getMe() {
        if (meSrc == null) meSrc = repository.getMe();
        return meSrc;
    }
}
