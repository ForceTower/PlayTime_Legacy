package com.forcetower.playtime.db.relations;

import com.forcetower.playtime.db.model.Comment;
import com.forcetower.playtime.db.model.Title;

import androidx.room.Ignore;

public class TitleComment extends Comment {
    private Title title;

    public TitleComment(String comment, String userName, String userImage, long timestamp) {
        super(comment, userName, userImage, timestamp);
    }

    @Ignore
    public TitleComment(Comment comment, Title title) {
        super(comment.getComment(), comment.getUserName(), comment.getUserImage(), comment.getTimestamp());
        this.title = title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Title getTitle() {
        return title;
    }
}
