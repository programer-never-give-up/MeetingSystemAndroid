package com.example.meetingsystemandroid.model;

import java.util.UUID;

public class User {
    private UUID uuid;
    private String userName;
    private String avatar;

    public User() {
        userName = "";
    }

    public User(String userName, String passWord) {
        this.userName = userName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
