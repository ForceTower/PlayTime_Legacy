package com.forcetower.playtime.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.FragmentTitleSeasonsBinding;
import com.forcetower.playtime.db.model.TVSeason;
import com.forcetower.playtime.ui.adapter.SeasonsAdapter;
import com.forcetower.playtime.ui.widget.DividerItemDecorator;
import com.forcetower.playtime.utils.MockUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import timber.log.Timber;

public class SerieSeasonsFragment extends Fragment {
    private FragmentTitleSeasonsBinding binding;
    private SeasonsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title_seasons, container, false);
        prepareRecycler();
        return binding.getRoot();
    }

    private void prepareRecycler() {
        adapter = new SeasonsAdapter(new DiffUtil.ItemCallback<TVSeason>() {
            @Override
            public boolean areItemsTheSame(@NonNull TVSeason oldItem, @NonNull TVSeason newItem) {
                return oldItem.getUid() == newItem.getUid();
            }

            @Override
            public boolean areContentsTheSame(@NonNull TVSeason oldItem, @NonNull TVSeason newItem) {
                return oldItem.equals(newItem);
            }
        });
        binding.seasonsRecycler.setAdapter(adapter);
        binding.seasonsRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.seasonsRecycler.setItemAnimator(new DefaultItemAnimator());
        binding.seasonsRecycler.addItemDecoration(new DividerItemDecorator(requireContext(), R.drawable.divider));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            long titleId = arguments.getLong("title_id");
            populateInterface(MockUtils.getSeasons());
        } else {
            Timber.e("Arguments are null");
        }
    }

    private void populateInterface(List<TVSeason> seasons) {
        adapter.submitList(seasons);
    }
}
