package com.forcetower.playtime.db.dao;

import com.forcetower.playtime.db.model.Genre;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.db.model.TitleGenre;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Transaction;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public abstract class TitleGenreDao {
    @Transaction
    public void insertTitleAndGenres(List<Title> titles) {
        for (Title title : titles) {
            insertTitle(title);
            List<TitleGenre> genres = new ArrayList<>();
            if (title.getGenreIds() != null) {
                for (int id : title.getGenreIds()) genres.add(new TitleGenre(title.getUid(), id));
            }
            insert(genres);
        }
    }

    @Insert(onConflict = REPLACE)
    public abstract void insert(List<TitleGenre> genres);

    @Insert(onConflict = REPLACE)
    public abstract void insertTitle(Title title);

    @Update(onConflict = REPLACE)
    public abstract void updateTitle(Title title);

    public void insertSingleTitle(Title title) {
        updateTitle(title);
        List<TitleGenre> genres = new ArrayList<>();
        if (title.getGenreList() != null) {
            for (Genre genre : title.getGenreList()) genres.add(new TitleGenre(title.getUid(), genre.getUid()));
        }
        insert(genres);
    }
}
