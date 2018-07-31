package com.forcetower.playtime.api.tmdb;

import java.util.List;

public class TitleCertification {
    private List<Certification> results;

    public List<Certification> getResults() {
        return results;
    }

    public void setResults(List<Certification> results) {
        this.results = results;
    }
}
