package com.forcetower.playtime.ui.auth;

import android.os.Bundle;

import com.forcetower.playtime.R;
import com.forcetower.playtime.api.adapter.Resource;
import com.forcetower.playtime.databinding.ActivityAuthBinding;
import com.forcetower.playtime.db.model.Genre;
import com.forcetower.playtime.ui.BaseActivity;
import com.forcetower.playtime.vm.PlayViewModelFactory;
import com.forcetower.playtime.vm.TitleViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import timber.log.Timber;

import static com.forcetower.playtime.utils.SnackbarHelper.configSnackbar;

public class AuthActivity extends BaseActivity implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> fragmentInjector;
    @Inject
    PlayViewModelFactory viewModelFactory;

    private ActivityAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth);

        TitleViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(TitleViewModel.class);
        viewModel.loadMovieGenres().observe(this, this::onGenresChanged);
        viewModel.loadSeriesGenres().observe(this, this::onGenresChanged);
    }

    private void onGenresChanged(Resource<List<Genre>> genres) {
        Timber.d("Genres Load Status: " + genres.status);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return getNavController().navigateUp();
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return fragmentInjector;
    }

    @Override
    public void showSnack(String string) {
        Snackbar snackbar = Snackbar.make(binding.snack, string, Snackbar.LENGTH_SHORT);
        snackbar.setAction(android.R.string.ok, view -> snackbar.dismiss());
        snackbar.setActionTextColor(getColor(R.color.colorAccent));

        configSnackbar(this, snackbar);
        snackbar.show();
    }
}
