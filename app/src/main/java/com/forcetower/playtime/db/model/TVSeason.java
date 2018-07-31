package com.forcetower.playtime.db.model;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TVSeason {
    @PrimaryKey
    @SerializedName("id")
    private long uid;
    @SerializedName("season_number")
    private int number;
    private String name;
    private String overview;
    @SerializedName("air_date")
    private String airDate;
    @SerializedName("episode_count")
    private int episodeCount;
    @SerializedName("poster_path")
    private String image;
    @ColumnInfo(name = "title_id")
    private long titleId;

    public TVSeason(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TVSeason tvSeason = (TVSeason) o;

        if (uid != tvSeason.uid) return false;
        if (number != tvSeason.number) return false;
        if (episodeCount != tvSeason.episodeCount) return false;
        if (name != null ? !name.equals(tvSeason.name) : tvSeason.name != null) return false;
        if (overview != null ? !overview.equals(tvSeason.overview) : tvSeason.overview != null)
            return false;
        if (airDate != null ? !airDate.equals(tvSeason.airDate) : tvSeason.airDate != null)
            return false;
        return image != null ? image.equals(tvSeason.image) : tvSeason.image == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (uid ^ (uid >>> 32));
        result = 31 * result + number;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (overview != null ? overview.hashCode() : 0);
        result = 31 * result + (airDate != null ? airDate.hashCode() : 0);
        result = 31 * result + episodeCount;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    public long getTitleId() {
        return titleId;
    }

    public void setTitleId(long titleId) {
        this.titleId = titleId;
    }
}
