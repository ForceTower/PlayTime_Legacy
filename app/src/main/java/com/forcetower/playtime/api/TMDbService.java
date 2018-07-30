package com.forcetower.playtime.api;

import com.forcetower.playtime.api.adapter.ApiResponse;
import com.forcetower.playtime.api.tmdb.GenreResponse;

import androidx.lifecycle.LiveData;
import retrofit2.http.GET;

public interface TMDbService {
    @GET("genre/movie/list")
    LiveData<ApiResponse<GenreResponse>> getMovieGenres();
    @GET("genre/tv/list")
    LiveData<ApiResponse<GenreResponse>> getSeriesGenres();
}
