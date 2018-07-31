package com.forcetower.playtime.db.dao;

import com.forcetower.playtime.db.model.Cast;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CastDao {
    @Insert(onConflict = REPLACE)
    void insert(List<Cast> cast);

    @Query("SELECT * FROM `Cast` WHERE titleId = :titleId ORDER BY `order` ASC")
    LiveData<List<Cast>> getTitleCast(long titleId);
}
