package com.forcetower.playtime.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.FragmentTitleDetailsOverallBinding;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.utils.MockUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import timber.log.Timber;

public class TitleDetailsOverviewFragment extends Fragment {
    private FragmentTitleDetailsOverallBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title_details_overall,
                container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            long titleId = arguments.getLong("title_id");
            populateInterface(MockUtils.getTitle());
        } else {
            Timber.e("No arguments");
        }
    }

    private void populateInterface(Title title) {
        binding.setTitle(title);
    }
}
