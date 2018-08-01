package com.forcetower.playtime.db.dao;

import com.forcetower.playtime.db.model.RecUnprocessed;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface RecUnprocessedDao {
    @Insert(onConflict = REPLACE)
    void insert(RecUnprocessed... processed);

    @Insert(onConflict = REPLACE)
    void insert(List<RecUnprocessed> processed);

    @Query("SELECT * FROM RecUnprocessed")
    List<RecUnprocessed> getUnprocessedDirect();


}
