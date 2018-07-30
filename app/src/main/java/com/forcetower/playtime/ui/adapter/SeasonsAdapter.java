package com.forcetower.playtime.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.ItemTitleSeasonBinding;
import com.forcetower.playtime.db.model.TVSeason;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class SeasonsAdapter extends ListAdapter<TVSeason, SeasonsAdapter.SeasonHolder> {
    public SeasonsAdapter(@NonNull DiffUtil.ItemCallback<TVSeason> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public SeasonsAdapter.SeasonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemTitleSeasonBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_title_season,
                parent, false);
        return new SeasonHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonsAdapter.SeasonHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class SeasonHolder extends RecyclerView.ViewHolder {
        private final ItemTitleSeasonBinding binding;

        SeasonHolder(ItemTitleSeasonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(TVSeason season) {
            binding.setSeason(season);
        }
    }
}
