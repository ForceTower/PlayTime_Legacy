package com.forcetower.playtime.api.tmdb;

import java.util.List;

public class VideoResults {
    private List<TitleVideo> results;

    public VideoResults(List<TitleVideo> results) {
        this.results = results;
    }

    public List<TitleVideo> getResults() {
        return results;
    }

    public void setResults(List<TitleVideo> results) {
        this.results = results;
    }
}
