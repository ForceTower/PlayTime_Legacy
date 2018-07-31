package com.forcetower.playtime.api;

import com.forcetower.playtime.api.adapter.ApiResponse;
import com.forcetower.playtime.api.tmdb.GenreResponse;
import com.forcetower.playtime.api.tmdb.PopularResult;
import com.forcetower.playtime.api.tmdb.TitleCertification;
import com.forcetower.playtime.api.tmdb.TitleImages;
import com.forcetower.playtime.db.model.Title;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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
    @GET("movie/{movie_id}")
    LiveData<ApiResponse<Title>> getMovieDetails(@Path("movie_id") long movieId, @Query("append_to_response") String append);
    @GET("tv/{tv_id}")
    LiveData<ApiResponse<Title>> getSeriesDetails(@Path("tv_id") long tvId, @Query("append_to_response") String append);
    @GET("movie/{movie_id}/release_dates")
    LiveData<ApiResponse<TitleCertification>> getMovieRating(@Path("movie_id") long movieId);
    @GET("tv/{tv_id}/content_ratings")
    LiveData<ApiResponse<TitleCertification>> getSeriesRating(@Path("tv_id") long tvId);
    @GET("movie/{movie_id}/similar")
    Call<PopularResult> getSimilarMovies(@Path("movie_id") long movieId, @Query("page") int page);
    @GET("tv/{tv_id}/similar")
    Call<PopularResult> getSimilarSeries(@Path("tv_id") long seriesId, @Query("page") int page);
    @GET("movie/{movie_id}/images")
    LiveData<ApiResponse<TitleImages>> getMovieImages(@Path("movie_id") long movieId, @Query("include_image_language") String language);
    @GET("movie/{tv_id}/images")
    LiveData<ApiResponse<TitleImages>> getSeriesImages(@Path("tv_id") long tvId, @Query("include_image_language") String language);

}
