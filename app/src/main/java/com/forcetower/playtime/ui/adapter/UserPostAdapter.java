package com.forcetower.playtime.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.ItemUserPostsBinding;
import com.forcetower.playtime.db.relations.TitleComment;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class UserPostAdapter extends ListAdapter<TitleComment, UserPostAdapter.CommentHolder> {

    public UserPostAdapter() {
        super(new DiffUtil.ItemCallback<TitleComment>() {
            @Override
            public boolean areItemsTheSame(@NonNull TitleComment oldItem, @NonNull TitleComment newItem) {
                return oldItem.getUid() == newItem.getUid();
            }

            @Override
            public boolean areContentsTheSame(@NonNull TitleComment oldItem, @NonNull TitleComment newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemUserPostsBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_user_posts,
                parent, false);
        return new CommentHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class CommentHolder extends RecyclerView.ViewHolder {
        private final ItemUserPostsBinding binding;

        CommentHolder(ItemUserPostsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(TitleComment comment) {
            binding.setPost(comment);
        }
    }
}
