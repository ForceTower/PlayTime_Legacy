package com.forcetower.playtime;

public class Constants {
    public static final String PLAY_TIME_URL = BuildConfig.DEBUG ? "192.168.15.7" : "playtime.herokuapp.com";
    public static final String PLAY_TIME_SERVICE_BASE = "http" + (BuildConfig.DEBUG ? "" : "s") + "://" + PLAY_TIME_URL + "/api/";

    public static final String PLAY_TIME_PASSWORD_ID = "2";
    public static final String PLAY_TIME_PASSWORD_SECRET = "Y79n639NYg4eu2pqmUcAiIk1AMqq6KFMEHS09SJE";


    public static final String TMDB_URL = "api.themoviedb.org";
    public static final String TMDB_SERVICE_BASE = "https://" + TMDB_URL + "/3/";
    public static final String TMDB_API_KEY = "09c4d9535c32ae9ff25a0074c8701487";
}
