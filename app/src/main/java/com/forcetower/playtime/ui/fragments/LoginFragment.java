package com.forcetower.playtime.ui.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.FragmentLoginBinding;
import com.forcetower.playtime.di.Injectable;
import com.forcetower.playtime.ui.BaseActivity;
import com.forcetower.playtime.ui.NavigationFragment;
import com.forcetower.playtime.vm.AuthViewModel;
import com.forcetower.playtime.vm.PlayViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import timber.log.Timber;

public class LoginFragment extends NavigationFragment implements Injectable {
    private FragmentLoginBinding binding;
    private CallbackManager facebookCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        ((BaseActivity)requireActivity()).setStatusBarColor(Color.WHITE);
        registerListeners();
        prepareFacebookFeatures();
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
        LoginManager.getInstance().setLoginBehavior(LoginBehavior.NATIVE_WITH_FALLBACK);
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "user_friends"));
    }

    private void onLogin() {
        String username = Objects.requireNonNull(binding.etEmail.getText()).toString();
        String password = Objects.requireNonNull(binding.etPassword.getText()).toString();

        Bundle bundle = new Bundle();
        bundle.putInt("login_type", 0);
        bundle.putString("username", username);
        bundle.putString("password", password);
        requireNavigation().navigate(R.id.action_login_connecting, bundle);
    }

    private void prepareFacebookFeatures() {
        facebookCallback = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(facebookCallback, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (!loginResult.getRecentlyDeniedPermissions().isEmpty()) {
                    showSnack(getString(R.string.you_need_to_allow_all_features));
                    LoginManager.getInstance().logOut();
                    return;
                }

                com.facebook.AccessToken fbToken = loginResult.getAccessToken();
                Timber.d("Facebook token: %s", fbToken.getToken());
                Timber.d("Facebook id: %s", fbToken.getUserId());

                Bundle bundle = new Bundle();
                bundle.putInt("login_type", 1);
                bundle.putString("facebook_token", fbToken.getToken());
                bundle.putString("facebook_id", fbToken.getUserId());
                requireNavigation().navigate(R.id.action_login_connecting, bundle);
            }

            @Override
            public void onCancel() {
                Timber.d("Facebook Login Canceled");
            }

            @Override
            public void onError(FacebookException error) {
                showSnack(getString(R.string.facebook_login_error));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookCallback.onActivityResult(requestCode, resultCode, data);
    }
}
