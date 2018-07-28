package com.forcetower.playtime.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.FragmentLoginBinding;
import com.forcetower.playtime.di.Injectable;
import com.forcetower.playtime.ui.BaseActivity;
import com.forcetower.playtime.ui.auth.AuthNavigation;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import timber.log.Timber;

public class LoginFragment extends Fragment implements Injectable {
    @Inject
    AuthNavigation navigation;

    private FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        ((BaseActivity)requireActivity()).setStatusBarColor(Color.WHITE);
        registerListeners();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void registerListeners() {
        binding.login.setOnClickListener(v -> onLogin());
        binding.facebookLogin.setOnClickListener(v -> onFacebook());
        binding.register.setOnClickListener(v -> onRegister());
        binding.help.setOnClickListener(v -> onHelp());
    }

    private void onRegister() {
        //TODO on Register
        Timber.d("Register");
    }

    private void onHelp() {
        //TODO On Help
        Timber.d("Help");
    }

    private void onFacebook() {
        //TODO On Facebook
        Timber.d("Facebook");
    }

    private void onLogin() {

    }
}
