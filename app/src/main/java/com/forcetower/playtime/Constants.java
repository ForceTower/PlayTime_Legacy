package com.forcetower.playtime;

public class Constants {
    public static final String PLAY_TIME_URL = BuildConfig.DEBUG ? "192.168.15.7" : "playtime.herokuapp.com";
    public static final String PLAY_TIME_SERVICE_BASE = "http" + (BuildConfig.DEBUG ? "" : "s") + "://" + PLAY_TIME_URL + "/api/";

    public static final String PLAY_TIME_PASSWORD_ID = "2";
    public static final String PLAY_TIME_PASSWORD_SECRET = "0tphofh07498orgiuero";
}
