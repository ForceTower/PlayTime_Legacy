package com.forcetower.playtime.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.ItemTitleCommentBinding;
import com.forcetower.playtime.db.model.Comment;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CommentAdapter extends ListAdapter<Comment, CommentAdapter.CommentHolder> {

    public CommentAdapter(@NonNull DiffUtil.ItemCallback<Comment> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemTitleCommentBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_title_comment,
                parent, false);
        return new CommentHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class CommentHolder extends RecyclerView.ViewHolder {
        private final ItemTitleCommentBinding binding;

        CommentHolder(ItemTitleCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Comment comment) {
            binding.setComment(comment);
        }
    }
}
