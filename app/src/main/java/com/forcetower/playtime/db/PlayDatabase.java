package com.forcetower.playtime.db;

import com.forcetower.playtime.db.dao.AccessTokenDao;
import com.forcetower.playtime.db.dao.CastDao;
import com.forcetower.playtime.db.dao.GenresDao;
import com.forcetower.playtime.db.dao.TitleDao;
import com.forcetower.playtime.db.dao.TitleGenreDao;
import com.forcetower.playtime.db.dao.UserDao;
import com.forcetower.playtime.db.model.AccessToken;
import com.forcetower.playtime.db.model.Cast;
import com.forcetower.playtime.db.model.Genre;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.db.model.TitleGenre;
import com.forcetower.playtime.db.model.User;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {
        AccessToken.class,
        User.class,
        Genre.class,
        Title.class,
        TitleGenre.class,
        Cast.class
}, version = 1)
public abstract class PlayDatabase extends RoomDatabase {
    public abstract AccessTokenDao accessTokenDao();
    public abstract UserDao userDao();
    public abstract GenresDao genresDao();
    public abstract TitleDao titleDao();
    public abstract TitleGenreDao titleGenreDao();
    public abstract CastDao castDao();
}
