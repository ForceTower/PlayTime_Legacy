package com.forcetower.playtime.api.tmdb;

public class TitleVideo {
    private String name;
    private String site;
    private String type;
    private String key;

    public TitleVideo(String name, String site, String type, String key) {
        this.name = name;
        this.site = site;
        this.type = type;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
