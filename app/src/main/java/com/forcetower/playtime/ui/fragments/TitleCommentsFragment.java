package com.forcetower.playtime.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.FragmentTitleCommentsBinding;
import com.forcetower.playtime.db.model.Comment;
import com.forcetower.playtime.ui.adapter.CommentAdapter;
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

public class TitleCommentsFragment extends Fragment {
    private FragmentTitleCommentsBinding binding;
    private CommentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title_comments, container, false);
        prepareRecycler();
        return binding.getRoot();
    }

    private void prepareRecycler() {
        adapter = new CommentAdapter(new DiffUtil.ItemCallback<Comment>() {
            @Override
            public boolean areItemsTheSame(@NonNull Comment oldItem, @NonNull Comment newItem) {
                return oldItem.getUid() == newItem.getUid();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Comment oldItem, @NonNull Comment newItem) {
                return oldItem.equals(newItem);
            }
        });
        binding.commentsRecycler.setAdapter(adapter);
        binding.commentsRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.commentsRecycler.addItemDecoration(new DividerItemDecorator(requireContext(), R.drawable.divider));
        binding.commentsRecycler.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            long titleId = arguments.getLong("title_id");
            populateInterface(MockUtils.getComments());
        } else {
            Timber.e("Arguments are null");
        }
    }

    private void populateInterface(List<Comment> comments) {
        adapter.submitList(comments);
    }
}
