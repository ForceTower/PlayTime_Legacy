package com.forcetower.playtime.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.ItemTitleCastBinding;
import com.forcetower.playtime.db.model.Cast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CastAdapter extends ListAdapter<Cast, CastAdapter.CastHolder> {

    public CastAdapter(@NonNull DiffUtil.ItemCallback<Cast> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemTitleCastBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_title_cast,
                parent, false);
        return new CastHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CastHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class CastHolder extends RecyclerView.ViewHolder {
        private final ItemTitleCastBinding binding;

        CastHolder(ItemTitleCastBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Cast cast) {
            binding.setCast(cast);
        }
    }
}
