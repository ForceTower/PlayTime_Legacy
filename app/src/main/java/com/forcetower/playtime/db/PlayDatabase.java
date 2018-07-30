package com.forcetower.playtime.db;

import com.forcetower.playtime.db.dao.AccessTokenDao;
import com.forcetower.playtime.db.dao.GenresDao;
import com.forcetower.playtime.db.dao.UserDao;
import com.forcetower.playtime.db.model.AccessToken;
import com.forcetower.playtime.db.model.Genre;
import com.forcetower.playtime.db.model.User;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {
        AccessToken.class,
        User.class,
        Genre.class
}, version = 1)
public abstract class PlayDatabase extends RoomDatabase {
    public abstract AccessTokenDao accessTokenDao();
    public abstract UserDao userDao();
    public abstract GenresDao genresDao();
}
