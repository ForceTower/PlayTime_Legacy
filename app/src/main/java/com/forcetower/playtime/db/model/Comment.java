package com.forcetower.playtime.db.model;

import androidx.room.Ignore;

public class Comment {
    private long uid;
    private long titleId;
    private long userId;
    private long timestamp;
    private String comment;
    private String userName;
    private String userImage;

    public Comment(long titleId, long userId, String comment) {
        this.titleId = titleId;
        this.userId = userId;
        this.comment = comment;
    }

    @Ignore
    public Comment(String comment, String userName, String userImage, long timestamp) {
        this.timestamp = timestamp;
        this.comment = comment;
        this.userName = userName;
        this.userImage = userImage;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getTitleId() {
        return titleId;
    }

    public void setTitleId(long titleId) {
        this.titleId = titleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment1 = (Comment) o;

        if (uid != comment1.uid) return false;
        if (timestamp != comment1.timestamp) return false;
        return comment.equals(comment1.comment);
    }

    @Override
    public int hashCode() {
        int result = (int) (uid ^ (uid >>> 32));
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + comment.hashCode();
        return result;
    }
}
