package com.forcetower.playtime.api;

import com.forcetower.playtime.api.adapter.ApiResponse;
import com.forcetower.playtime.db.helpers.Credentials;
import com.forcetower.playtime.db.model.AccessToken;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.db.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PlayService {
    @POST("oauth/token")
    LiveData<ApiResponse<AccessToken>> login(@Body Credentials.LoginCredentials credentials);
    @POST("oauth/token")
    LiveData<ApiResponse<AccessToken>> loginFacebook(@Body Credentials.FacebookCredentials credentials);
    @GET("me")
    LiveData<ApiResponse<User>> me();
    @GET("movies/popular")
    Call<List<Title>> getPopularMovies(int page);
}
