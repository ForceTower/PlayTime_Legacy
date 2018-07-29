package com.forcetower.playtime.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forcetower.playtime.ui.NavigationFragment;
import com.forcetower.playtime.utils.MockUtils;
import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.FragmentRecommendationsBinding;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.ui.adapter.RecommendationsAdapter;
import com.squareup.picasso.Picasso;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.SwipeDirection;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import timber.log.Timber;

public class RecommendationsFragment extends NavigationFragment {
    private FragmentRecommendationsBinding binding;
    private RecommendationsAdapter adapter;

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
                    updateInterfaceInformation(adapter.getItem(index));
                } else {
                    updateInterfaceInformation(null);
                }

                switch (direction) {
                    case Right:
                        Timber.d("Add to Watch Later");
                        break;
                    case Left:
                        Timber.d("Don't like this movie");
                        break;
                    case Top:
                        Timber.d("Already seen this");
                        break;
                }
            }

            @Override
            public void onCardReversed() { }

            @Override
            public void onCardMovedToOrigin() { }

            @Override
            public void onCardClicked(int index) {
                Title item = adapter.getItem(index);
                if (item != null)
                Timber.d("Item clicked: " + item);
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
        if (savedInstanceState == null) {
            List<Title> titles = MockUtils.getAll();
            fillAdapter(titles);
            if (!titles.isEmpty()) updateInterfaceInformation(titles.get(0));
            else updateInterfaceInformation(null);
        }
    }

    private void fillAdapter(List<Title> data) {
        adapter.clear();
        adapter.addAll(data);
    }
}
