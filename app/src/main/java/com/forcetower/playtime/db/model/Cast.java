package com.forcetower.playtime.db.model;

import com.google.gson.annotations.SerializedName;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cast {
    @PrimaryKey
    @SerializedName(value = "id")
    private long uid;
    private long titleId;
    private int gender;
    @SerializedName(value = "profile_path")
    private String image;
    private String name;
    @SerializedName(value = "character")
    private String role;
    private int order;

    public Cast(long titleId, String image, String name, String role) {
        this.titleId = titleId;
        this.image = image;
        this.name = name;
        this.role = role;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getTitleId() {
        return titleId;
    }

    public void setTitleId(long titleId) {
        this.titleId = titleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
