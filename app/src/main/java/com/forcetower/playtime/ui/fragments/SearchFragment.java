package com.forcetower.playtime.ui.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arlib.floatingsearchview.util.view.SearchInputView;
import com.forcetower.playtime.R;
import com.forcetower.playtime.databinding.FragmentSearchBinding;
import com.forcetower.playtime.db.model.Genre;
import com.forcetower.playtime.ui.NavigationFragment;
import com.forcetower.playtime.utils.AnimUtils;
import com.forcetower.playtime.utils.MockUtils;
import com.forcetower.playtime.utils.PixelUtils;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
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

        binding.submitButton.setOnClickListener(v -> {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < binding.categoryChips.getChildCount(); i++) {
                Chip chip = (Chip) binding.categoryChips.getChildAt(i);
                if (chip.isChecked()) builder.append(chip.getId()).append(";");
            }

            String str = builder.toString();
            if (str.endsWith(";")) str = str.substring(0, str.length() - 1);
            Timber.d("ID's: " + str);

            Bundle bundle = new Bundle();
            bundle.putString("genre_ids", str);

            int checkedChipId = binding.releaseYearGroup.getCheckedChipId();
            if (checkedChipId != -1) {
                String year = binding.etYear.getText().toString();
                switch (checkedChipId) {
                    case R.id.chip_equal_to:
                        year += ";eq";
                        break;
                    case R.id.chip_greater_than:
                        year += ";gte";
                        break;
                    case R.id.chip_smaller_than:
                        year += ";sme";
                        break;
                }

                bundle.putString("year", year);
                Timber.d("Year: " + year);
            }

            requireNavigation().navigate(R.id.action_to_result_page, bundle);
        });
    }

    private void prepareSearchBar() {
        CardView cardView = binding.searchView.findViewById(R.id.search_query_section);
        cardView.setRadius(PixelUtils.getPixelsFromDp(requireContext(), 8));

        SearchInputView inputView = binding.searchView.findViewById(R.id.search_bar_text);
        Typeface font = ResourcesCompat.getFont(requireContext(), R.font.product_sans_regular);
        inputView.setTypeface(font);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        populateChips(MockUtils.getGenres());
    }

    private void populateChips(List<Genre> genres) {
        binding.categoryChips.removeAllViews();
        for (Genre genre : genres) {
            createChip(genre);
        }
    }

    private void createChip(Genre genre) {
        Chip chip = new Chip(requireContext());
        chip.setChipBackgroundColorResource(R.color.white);
        chip.setChipStrokeColorResource(R.color.chip_color);
        chip.setRippleColorResource(R.color.chip_ripple);
        chip.setChipStrokeWidth(PixelUtils.getPixelsFromDp(requireContext(), 1));
        chip.setCheckedIconEnabled(false);
        chip.setText(genre.getName());
        chip.setId(Long.valueOf(genre.getUid()).intValue());
        chip.setTextAppearance(R.style.AppTheme_Chips);
        chip.setCheckable(true);
        binding.categoryChips.addView(chip);
    }
}
