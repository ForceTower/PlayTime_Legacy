package com.forcetower.playtime.db.helpers;

import com.forcetower.playtime.Constants;
import com.google.gson.annotations.SerializedName;

public abstract class Credentials {
    @SerializedName(value = "grant_type")
    private final String grantType;
    @SerializedName(value = "client_id")
    private final String clientId;
    @SerializedName(value = "client_secret")
    private final String clientSecret;
    private final String scope;

    private Credentials(String grantType, String clientId, String clientSecret, String scope) {
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
    }

    public String getGrantType() {
        return grantType;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getScope() {
        return scope;
    }

    public static class LoginCredentials extends Credentials {
        private final String username;
        private final String password;

        public LoginCredentials(String username, String password) {
            super("password", Constants.PLAY_TIME_PASSWORD_ID, Constants.PLAY_TIME_PASSWORD_SECRET, "*");
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
