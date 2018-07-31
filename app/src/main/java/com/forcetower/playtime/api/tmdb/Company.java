package com.forcetower.playtime.api.tmdb;

import com.google.gson.annotations.SerializedName;

public class Company {
    private int id;
    private String name;
    @SerializedName(value = "logo_path")
    private String logoPath;

    public Company(String name, String logoPath) {
        this.name = name;
        this.logoPath = logoPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
}
