package com.forcetower.playtime.ui.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.db.model.AccessToken;
import com.forcetower.playtime.di.Injectable;
import com.forcetower.playtime.ui.BaseActivity;
import com.forcetower.playtime.ui.MainActivity;
import com.forcetower.playtime.ui.NavigationFragment;
import com.forcetower.playtime.vm.AuthViewModel;
import com.forcetower.playtime.vm.PlayViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProviders;

public class SplashFragment extends NavigationFragment implements Injectable {
    @Inject
    PlayViewModelFactory viewModelFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setExitTransition(new Fade(Fade.OUT));
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((BaseActivity)requireActivity()).setStatusBarColor(Color.WHITE);
        AuthViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthViewModel.class);
        viewModel.getAccessToken().observe(this, this::onReceiveToken);
    }

    private void onReceiveToken(AccessToken accessToken) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                if (accessToken == null) {
                    login();
                } else {
                    connected();
                }
            }
        }, 1000);
    }

    private void login() {
        requireNavigation().navigate(R.id.action_splash_landing);
    }

    private void connected() {
        Intent intent = new Intent(requireContext(), MainActivity.class);
        startActivity(intent);
    }
}
