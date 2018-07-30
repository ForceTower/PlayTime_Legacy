package com.forcetower.playtime.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.FragmentTitleAlikeBinding;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.ui.TitleClickListener;
import com.forcetower.playtime.ui.TitleDetailsActivity;
import com.forcetower.playtime.ui.adapter.AlikeAdapter;
import com.forcetower.playtime.ui.widget.DividerItemDecorator;
import com.forcetower.playtime.utils.MockUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import timber.log.Timber;

public class TitleAlikeFragment extends Fragment {
    private FragmentTitleAlikeBinding binding;
    private AlikeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title_alike, container, false);
        prepareRecycler();
        return binding.getRoot();
    }

    private void prepareRecycler() {
        adapter = new AlikeAdapter();
        adapter.setTitleClickListener(titleClickListener);
        binding.alikeRecycler.setAdapter(adapter);
        binding.alikeRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.alikeRecycler.setItemAnimator(new DefaultItemAnimator());
        binding.alikeRecycler.addItemDecoration(new DividerItemDecorator(requireActivity(), R.drawable.divider));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            long titleId = arguments.getLong("title_id");
            populateInterface(MockUtils.getAlike());
        } else {
            Timber.e("Arguments are null");
        }
    }

    private void populateInterface(List<Title> data) {
        adapter.submitList(data);
    }

    private TitleClickListener titleClickListener = (title, position, view) -> {
        Intent intent = new Intent(requireContext(), TitleDetailsActivity.class);
        intent.putExtra("title_id", title.getUid());
        startActivity(intent);
    };
}
