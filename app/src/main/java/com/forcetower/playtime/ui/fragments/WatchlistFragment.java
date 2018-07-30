package com.forcetower.playtime.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.FragmentWatchlistBinding;
import com.forcetower.playtime.db.relations.TitleWatchlist;
import com.forcetower.playtime.ui.NavigationFragment;
import com.forcetower.playtime.ui.TitleClickListener;
import com.forcetower.playtime.ui.TitleDetailsActivity;
import com.forcetower.playtime.ui.adapter.WatchlistAdapter;
import com.forcetower.playtime.utils.MockUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.forcetower.playtime.utils.PixelUtils.getPixelsFromDp;

public class WatchlistFragment extends NavigationFragment {
    private FragmentWatchlistBinding binding;
    private WatchlistAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_watchlist, container, false);
        setupRecycler();
        return binding.getRoot();
    }

    private void setupRecycler() {
        adapter = new WatchlistAdapter();
        adapter.setTitleClickListener(titleClickListener);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.watchlistRecycler.setAdapter(adapter);
        binding.watchlistRecycler.setLayoutManager(layoutManager);
        binding.watchlistRecycler.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        binding.watchlistRecycler.setItemAnimator(new DefaultItemAnimator());
        binding.watchlistRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0){
                    getToolbar().setElevation(0);
                } else {
                    getToolbar().setElevation(getPixelsFromDp(requireContext(), 6));
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        populateAdapter(MockUtils.getWatchlist());
    }

    private void populateAdapter(List<TitleWatchlist> data) {
        adapter.submitList(data);
    }

    private TitleClickListener titleClickListener = (title, position, view) -> {
        Intent intent = new Intent(requireContext(), TitleDetailsActivity.class);
        intent.putExtra("title_id", title.getUid());
        startActivity(intent);
    };
}
