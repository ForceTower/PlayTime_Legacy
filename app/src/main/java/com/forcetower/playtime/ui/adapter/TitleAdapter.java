package com.forcetower.playtime.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.ItemTitleListBinding;
import com.forcetower.playtime.db.model.Title;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class TitleAdapter extends PagedListAdapter<Title, TitleAdapter.TitleHolder> {

    public TitleAdapter(@NonNull DiffUtil.ItemCallback<Title> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public TitleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTitleListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_title_list, parent, false);
        return new TitleHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TitleHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class TitleHolder extends RecyclerView.ViewHolder {
        private final ItemTitleListBinding binding;

        TitleHolder(ItemTitleListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Title title) {
            this.binding.setTitle(title);
        }
    }
}
