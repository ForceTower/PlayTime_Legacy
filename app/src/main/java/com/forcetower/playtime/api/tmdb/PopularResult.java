package com.forcetower.playtime.api.tmdb;

import com.google.gson.annotations.SerializedName;

public class PopularResult {
    private int page;
    @SerializedName(value = "total_results")
    private int totalResults;
    @SerializedName(value = "total_pages")
    private int totalPages;
}
