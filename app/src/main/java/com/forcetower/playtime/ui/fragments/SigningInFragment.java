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
import com.forcetower.playtime.api.adapter.Resource;
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
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import timber.log.Timber;

import static com.forcetower.playtime.utils.StringUtils.validString;

public class SigningInFragment extends NavigationFragment implements Injectable {
    @Inject
    PlayViewModelFactory viewModelFactory;

    private AuthViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_signing_in, container, false).getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BaseActivity activity = (BaseActivity) requireActivity();
        activity.setStatusBarColor(Color.WHITE);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthViewModel.class);
        viewModel.getAccessToken().observe(this, this::onReceiveToken);
        viewModel.getLogin().observe(this, this::onLoginProgress);

        Bundle arguments = getArguments();

        if (arguments == null) {
            activity.showSnack(getString(R.string.internal_error));
        } else {
            int loginType = arguments.getInt("login_type");
            switch (loginType) {
                case 0:
                    doLogin(arguments.getString("username"), arguments.getString("password"));
                    break;
                case 1:
                    doFacebook(arguments.getString("facebook_token"), arguments.getString("facebook_id"));
                    break;
            }
        }
    }

    private void doFacebook(String token, String userId) {
        if (!validString(token) || !validString(userId)) {
            showSnack(getString(R.string.invalid_login));
            requireNavigation().popBackStack();
        } else {
            viewModel.loginFacebook(token, userId);
        }
    }

    private void onLoginProgress(Resource<AccessToken> resource) {
        switch (resource.status) {
            case SUCCESS:
                Timber.d("Login Completed");
                break;
            case ERROR:
                Timber.e("Login failed");
                Timber.e("Resource status: " + resource.message + " --> " + resource.code);
                if (resource.code != 401) showSnack(getString(R.string.login_failed));
                else showSnack(getString(R.string.invalid_credentials));
                requireNavigation().popBackStack();
                break;
            case LOADING:
                Timber.i("Loading request");
                break;
        }
    }

    private void onReceiveToken(AccessToken accessToken) {
        if (accessToken != null) connected();
    }

    private void doLogin(String username, String password) {
        if (!validString(username) || !validString(password)) {
            showSnack(getString(R.string.invalid_login));
            requireNavigation().popBackStack();
        } else {
            viewModel.login(username, password);
        }
    }

    private void connected() {
        Intent intent = new Intent(requireContext(), MainActivity.class);
        startActivity(intent);

        requireActivity().finish();
    }

}
