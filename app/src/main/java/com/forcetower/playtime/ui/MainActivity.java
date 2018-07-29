package com.forcetower.playtime.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.ActivityMainBinding;
import com.forcetower.playtime.ui.fragments.TitleListFragment;
import com.squareup.picasso.Picasso;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import timber.log.Timber;

import static com.forcetower.playtime.utils.ColorUtils.isColorDark;

public class MainActivity extends BaseActivity implements ToolbarActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.scale_up_and_alpha, R.anim.scale_down_and_alpha);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setStatusBarColor(Color.WHITE);
        Picasso.with(this)
                .load("https://avatars1.githubusercontent.com/u/9421614?s=460&v=4")
                .into(binding.toolbarInclude.userImage);

        NavigationUI.setupWithNavController(binding.bottomNavigation, getNavController());
    }

    @Override
    public boolean onSupportNavigateUp() {
        return getNavController().navigateUp();
    }

    @Override
    public Toolbar getToolbar() {
        return binding.toolbarInclude.toolbar;
    }
}
