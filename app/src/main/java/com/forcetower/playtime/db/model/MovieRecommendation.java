package com.forcetower.playtime.db.model;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MovieRecommendation {
    @PrimaryKey(autoGenerate = true)
    private long uid;
    private long titleOriginal;
    private long titleRecommended;

    public MovieRecommendation(long titleOriginal, long titleRecommended) {
        this.titleOriginal = titleOriginal;
        this.titleRecommended = titleRecommended;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getTitleOriginal() {
        return titleOriginal;
    }

    public void setTitleOriginal(long titleOriginal) {
        this.titleOriginal = titleOriginal;
    }

    public long getTitleRecommended() {
        return titleRecommended;
    }

    public void setTitleRecommended(long titleRecommended) {
        this.titleRecommended = titleRecommended;
    }

    @Override
    public String toString() {
        return titleRecommended + "";
    }
}
