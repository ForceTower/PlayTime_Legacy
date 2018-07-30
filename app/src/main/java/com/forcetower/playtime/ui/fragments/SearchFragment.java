package com.forcetower.playtime.ui.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arlib.floatingsearchview.util.view.SearchInputView;
import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.FragmentSearchBinding;
import com.forcetower.playtime.ui.NavigationFragment;
import com.forcetower.playtime.utils.AnimUtils;
import com.forcetower.playtime.utils.PixelUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import timber.log.Timber;

public class SearchFragment extends NavigationFragment {
    private FragmentSearchBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        getToolbar().setElevation(0);
        prepareSearchBar();
        prepareActions();
        return binding.getRoot();
    }

    private void prepareActions() {
        binding.categoryChips.setOnCheckedChangeListener((chipGroup, i) -> {
            Timber.d("Position i: " + i);
        });

        binding.releaseYearGroup.setOnCheckedChangeListener((chipGroup, i) -> {
            Timber.d("Year Selection i: " + i);
            if (i == -1) {
                AnimUtils.fadeOut(requireContext(), binding.etYear);
            } else {
                AnimUtils.fadeIn(requireContext(), binding.etYear);
            }
        });
    }

    private void prepareSearchBar() {
        CardView cardView = binding.searchView.findViewById(R.id.search_query_section);
        cardView.setRadius(PixelUtils.getPixelsFromDp(requireContext(), 8));

        SearchInputView inputView = binding.searchView.findViewById(R.id.search_bar_text);
        Typeface font = ResourcesCompat.getFont(requireContext(), R.font.product_sans_regular);
        inputView.setTypeface(font);
    }
}
