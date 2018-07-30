package com.forcetower.playtime.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(entity = Title.class, parentColumns = "uid", childColumns = "title_id", onDelete = CASCADE, onUpdate = CASCADE)
}, indices = {
        @Index(value = "title_id", unique = true)
})
public class    WatchlistItem {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "title_id")
    private long titleId;
    private long added;

    public WatchlistItem(long titleId, long added) {
        this.titleId = titleId;
        this.added = added;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getTitleId() {
        return titleId;
    }

    public void setTitleId(long titleId) {
        this.titleId = titleId;
    }

    public long getAdded() {
        return added;
    }

    public void setAdded(long added) {
        this.added = added;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WatchlistItem that = (WatchlistItem) o;

        if (uid != that.uid) return false;
        if (titleId != that.titleId) return false;
        return added == that.added;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (int) (titleId ^ (titleId >>> 32));
        result = 31 * result + (int) (added ^ (added >>> 32));
        return result;
    }
}
