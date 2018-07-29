package com.forcetower.playtime.db.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Title {
    @PrimaryKey(autoGenerate = true)
    private long uid;
    private String name;
    private String image;
    private String trailer;
    private float rating;
    private String releaseDate;
    private String description;
    private String genres;
    private int watchCount;

    public Title(String name, String image, String trailer, float rating, String releaseDate) {
        this.name = name;
        this.image = image;
        this.trailer = trailer;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public int getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(int watchCount) {
        this.watchCount = watchCount;
    }
}
