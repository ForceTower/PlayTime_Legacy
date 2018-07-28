package com.forcetower.playtime.ui;

import android.os.Bundle;

import com.forcetower.playtime.R;

import androidx.databinding.DataBindingUtil;

public class TitleDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_title_details);
    }
}
