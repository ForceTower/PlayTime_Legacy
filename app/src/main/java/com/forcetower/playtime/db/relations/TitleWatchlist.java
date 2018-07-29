package com.forcetower.playtime.db.relations;

import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.db.model.WatchlistItem;

import androidx.room.Relation;

public class TitleWatchlist extends WatchlistItem {
    @Relation(entity = Title.class, parentColumn = "title_id", entityColumn = "uid")
    private Title title;

    public TitleWatchlist(Title title, long added) {
        super(title.getUid(), added);
        this.title = title;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
