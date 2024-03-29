package com.forcetower.playtime.di.module;

import com.forcetower.playtime.di.annotation.ViewModelKey;
import com.forcetower.playtime.vm.AccountViewModel;
import com.forcetower.playtime.vm.AuthViewModel;
import com.forcetower.playtime.vm.PlayViewModelFactory;
import com.forcetower.playtime.vm.TitleViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@SuppressWarnings("WeakerAccess")
@Module
public abstract class ViewModelModule {
    @Binds @IntoMap @ViewModelKey(AuthViewModel.class)
    abstract ViewModel bindLoginViewModel(AuthViewModel authViewModel);

    @Binds @IntoMap @ViewModelKey(AccountViewModel.class)
    abstract ViewModel bindAccountViewModel(AccountViewModel accountViewModel);

    @Binds @IntoMap @ViewModelKey(TitleViewModel.class)
    abstract ViewModel bindTitleViewModel(TitleViewModel titleViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(PlayViewModelFactory factory);
}
