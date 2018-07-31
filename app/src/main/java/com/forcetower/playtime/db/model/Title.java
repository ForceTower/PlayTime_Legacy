package com.forcetower.playtime.db.model;

import com.forcetower.playtime.api.tmdb.Company;
import com.forcetower.playtime.api.tmdb.Credits;
import com.forcetower.playtime.api.tmdb.VideoResults;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import timber.log.Timber;

@Entity
public class Title {
    @PrimaryKey
    @SerializedName(value = "id")
    private long uid;
    @SerializedName(value = "title", alternate = "name")
    private String name;
    @SerializedName(value = "poster_path")
    private String image;
    private String trailer;
    @SerializedName(value = "vote_average")
    private float rating;
    @SerializedName(value = "release_date", alternate = "first_air_date")
    private String releaseDate;
    @SerializedName(value = "overview")
    private String description;
    @SerializedName(value = "ignored_genres")
    private String genres;
    private int watchCount;
    private String classification;
    private int runtime;
    @SerializedName(value = "backdrop_path")
    private String imageHorizontal;
    private boolean movie;
    private String thumbnail;
    private int likes;
    private int colorPalette;
    private int budget;
    private String homepage;
    @SerializedName(value = "imdb_id")
    private String imdbId;
    private String status;
    private String tagline;
    private boolean hasColorPalette;

    @Ignore
    @SerializedName(value = "episode_run_time")
    private List<Integer> episodeRunTime;

    @Ignore
    @SerializedName(value = "genre_ids")
    private List<Integer> genreIds;

    @Ignore
    @SerializedName(value = "genres")
    private List<Genre> genreList;

    @Ignore
    @SerializedName(value = "production_companies")
    private List<Company> companies;

    @Ignore
    private VideoResults videos;

    @Ignore
    private Credits credits;

    @Ignore
    private List<TVSeason> seasons;

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

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getImageHorizontal() {
        return imageHorizontal;
    }

    public void setImageHorizontal(String imageHorizontal) {
        this.imageHorizontal = imageHorizontal;
    }

    public boolean isMovie() {
        return movie;
    }

    public void setMovie(boolean movie) {
        this.movie = movie;
    }

    public String getThumbnail() {
        if (thumbnail == null && trailer != null) {
            boolean hasV = trailer.contains("v=");
            String videoId;
            if (hasV) {
                int index = trailer.lastIndexOf("v=");
                videoId = trailer.substring(index + 2);
            } else {
                int index = trailer.lastIndexOf("/");
                videoId = trailer.substring(index + 1);
            }

            Timber.d("Video ID: " + videoId);
            boolean hasSlash = videoId.endsWith("/");
            this.thumbnail = "http://img.youtube.com/vi/" + videoId + (hasSlash ? "" : "/") + "0.jpg";
        }
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    public void setColorPalette(int colorPalette) {
        this.colorPalette = colorPalette;
    }

    public int getColorPalette() {
        return colorPalette;
    }

    public void setHasColorPalette(boolean hasColorPalette) {
        this.hasColorPalette = hasColorPalette;
    }

    public boolean getHasColorPalette() {
        return hasColorPalette;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    @Override
    public String toString() {
        return getName();
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public VideoResults getVideos() {
        return videos;
    }

    public void setVideos(VideoResults videos) {
        this.videos = videos;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public List<Integer> getEpisodeRunTime() {
        return episodeRunTime;
    }

    public void setEpisodeRunTime(List<Integer> episodeRunTime) {
        this.episodeRunTime = episodeRunTime;
    }

    public List<TVSeason> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<TVSeason> seasons) {
        this.seasons = seasons;
    }
}
