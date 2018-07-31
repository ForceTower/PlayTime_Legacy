package com.forcetower.playtime.api.tmdb;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Certification {
    @SerializedName(value = "iso_3166_1")
    private String iso;
    @SerializedName(value = "release_dates")
    private List<ReleaseDate> releaseDates;
    private String rating;

    public class ReleaseDate {
        public String certification;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public List<ReleaseDate> getReleaseDates() {
        return releaseDates;
    }

    public void setReleaseDates(List<ReleaseDate> releaseDates) {
        this.releaseDates = releaseDates;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
