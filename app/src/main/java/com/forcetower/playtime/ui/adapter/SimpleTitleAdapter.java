package com.forcetower.playtime.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.ItemTitleFavoriteBinding;
import com.forcetower.playtime.db.model.Title;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class SimpleTitleAdapter extends ListAdapter<Title, SimpleTitleAdapter.TitleHolder> {

    public SimpleTitleAdapter() {
        super(new TitleDiff());
    }

    @NonNull
    @Override
    public TitleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemTitleFavoriteBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_title_favorite,
                parent, false);
        return new TitleHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TitleHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class TitleHolder extends RecyclerView.ViewHolder {
        private final ItemTitleFavoriteBinding binding;

        TitleHolder(ItemTitleFavoriteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Title title) {
            binding.setTitle(title);
        }
    }
}
