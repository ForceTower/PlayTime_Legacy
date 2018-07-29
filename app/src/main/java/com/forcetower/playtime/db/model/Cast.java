package com.forcetower.playtime.db.model;

public class Cast {
    private long uid;
    private long titleId;
    private String image;
    private String name;
    private String role;

    public Cast(long titleId, String image, String name, String role) {
        this.titleId = titleId;
        this.image = image;
        this.name = name;
        this.role = role;
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
}
