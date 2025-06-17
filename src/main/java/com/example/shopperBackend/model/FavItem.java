package com.example.shopperBackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavItem {
    @JsonProperty(value = "item_id")
    private int itemId;
    @JsonProperty(value = "user_username")
    private String userUsername;

    public FavItem() {
    }

    public FavItem(int itemId, String userUsername) {
        this.itemId = itemId;
        this.userUsername = userUsername;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "FavItems{" +
                "itemId=" + itemId +
                ", userUsername='" + userUsername + '\'' +
                '}';
    }
}
