package com.forcetower.playtime.ui;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import timber.log.Timber;

public abstract class NavigationFragment extends Fragment {
    private ToolbarActivity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activity = (ToolbarActivity) context;
        } catch (ClassCastException e) {
            Timber.w("The getToolbar() method will throw an NPE since the parent activity does not implement ToolbarActivity");
        }
    }

    protected NavController requireNavigation() {
        View view = getView();
        if (view != null) {
            return Navigation.findNavController(view);
        }
        throw new IllegalStateException("NavController was not found. The Fragment has no view attached to it");
    }

    public Toolbar getToolbar () {
        return activity.getToolbar();
    }
}
