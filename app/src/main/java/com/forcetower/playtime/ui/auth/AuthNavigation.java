package com.forcetower.playtime.ui.auth;

import android.os.Bundle;
import android.transition.Fade;

import com.forcetower.playtime.ui.BaseNavigation;
import com.forcetower.playtime.ui.fragments.LandingPageFragment;
import com.forcetower.playtime.ui.fragments.LoginFragment;
import com.forcetower.playtime.ui.fragments.SigningInFragment;
import com.forcetower.playtime.ui.fragments.SplashFragment;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;

public class AuthNavigation extends BaseNavigation {

    @Inject
    public AuthNavigation(AuthActivity activity) {
        super(activity);
    }

    public void loading() {
        switchFragment(new SplashFragment());
    }

    public void landing() {
        Fragment fragment = new LandingPageFragment();
        fragment.setEnterTransition(new Fade());
        switchFragment(fragment);
    }

    public void login() {
        Fragment fragment = new LoginFragment();
        fragment.setEnterTransition(new Fade(Fade.IN));
        switchFragment(fragment, true, "login_fragment");
    }

    public void connect(String username, String password) {
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putString("password", password);
        Fragment fragment = new SigningInFragment();
        fragment.setEnterTransition(new Fade(Fade.IN));

        switchFragment(fragment, true, "connecting", bundle, null);
    }
}
