package com.forcetower.playtime.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forcetower.playtime.utils.MockUtils;
import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.FragmentTitleListBinding;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.ui.adapter.TitleAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.forcetower.playtime.utils.PixelUtils.getPixelsFromDp;

public class TitleListFragment extends Fragment {
    private FragmentTitleListBinding binding;
    private TitleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title_list, container, false);
        setupRecyclerView();
        return binding.getRoot();
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0){
                    binding.toolbarInclude.toolbar.setElevation(0);
                } else {
                    binding.toolbarInclude.toolbar.setElevation(getPixelsFromDp(requireContext(), 6));
                }
            }
        });


        adapter = new TitleAdapter(new DiffUtil.ItemCallback<Title>() {
            @Override
            public boolean areItemsTheSame(@NonNull Title oldItem, @NonNull Title newItem) {
                return oldItem.getUid() == newItem.getUid();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Title oldItem, @NonNull Title newItem) {
                return oldItem.equals(newItem);
            }
        });

        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        populateAdapter(MockUtils.getTop());
        Picasso.with(requireContext()).load("https://avatars1.githubusercontent.com/u/9421614?s=460&v=4").into(binding.toolbarInclude.userImage);
    }

    private void populateAdapter(List<Title> data) {
        adapter.submitList(data);
    }
}
