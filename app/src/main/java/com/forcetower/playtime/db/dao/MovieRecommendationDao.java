package com.forcetower.playtime.db.dao;

import com.forcetower.playtime.db.model.MovieRecommendation;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MovieRecommendationDao {
    @Insert(onConflict = REPLACE)
    void insert(MovieRecommendation... recommendation);
    @Insert(onConflict = REPLACE)
    void insert(List<MovieRecommendation> recommendation);
    @Query("SELECT * FROM MovieRecommendation WHERE titleOriginal = :titleId")
    LiveData<List<MovieRecommendation>> getRecommendations(long titleId);
    @Query("SELECT * FROM MovieRecommendation WHERE titleOriginal = :titleId")
    List<MovieRecommendation> getRecommendationsDirect(long titleId);
}
