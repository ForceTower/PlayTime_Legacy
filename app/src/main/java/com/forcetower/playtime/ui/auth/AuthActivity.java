package com.forcetower.playtime.ui.auth;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.ActivityAuthBinding;
import com.forcetower.playtime.ui.BaseActivity;
import com.forcetower.playtime.ui.fragments.SplashFragment;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import timber.log.Timber;

public class AuthActivity extends BaseActivity implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    private ActivityAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return getNavController().navigateUp();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }

    @Override
    public void showSnack(String string) {
        Snackbar snackbar = Snackbar.make(binding.snack, string, Snackbar.LENGTH_SHORT);
        snackbar.setAction(android.R.string.ok, view -> snackbar.dismiss());
        snackbar.setActionTextColor(getColor(R.color.colorAccent));

        Typeface font = ResourcesCompat.getFont(this, R.font.product_sans_regular);

        View snackbarView = snackbar.getView();
        TextView tv = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setTypeface(font);

        TextView at = snackbarView.findViewById(com.google.android.material.R.id.snackbar_action);
        at.setTypeface(font, Typeface.BOLD);

        snackbar.show();
    }
}
