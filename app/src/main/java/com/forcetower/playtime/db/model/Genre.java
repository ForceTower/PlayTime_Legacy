package com.forcetower.playtime.db.model;

import androidx.room.Ignore;

public class Genre {
    private long uid;
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    @Ignore
    public Genre(long uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
