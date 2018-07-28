package com.forcetower.playtime.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import timber.log.Timber;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.ActivityMainBinding;
import com.forcetower.playtime.ui.fragments.LandingPageFragment;
import com.forcetower.playtime.ui.fragments.RecommendationsFragment;
import com.forcetower.playtime.ui.fragments.TitleListFragment;
import com.squareup.picasso.Picasso;

import static com.forcetower.playtime.utils.ColorUtils.isColorDark;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setLightStatusBar(binding.getRoot());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new LandingPageFragment(), "recommendations")
                    .commit();
        }
    }

    public void setLightStatusBar(View view){
        int flags = view.getSystemUiVisibility();
        flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        view.setSystemUiVisibility(flags);
        getWindow().setStatusBarColor(Color.WHITE);
    }

    public void setStatusBarCor(int color) {
        View view = getWindow().getDecorView();
        int flags = view.getSystemUiVisibility();
        if (isColorDark(color)) {
            flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            Timber.d("Color is dark");
        } else {
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            Timber.d("Color is Light");
        }

        getWindow().setStatusBarColor(color);
        view.setSystemUiVisibility(flags);
    }

}
