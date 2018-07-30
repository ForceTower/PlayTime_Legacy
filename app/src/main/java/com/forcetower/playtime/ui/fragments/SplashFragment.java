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
import com.forcetower.playtime.di.Injectable;
import com.forcetower.playtime.ui.BaseActivity;
import com.forcetower.playtime.ui.MainActivity;
import com.forcetower.playtime.ui.NavigationFragment;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SplashFragment extends NavigationFragment implements Injectable {

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
        checkAccess();
    }

    private void checkAccess() {
        //TODO Check real access
        new Handler(Looper.getMainLooper()).postDelayed(this::login, 2500);
    }

    private void login() {
        requireNavigation().navigate(R.id.action_splash_landing);
    }

    private void connected() {
        Intent intent = new Intent(requireContext(), MainActivity.class);
        startActivity(intent);
    }
}
