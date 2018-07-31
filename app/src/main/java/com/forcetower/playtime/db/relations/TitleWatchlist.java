package com.forcetower.playtime.db.relations;

import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.db.model.WatchlistItem;

import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

public class TitleWatchlist extends WatchlistItem {
    @Embedded private Title title;

    public TitleWatchlist(long titleId, long added) {
        super(titleId, added);
    }

    @Ignore
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
