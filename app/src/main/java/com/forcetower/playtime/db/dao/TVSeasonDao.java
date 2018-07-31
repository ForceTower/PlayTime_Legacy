package com.forcetower.playtime.db.dao;

import com.forcetower.playtime.db.model.TVSeason;
import com.forcetower.playtime.db.model.Title;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public abstract class TVSeasonDao {
    @Transaction
    public void insertTVSeasons(Title title) {
        List<TVSeason> seasons = title.getSeasons();
        for (TVSeason season : seasons) season.setTitleId(title.getUid());
        insert(seasons);
    }

    @Insert(onConflict = REPLACE)
    protected abstract void insert(List<TVSeason> seasons);

    @Query("SELECT * FROM TVSeason WHERE title_id = :titleId ORDER BY number ASC")
    public abstract LiveData<List<TVSeason>> getSeasons(long titleId);
}
