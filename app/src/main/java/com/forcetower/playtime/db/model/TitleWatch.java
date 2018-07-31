package com.forcetower.playtime.db.model;

import java.util.UUID;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(indices = {
        @Index(value = "uuid", unique = true),
        @Index(value = {"title_id", "is_movie"}, unique = true)
}, foreignKeys = {
        @ForeignKey(entity = Title.class, parentColumns = "uid", childColumns = "title_id", onDelete = CASCADE, onUpdate = CASCADE)
})
public class TitleWatch {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    private String uuid;
    private long markTime;
    @ColumnInfo(name = "title_id")
    private long titleId;
    @ColumnInfo(name = "is_movie")
    private boolean isMovie;

    public TitleWatch(long markTime, long titleId, boolean isMovie) {
        this.uuid = UUID.randomUUID().toString();
        this.markTime = markTime;
        this.titleId = titleId;
        this.isMovie = isMovie;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getMarkTime() {
        return markTime;
    }

    public void setMarkTime(long markTime) {
        this.markTime = markTime;
    }

    public long getTitleId() {
        return titleId;
    }

    public void setTitleId(long titleId) {
        this.titleId = titleId;
    }

    public boolean isMovie() {
        return isMovie;
    }

    public void setMovie(boolean movie) {
        isMovie = movie;
    }
}
