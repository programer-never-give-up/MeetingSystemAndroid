package com.example.meetingsystemandroid.model;

import java.util.UUID;

public class User {
    private static User activeUser;
    private UUID uuid;
    private String userName;
    private String avatar;
    public enum UserType {
        VISITOR,
        COMPANY,
        PERSONAL
    }
    private UserType type;
    private String profession;
    private String introduction;
    private String phoneNumber;
    private String email;


    public User getInstance() {
        if (activeUser == null) {
            activeUser = new User();
        }
        return activeUser;
    }

    private User() {
        // 初始化时为游客账户
        uuid = null;
        userName = "未登录";
        avatar = "http://139.219.14.146/avatar/227af7b8-c7fc-11e9-ba32-887873aca633.png"; // 之后换成default
        email = "";
        phoneNumber = "";
        profession = "";
        introduction = "";
        type = UserType.VISITOR;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
