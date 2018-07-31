package com.forcetower.playtime.db.dao;

import com.forcetower.playtime.db.model.Genre;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface GenresDao {
    @Insert(onConflict = REPLACE)
    void insert(List<Genre> genres);

    @Query("SELECT * FROM Genre")
    LiveData<List<Genre>> loadAll();

    @Query("SELECT * FROM Genre")
    List<Genre> loadAllDirect();
}
