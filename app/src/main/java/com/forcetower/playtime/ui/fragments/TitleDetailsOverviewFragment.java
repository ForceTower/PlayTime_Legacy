package com.forcetower.playtime.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forcetower.playtime.R;
import com.forcetower.playtime.api.adapter.Resource;
import com.forcetower.playtime.databinding.FragmentTitleDetailsOverallBinding;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.db.model.WatchlistItem;
import com.forcetower.playtime.di.Injectable;
import com.forcetower.playtime.ui.ImagesActivity;
import com.forcetower.playtime.ui.NavigationFragment;
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

public class TitleDetailsOverviewFragment extends NavigationFragment implements Injectable {
    @Inject
    PlayViewModelFactory viewModelFactory;

    private FragmentTitleDetailsOverallBinding binding;
    private TitleViewModel viewModel;
    private long titleId;
    private boolean isMovie;
    private Title title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title_details_overall,
                container, false);

        binding.likeLayout.setOnClickListener(v -> onWatchLater());
        binding.likeLayout.setOnLongClickListener(v -> { onMarkWatched(); return true; });
        binding.posterLayout.setOnClickListener(v -> startImagesActivity());
        return binding.getRoot();
    }

    private void startImagesActivity() {
        Intent intent = new Intent(requireContext(), ImagesActivity.class);
        intent.putExtra("title_id", titleId);
        intent.putExtra("is_movie", isMovie);
        startActivity(intent);
    }

    private void onWatchLater() {
        viewModel.markToWatchLater(titleId, isMovie);
        showSnack(getString(R.string.title_added_to_watchlist, title.getName()));
    }

    private void onMarkWatched() {
        viewModel.markAsWatched(titleId, isMovie);
        showSnack(getString(R.string.title_marked_as_watched, title.getName()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            titleId = arguments.getLong("title_id");
            isMovie = arguments.getBoolean("is_movie");
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(TitleViewModel.class);
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
        this.title = title;
        binding.setTitle(title);
    }
}
