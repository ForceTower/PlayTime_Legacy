package com.forcetower.playtime.db.dao;

import com.forcetower.playtime.db.model.Title;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TitleDao {
    @Insert(onConflict = REPLACE)
    void insert(List<Title> titles);

    @Query("SELECT * FROM Title")
    LiveData<List<Title>> getTitles();

    @Query("SELECT * FROM Title WHERE movie = 1")
    LiveData<List<Title>> getMovies();

    @Query("SELECT * FROM Title WHERE movie = 0")
    LiveData<List<Title>> getSeries();
}
