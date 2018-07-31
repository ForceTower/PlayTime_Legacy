package com.forcetower.playtime.api.tmdb;

import com.forcetower.playtime.db.model.TitleImage;

import java.util.ArrayList;
import java.util.List;

public class TitleImages {
    private List<TitleImage> backdrops;
    private List<TitleImage> posters;

    public List<TitleImage> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<TitleImage> backdrops) {
        this.backdrops = backdrops;
    }

    public List<TitleImage> getPosters() {
        return posters;
    }

    public void setPosters(List<TitleImage> posters) {
        this.posters = posters;
    }

    public List<TitleImage> getMergedList() {
        List<TitleImage> merged = new ArrayList<>();
        if (backdrops != null) merged.addAll(backdrops);
        if (posters != null) merged.addAll(posters);
        return merged;
    }
}
