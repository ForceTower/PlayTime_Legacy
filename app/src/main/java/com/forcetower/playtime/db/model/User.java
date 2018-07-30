package com.forcetower.playtime.db.model;

import com.google.gson.annotations.SerializedName;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @SerializedName(value = "id")
    @PrimaryKey(autoGenerate = true)
    private long uid;
    private String username;
    private String name;
    private String subtitle;
    @SerializedName(value = "image_url")
    private String image;
    private int hours;
    @SerializedName(value = "watch_count")
    private int watchCount;
    private int friends;
    private boolean me;

    public User(String name, String subtitle, String image, int hours, int watchCount, int friends) {
        this.name = name;
        this.subtitle = subtitle;
        this.image = image;
        this.hours = hours;
        this.watchCount = watchCount;
        this.friends = friends;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(int watchCount) {
        this.watchCount = watchCount;
    }

    public int getFriends() {
        return friends;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (uid != user.uid) return false;
        if (hours != user.hours) return false;
        if (watchCount != user.watchCount) return false;
        if (friends != user.friends) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (subtitle != null ? !subtitle.equals(user.subtitle) : user.subtitle != null)
            return false;
        return image != null ? image.equals(user.image) : user.image == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (uid ^ (uid >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (subtitle != null ? subtitle.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + hours;
        result = 31 * result + watchCount;
        result = 31 * result + friends;
        return result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isMe() {
        return me;
    }

    public void setMe(boolean me) {
        this.me = me;
    }
}
