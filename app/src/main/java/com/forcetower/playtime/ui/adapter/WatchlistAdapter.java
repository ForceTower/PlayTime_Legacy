package com.forcetower.playtime.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.ItemWatchlistBinding;
import com.forcetower.playtime.db.relations.TitleWatchlist;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class WatchlistAdapter extends ListAdapter<TitleWatchlist, WatchlistAdapter.TitleHolder> {

    public WatchlistAdapter() {
        super(new DiffUtil.ItemCallback<TitleWatchlist>() {
            @Override
            public boolean areItemsTheSame(@NonNull TitleWatchlist oldItem, @NonNull TitleWatchlist newItem) {
                return oldItem.getUid() == newItem.getUid();
            }

            @Override
            public boolean areContentsTheSame(@NonNull TitleWatchlist oldItem, @NonNull TitleWatchlist newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public TitleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemWatchlistBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_watchlist,
                parent, false);
        return new TitleHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TitleHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class TitleHolder extends RecyclerView.ViewHolder {
        private final ItemWatchlistBinding binding;

        TitleHolder(ItemWatchlistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(TitleWatchlist title) {
            binding.setWatchlist(title);
        }
    }
}
