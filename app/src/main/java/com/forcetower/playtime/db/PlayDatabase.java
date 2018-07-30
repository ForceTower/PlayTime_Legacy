package com.forcetower.playtime.db;

import com.forcetower.playtime.db.dao.AccessTokenDao;
import com.forcetower.playtime.db.model.AccessToken;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {
        AccessToken.class
}, version = 1)
public abstract class PlayDatabase extends RoomDatabase {
    public abstract AccessTokenDao accessTokenDao();
}
