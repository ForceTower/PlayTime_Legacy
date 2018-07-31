package com.forcetower.playtime.db.dao;

import com.forcetower.playtime.db.model.WatchlistItem;
import com.forcetower.playtime.db.relations.TitleWatchlist;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface WatchlistItemDao {
    @Transaction
    @Query("SELECT * FROM WatchlistItem w, Title t WHERE w.title_id = t.uid")
    LiveData<List<TitleWatchlist>> getWatchlist();

    @Insert(onConflict = REPLACE)
    void insert(WatchlistItem item);

    @Delete
    void delete(WatchlistItem item);

    @Query("DELETE FROM WatchlistItem WHERE id = :id")
    void deleteById(int id);

    @Query("DELETE FROM WatchlistItem WHERE title_id = :titleId")
    void deleteIfExisting(long titleId);
}
