package com.forcetower.playtime.db.model;

import com.google.gson.annotations.SerializedName;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Genre {
    @PrimaryKey
    @SerializedName(value = "id")
    private int uid;
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    @Ignore
    public Genre(int uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
