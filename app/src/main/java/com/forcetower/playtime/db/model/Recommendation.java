package com.forcetower.playtime.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = @Index(value = "movie_id", unique = true))
public class Recommendation {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "movie_id")
    private long movieId;
    private boolean fromFriend;
    private long username;

    public Recommendation(long movieId) {
        this.movieId = movieId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public boolean isFromFriend() {
        return fromFriend;
    }

    public void setFromFriend(boolean fromFriend) {
        this.fromFriend = fromFriend;
    }

    public long getUsername() {
        return username;
    }

    public void setUsername(long username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return getMovieId() + " -> FF? " + fromFriend + " " + username;
    }
}
