package com.forcetower.playtime.db.dao;

import com.forcetower.playtime.db.model.User;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {
    @Insert(onConflict = REPLACE)
    void insert(User user);

    @Query("SELECT * FROM User WHERE me = 1 LIMIT 1")
    LiveData<User> getMeUser();

    @Query("UPDATE USER SET me = 0")
    void setAllNotMe();
}
