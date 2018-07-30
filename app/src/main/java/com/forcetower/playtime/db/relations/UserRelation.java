package com.forcetower.playtime.db.relations;

import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.db.model.User;

import java.util.List;

public class UserRelation extends User {
    private List<Title> favorites;
    private List<Title> history;
    private List<TitleComment> comments;

    public UserRelation(String name, String subtitle, String image, int hours, int watchCount, int friends) {
        super(name, subtitle, image, hours, watchCount, friends);
    }

    public List<Title> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Title> favorites) {
        this.favorites = favorites;
    }

    public List<Title> getHistory() {
        return history;
    }

    public void setHistory(List<Title> history) {
        this.history = history;
    }

    public List<TitleComment> getComments() {
        return comments;
    }

    public void setComments(List<TitleComment> comments) {
        this.comments = comments;
    }
}
