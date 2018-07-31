package com.forcetower.playtime.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.FragmentWatchlistBinding;
import com.forcetower.playtime.db.model.WatchlistItem;
import com.forcetower.playtime.db.relations.TitleWatchlist;
import com.forcetower.playtime.di.Injectable;
import com.forcetower.playtime.ui.NavigationFragment;
import com.forcetower.playtime.ui.TitleClickListener;
import com.forcetower.playtime.ui.TitleDetailsActivity;
import com.forcetower.playtime.ui.adapter.WatchlistAdapter;
import com.forcetower.playtime.ui.rv.SwipeToDeleteCallback;
import com.forcetower.playtime.utils.MockUtils;
import com.forcetower.playtime.vm.PlayViewModelFactory;
import com.forcetower.playtime.vm.TitleViewModel;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.forcetower.playtime.utils.PixelUtils.getPixelsFromDp;

public class WatchlistFragment extends NavigationFragment implements Injectable {
    @Inject
    PlayViewModelFactory viewModelFactory;

    private FragmentWatchlistBinding binding;
    private WatchlistAdapter adapter;
    private TitleViewModel viewModel;
    private List<TitleWatchlist> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_watchlist, container, false);
        setupRecycler();
        getToolbar().setElevation(0);
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
        swipeActionsSupport();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TitleViewModel.class);
        viewModel.getWatchlist().observe(this, this::populateAdapter);
    }

    private void populateAdapter(List<TitleWatchlist> data) {
        list = data;

        if (data.isEmpty()) {
            binding.watchlistRecycler.setVisibility(View.GONE);
            binding.layoutNoData.setVisibility(View.VISIBLE);
        } else {
            binding.watchlistRecycler.setVisibility(View.VISIBLE);
            binding.layoutNoData.setVisibility(View.GONE);
        }
        adapter.submitList(data);
    }

    private void swipeActionsSupport() {
        SwipeToDeleteCallback callback = new SwipeToDeleteCallback(requireContext()) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                try {
                    viewModel.markAsWatched(list.get(position));
                } catch (Exception e) { e.printStackTrace(); }
            }
        };

        new ItemTouchHelper(callback).attachToRecyclerView(binding.watchlistRecycler);
    }

    private TitleClickListener titleClickListener = (title, position, view) -> {
        Intent intent = new Intent(requireContext(), TitleDetailsActivity.class);
        intent.putExtra("title_id", title.getUid());
        intent.putExtra("is_movie", title.isMovie());
        startActivity(intent);
    };
}
