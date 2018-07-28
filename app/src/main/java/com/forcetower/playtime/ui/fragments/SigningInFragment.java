package com.forcetower.playtime.ui.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.di.Injectable;
import com.forcetower.playtime.ui.BaseActivity;
import com.forcetower.playtime.ui.MainActivity;
import com.forcetower.playtime.ui.auth.AuthNavigation;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import static com.forcetower.playtime.utils.StringUtils.validString;

public class SigningInFragment extends Fragment implements Injectable {
    @Inject
    AuthNavigation navigation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_signing_in, container, false).getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle arguments = getArguments();
        BaseActivity activity = (BaseActivity) requireActivity();
        activity.setStatusBarColor(Color.WHITE);

        if (arguments == null) {
            activity.showSnack(getString(R.string.internal_error));
        } else {
            doLogin(arguments.getString("username"), arguments.getString("password"));
        }
    }

    private void doLogin(String username, String password) {
        if (!validString(username) || !validString(password)) {
            ((BaseActivity) requireActivity()).showSnack(getString(R.string.invalid_login));
            navigation.popBackStack();
        } else {
            new Handler(Looper.getMainLooper()).postDelayed(this::connected, 4000);
        }
    }

    private void connected() {
        navigation.startActivity(requireContext(), MainActivity.class);
        requireActivity().finish();
    }

}
