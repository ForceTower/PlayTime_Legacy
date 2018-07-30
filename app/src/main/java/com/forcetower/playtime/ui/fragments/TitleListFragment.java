package com.forcetower.playtime.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forcetower.playtime.ui.NavigationFragment;
import com.forcetower.playtime.ui.adapter.TitleDiff;
import com.forcetower.playtime.utils.MockUtils;
import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.FragmentTitleListBinding;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.ui.adapter.TitleAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

import static com.forcetower.playtime.utils.PixelUtils.getPixelsFromDp;

public class TitleListFragment extends NavigationFragment {
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
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0){
                    getToolbar().setElevation(0);
                } else {
                    getToolbar().setElevation(getPixelsFromDp(requireContext(), 6));
                }
            }
        });

        adapter = new TitleAdapter(new TitleDiff());
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            int source = arguments.getInt("source");
            Timber.d("Data Source Enum is: " + source);
            populateAdapter(MockUtils.getTop());
        } else {
            Timber.e("No arguments");
        }
    }

    private void populateAdapter(List<Title> data) {
        adapter.submitList(data);
    }
}
