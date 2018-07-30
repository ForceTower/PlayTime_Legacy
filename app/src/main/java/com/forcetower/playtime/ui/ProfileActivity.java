package com.forcetower.playtime.ui;

import android.graphics.Color;
import android.os.Bundle;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.ActivityProfileBinding;
import com.forcetower.playtime.db.relations.UserRelation;
import com.forcetower.playtime.ui.adapter.SimpleTitleAdapter;
import com.forcetower.playtime.ui.adapter.UserPostAdapter;
import com.forcetower.playtime.utils.MockUtils;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;

public class ProfileActivity extends BaseActivity {
    private ActivityProfileBinding binding;
    private SimpleTitleAdapter historyAdapter;
    private SimpleTitleAdapter favoriteAdapter;
    private UserPostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        prepareInterface();
        setStatusBarColor(Color.WHITE);
        populateInterface(MockUtils.getUser());
    }

    private void prepareInterface() {
        historyAdapter = new SimpleTitleAdapter();
        binding.historyRecycler.setAdapter(historyAdapter);
        binding.historyRecycler.setItemAnimator(new DefaultItemAnimator());

        favoriteAdapter = new SimpleTitleAdapter();
        binding.favoritesRecycler.setAdapter(favoriteAdapter);
        binding.favoritesRecycler.setItemAnimator(new DefaultItemAnimator());

        postAdapter = new UserPostAdapter();
        binding.commentsRecycler.setAdapter(postAdapter);
        binding.favoritesRecycler.setItemAnimator(new DefaultItemAnimator());

        binding.toolbar.setNavigationOnClickListener(v -> finishAfterTransition());
    }

    private void populateInterface(UserRelation user) {
        binding.setUser(user);
        historyAdapter.submitList(user.getHistory());
        favoriteAdapter.submitList(user.getFavorites());
        postAdapter.submitList(user.getComments());
    }
}
