package com.forcetower.playtime.api;

import com.forcetower.playtime.api.adapter.ApiResponse;
import com.forcetower.playtime.api.tmdb.GenreResponse;
import com.forcetower.playtime.api.tmdb.PopularResult;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDbService {
    @GET("genre/movie/list")
    LiveData<ApiResponse<GenreResponse>> getMoviesGenres();
    @GET("genre/tv/list")
    LiveData<ApiResponse<GenreResponse>> getSeriesGenres();
    @GET("movie/popular")
    Call<PopularResult> getMoviesPopular(@Query("page") int page);
    @GET("tv/popular")
    Call<PopularResult> getSeriesPopular(@Query("page") int page);
}
