package com.forcetower.playtime.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.ActivityMainBinding;
import com.forcetower.playtime.ui.fragments.TitleListFragment;

import androidx.databinding.DataBindingUtil;
import timber.log.Timber;

import static com.forcetower.playtime.utils.ColorUtils.isColorDark;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.scale_up_and_alpha, R.anim.scale_down_and_alpha);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setStatusBarColor(Color.WHITE);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new TitleListFragment(), "titles")
                    .commit();
        }
    }

}
