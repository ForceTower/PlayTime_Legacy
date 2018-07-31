package com.forcetower.playtime.db;

import com.forcetower.playtime.db.dao.AccessTokenDao;
import com.forcetower.playtime.db.dao.CastDao;
import com.forcetower.playtime.db.dao.GenresDao;
import com.forcetower.playtime.db.dao.TVSeasonDao;
import com.forcetower.playtime.db.dao.TitleDao;
import com.forcetower.playtime.db.dao.TitleGenreDao;
import com.forcetower.playtime.db.dao.TitleWatchDao;
import com.forcetower.playtime.db.dao.UserDao;
import com.forcetower.playtime.db.dao.WatchlistItemDao;
import com.forcetower.playtime.db.model.AccessToken;
import com.forcetower.playtime.db.model.Cast;
import com.forcetower.playtime.db.model.Genre;
import com.forcetower.playtime.db.model.TVSeason;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.db.model.TitleGenre;
import com.forcetower.playtime.db.model.TitleWatch;
import com.forcetower.playtime.db.model.User;
import com.forcetower.playtime.db.model.WatchlistItem;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {
        AccessToken.class,
        User.class,
        Genre.class,
        Title.class,
        TitleGenre.class,
        Cast.class,
        WatchlistItem.class,
        TitleWatch.class,
        TVSeason.class
}, version = 1)
public abstract class PlayDatabase extends RoomDatabase {
    public abstract AccessTokenDao accessTokenDao();
    public abstract UserDao userDao();
    public abstract GenresDao genresDao();
    public abstract TitleDao titleDao();
    public abstract TitleGenreDao titleGenreDao();
    public abstract CastDao castDao();
    public abstract WatchlistItemDao watchlistItemDao();
    public abstract TitleWatchDao titleWatchDao();
    public abstract TVSeasonDao tvSeasonDao();
}
