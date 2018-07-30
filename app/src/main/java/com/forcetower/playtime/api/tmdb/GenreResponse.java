package com.forcetower.playtime.api.tmdb;

import com.forcetower.playtime.db.model.Genre;

import java.util.List;

public class GenreResponse {
    private List<Genre> genres;

    public GenreResponse(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
