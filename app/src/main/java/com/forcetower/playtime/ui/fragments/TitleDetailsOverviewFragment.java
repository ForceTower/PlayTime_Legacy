package com.forcetower.playtime.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.api.adapter.Resource;
import com.forcetower.playtime.databinding.FragmentTitleDetailsOverallBinding;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.di.Injectable;
import com.forcetower.playtime.utils.MockUtils;
import com.forcetower.playtime.vm.PlayViewModelFactory;
import com.forcetower.playtime.vm.TitleViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import timber.log.Timber;

public class TitleDetailsOverviewFragment extends Fragment implements Injectable {
    @Inject
    PlayViewModelFactory viewModelFactory;

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
            boolean isMovie = arguments.getBoolean("is_movie");
            TitleViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(TitleViewModel.class);
            viewModel.getTitleFromDatabase(titleId, isMovie).observe(this, this::populateInterface);
            viewModel.getTitleRating(titleId, isMovie).observe(this, this::onMovieClassificationUpdate);
        } else {
            Timber.e("No arguments");
        }
    }

    private void onMovieClassificationUpdate(Resource<Title> resource) {
        Timber.d("Classification Resource: " + resource.status);
    }

    private void populateInterface(Title title) {
        binding.setTitle(title);
    }
}
