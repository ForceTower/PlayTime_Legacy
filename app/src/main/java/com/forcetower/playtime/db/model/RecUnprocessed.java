package com.forcetower.playtime.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = @Index(value = "movie_id", unique = true))
public class RecUnprocessed {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "movie_id")
    private int movieId;

    public RecUnprocessed(int movieId) {
        this.movieId = movieId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
