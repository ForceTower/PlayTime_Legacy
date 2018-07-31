package com.forcetower.playtime.api.tmdb;

import com.forcetower.playtime.db.model.Title;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularResult {
    private int page;
    @SerializedName(value = "total_results")
    private int totalResults;
    @SerializedName(value = "total_pages")
    private int totalPages;
    private List<Title> results;

    public PopularResult(int page, int totalResults, int totalPages, List<Title> results) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Title> getResults() {
        return results;
    }

    public void setResults(List<Title> results) {
        this.results = results;
    }
}
