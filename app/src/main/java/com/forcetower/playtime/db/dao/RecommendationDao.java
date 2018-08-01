package com.forcetower.playtime.db.dao;

import com.forcetower.playtime.db.model.Recommendation;
import com.forcetower.playtime.db.relations.TitleRecommendation;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface RecommendationDao {
    @Insert(onConflict = IGNORE)
    void insert(List<Recommendation> recommendations);

    @Query("SELECT t.*, r.fromFriend, r.username FROM Recommendation r, Title t WHERE r.movie_id = t.uid")
    LiveData<List<TitleRecommendation>> getRecommendations();

    @Query("DELETE FROM Recommendation WHERE movie_id = :titleId")
    void removeRecommendationWithTitle(long titleId);
}
