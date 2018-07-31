package com.forcetower.playtime.db.model;

import com.google.gson.annotations.SerializedName;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(entity = Title.class, parentColumns = "uid", childColumns = "titleId", onUpdate = CASCADE, onDelete = CASCADE)
}, indices = {
        @Index(value = "titleId")
})
public class TitleImage {
    @PrimaryKey(autoGenerate = true)
    private long uid;
    @SerializedName("file_path")
    private String image;
    private String width;
    private long titleId;

    public TitleImage(String image, String width, long titleId) {
        this.image = image;
        this.width = width;
        this.titleId = titleId;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public long getTitleId() {
        return titleId;
    }

    public void setTitleId(long titleId) {
        this.titleId = titleId;
    }
}
