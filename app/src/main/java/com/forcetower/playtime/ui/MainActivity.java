package com.forcetower.playtime.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.ActivityMainBinding;
import com.forcetower.playtime.ui.fragments.RecommendationsFragment;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setLightStatusBar(binding.getRoot());

        Picasso.with(this).load("https://avatars1.githubusercontent.com/u/9421614?s=460&v=4").into(binding.userImage);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new RecommendationsFragment(), "recommendations")
                    .commit();
        }
    }

    public void setLightStatusBar(View view){
        int flags = view.getSystemUiVisibility();
        flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        view.setSystemUiVisibility(flags);
        getWindow().setStatusBarColor(Color.WHITE);
    }
}
