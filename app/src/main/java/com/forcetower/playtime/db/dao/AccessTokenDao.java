package com.forcetower.playtime.db.dao;

import com.forcetower.playtime.db.model.AccessToken;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface AccessTokenDao {
    @Query("SELECT * FROM AccessToken ORDER BY created_at DESC LIMIT 1")
    LiveData<AccessToken> getAccessToken();

    @Query("SELECT * FROM AccessToken ORDER BY created_at DESC LIMIT 1")
    AccessToken getAccessTokenDirect();

    @Update
    void update(AccessToken token);

    @Insert(onConflict = REPLACE)
    void insert(AccessToken... token);

    @Delete
    void delete(AccessToken token);

    @Query("DELETE FROM AccessToken")
    void deleteAll();
}