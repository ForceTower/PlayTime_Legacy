package com.forcetower.playtime.db.dao;

import com.forcetower.playtime.db.model.TitleWatch;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TitleWatchDao {
    @Insert(onConflict = REPLACE)
    void insert(TitleWatch watch);

    @Query("SELECT * FROM TitleWatch")
    LiveData<List<TitleWatch>> getWatched();
}
