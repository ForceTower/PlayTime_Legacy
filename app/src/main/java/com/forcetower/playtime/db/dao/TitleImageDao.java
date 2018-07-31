package com.forcetower.playtime.db.dao;

import com.forcetower.playtime.db.model.TitleImage;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TitleImageDao {
    @Insert(onConflict = REPLACE)
    void insert(List<TitleImage> items);

    @Query("SELECT * FROM TitleImage WHERE titleId = :titleId")
    LiveData<List<TitleImage>> getTitleImages(long titleId);
}
