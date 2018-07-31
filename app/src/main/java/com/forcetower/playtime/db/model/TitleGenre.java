package com.forcetower.playtime.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(entity = Title.class, parentColumns = "uid", childColumns = "title_id", onUpdate = CASCADE, onDelete = CASCADE),
        @ForeignKey(entity = Genre.class, parentColumns = "uid", childColumns = "genre_id", onUpdate = CASCADE, onDelete = CASCADE)
}, indices = {
        @Index(value = {"title_id", "genre_id"}, unique = true),
        @Index(value = {"genre_id"})
})
public class TitleGenre {
    @PrimaryKey(autoGenerate = true)
    private long uid;
    @ColumnInfo(name = "title_id")
    private long titleId;
    @ColumnInfo(name = "genre_id")
    private long genreId;

    public TitleGenre(long titleId, long genreId) {
        this.titleId = titleId;
        this.genreId = genreId;
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

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }
}
