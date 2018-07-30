package com.forcetower.playtime.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.ItemAlikeBinding;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.ui.TitleClickListener;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class AlikeAdapter extends ListAdapter<Title, AlikeAdapter.TitleHolder> {
    private TitleClickListener titleClickListener;

    public AlikeAdapter() {
        super(new DiffUtil.ItemCallback<Title>() {
            @Override
            public boolean areItemsTheSame(@NonNull Title oldItem, @NonNull Title newItem) {
                return oldItem.getUid() == newItem.getUid();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Title oldItem, @NonNull Title newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public TitleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAlikeBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_alike,
                parent, false);
        return new TitleHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TitleHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public void setTitleClickListener(TitleClickListener titleClickListener) {
        this.titleClickListener = titleClickListener;
    }

    class TitleHolder extends RecyclerView.ViewHolder {
        private final ItemAlikeBinding binding;

        TitleHolder(ItemAlikeBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setOnClickListener(v -> onItemClick());
            this.binding = binding;
        }

        void bind(Title title) {
            binding.setTitle(title);
        }

        private void onItemClick() {
            int position = getAdapterPosition();
            Title item = getItem(position);
            if (titleClickListener != null) titleClickListener.onTitleClick(item, position, binding.titleImage);
        }
    }
}
