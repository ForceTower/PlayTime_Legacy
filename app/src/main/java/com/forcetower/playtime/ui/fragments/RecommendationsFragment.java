package com.forcetower.playtime.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forcetower.playtime.db.relations.TitleRecommendation;
import com.forcetower.playtime.di.Injectable;
import com.forcetower.playtime.ui.NavigationFragment;
import com.forcetower.playtime.ui.TitleClickListener;
import com.forcetower.playtime.ui.TitleDetailsActivity;
import com.forcetower.playtime.utils.MockUtils;
import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.FragmentRecommendationsBinding;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.ui.adapter.RecommendationsAdapter;
import com.forcetower.playtime.vm.PlayViewModelFactory;
import com.forcetower.playtime.vm.TitleViewModel;
import com.squareup.picasso.Picasso;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.SwipeDirection;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import timber.log.Timber;

public class RecommendationsFragment extends NavigationFragment implements Injectable {
    @Inject
    PlayViewModelFactory viewModelFactory;

    private FragmentRecommendationsBinding binding;
    private RecommendationsAdapter adapter;
    private TitleViewModel viewModel;
    private List<TitleRecommendation> recommendations;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recommendations, container, false);
        getToolbar().setElevation(0);
        setupRecommendations();
        return binding.getRoot();
    }

    private void setupRecommendations() {
        adapter = new RecommendationsAdapter(requireContext());
        binding.cardStack.setAdapter(adapter);
        binding.cardStack.setCardEventListener(new CardStackView.CardEventListener() {
            @Override
            public void onCardDragging(float percentX, float percentY) { }

            @Override
            public void onCardSwiped(SwipeDirection direction) {
                int index = binding.cardStack.getTopIndex();
                Timber.d("Swiped Index: " + index);


                if (adapter.getCount() > index) {
                    Title item = adapter.getItem(index);
                    updateInterfaceInformation(item);
                } else {
                    updateInterfaceInformation(null);
                }

                Title item = recommendations.get(index - 1);
                if (item != null) {
                    switch (direction) {
                        case Top:
                            Timber.d("Add Watch Later " + item.getName());
                            viewModel.removeFromRecommendations(item.getUid());
                            viewModel.markToWatchLater(item.getUid(), item.isMovie());
                            break;
                        case Left:
                            viewModel.removeFromRecommendations(item.getUid());
                            Timber.d("Don't like the movie " + item.getName());
                            break;
                        case Right:
                            Timber.d("Already seen " + item.getName());
                            viewModel.removeFromRecommendations(item.getUid());
                            viewModel.markAsWatched(item.getUid(), item.isMovie());
                            break;
                    }
                }
            }

            @Override
            public void onCardReversed() { }

            @Override
            public void onCardMovedToOrigin() { }

            @Override
            public void onCardClicked(int index) {
                Title item = adapter.getItem(index);
                if (item != null) {
                    Timber.d("Item clicked: " + item);
                    titleClickListener.onTitleClick(item, index, null);
                }
            }
        });
    }

    private void updateInterfaceInformation(Title item) {
        if (item != null) {
            binding.titleName.setText(item.getName());
            binding.titleLaunch.setText(getString(R.string.launched_at, item.getReleaseDate()));
        } else {
            binding.titleName.setText(getString(R.string.no_more_titles_for_you));
            binding.titleLaunch.setText("");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Timber.d("onActivityCreated()");
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TitleViewModel.class);
        viewModel.getMoviesLocalRecommendations().observe(this, this::onRecommendationsUpdate);
        if (savedInstanceState == null) {
            //List<Title> titles = MockUtils.getAll();
            //fillAdapter(titles);
            //if (!titles.isEmpty()) updateInterfaceInformation(titles.get(0));
            //else updateInterfaceInformation(null);
        }
    }

    private void onRecommendationsUpdate(List<TitleRecommendation> recommendations) {
        if (!recommendations.isEmpty()) {
            if (this.recommendations == null) {
                fillAdapter(recommendations);
                updateInterfaceInformation(recommendations.get(0));
                this.recommendations = recommendations;
            }
        } else {
            updateInterfaceInformation(null);
        }
    }

    private void fillAdapter(List<TitleRecommendation> data) {
        adapter.clear();
        adapter.addAll(data);
    }

    private TitleClickListener titleClickListener = (title, position, view) -> {
        Intent intent = new Intent(requireContext(), TitleDetailsActivity.class);
        intent.putExtra("title_id", title.getUid());
        intent.putExtra("is_movie", title.isMovie());
        startActivity(intent);
    };
}
