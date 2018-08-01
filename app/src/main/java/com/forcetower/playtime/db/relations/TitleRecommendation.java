package com.forcetower.playtime.db.relations;

import com.forcetower.playtime.db.model.Title;

public class TitleRecommendation extends Title {
    private String username;
    private boolean fromFriend;

    public TitleRecommendation(String name, String image, String trailer, float rating, String releaseDate) {
        super(name, image, trailer, rating, releaseDate);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isFromFriend() {
        return fromFriend;
    }

    public void setFromFriend(boolean fromFriend) {
        this.fromFriend = fromFriend;
    }
}
