package com.forcetower.playtime.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.FragmentTitleCastBinding;
import com.forcetower.playtime.db.model.Cast;
import com.forcetower.playtime.ui.adapter.CastAdapter;
import com.forcetower.playtime.ui.widget.DividerItemDecorator;
import com.forcetower.playtime.utils.MockUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import timber.log.Timber;

public class TitleCastFragment extends Fragment {
    private FragmentTitleCastBinding binding;
    private CastAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title_cast, container, false);
        prepareRecyclerView();
        return binding.getRoot();
    }

    private void prepareRecyclerView() {
        adapter = new CastAdapter(new DiffUtil.ItemCallback<Cast>() {
            @Override
            public boolean areItemsTheSame(@NonNull Cast oldItem, @NonNull Cast newItem) {
                return oldItem.getUid() == newItem.getUid();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Cast oldItem, @NonNull Cast newItem) {
                return oldItem.equals(newItem);
            }
        });
        binding.castRecycler.setAdapter(adapter);
        binding.castRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.castRecycler.setItemAnimator(new DefaultItemAnimator());
        binding.castRecycler.addItemDecoration(new DividerItemDecorator(requireContext(), R.drawable.divider));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            long titleId = arguments.getLong("title_id");
            populateInterface(MockUtils.getCast());
        } else {
            Timber.e("Arguments are null...");
        }
    }

    private void populateInterface(List<Cast> cast) {
        adapter.submitList(cast);
    }
}
