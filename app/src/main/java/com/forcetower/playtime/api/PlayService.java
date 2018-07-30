package com.forcetower.playtime.api;

import com.forcetower.playtime.api.adapter.ApiResponse;
import com.forcetower.playtime.db.helpers.Credentials;
import com.forcetower.playtime.db.model.AccessToken;

import androidx.lifecycle.LiveData;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PlayService {
    @POST("oauth/token")
    LiveData<ApiResponse<AccessToken>> login(@Body Credentials.LoginCredentials credentials);
    @POST("oauth/token")
    LiveData<ApiResponse<AccessToken>> loginFacebook(@Body Credentials.FacebookCredentials credentials);
}
