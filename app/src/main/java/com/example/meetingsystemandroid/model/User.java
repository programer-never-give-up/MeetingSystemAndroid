package com.example.meetingsystemandroid.model;

import com.example.meetingsystemandroid.main.UserBean;

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
    private String gender;
    private String address;


    public static User getInstance() {
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

    public static void setDefault() {
        activeUser.setAvatar("http://139.219.14.146/avatar/227af7b8-c7fc-11e9-ba32-887873aca633.png");
        activeUser.setEmail("");
        activeUser.setAddress("");
        activeUser.setIntroduction("");
        activeUser.setGender("");
        activeUser.setPhoneNumber("");
        activeUser.setType(UserType.VISITOR);
        activeUser.setProfession("");
        activeUser.setUserName("未登录");
    }

    public static void setUserbeanToUser(UserBean bean) {
        if (activeUser == null) {
            activeUser = new User();
        }
        activeUser.setAvatar(bean.getAvatar());
        activeUser.setEmail(bean.getEmail());
        activeUser.setIntroduction(bean.getIntroduction());
        switch (bean.getType()) {
            case "游客":
                activeUser.setType(UserType.VISITOR);
                break;
            case "企业":
                activeUser.setType(UserType.COMPANY);
                break;
            case "个人":
                activeUser.setType(UserType.PERSONAL);
                break;
                default:
                    activeUser.setType(UserType.VISITOR);
        }
        activeUser.setPhoneNumber(bean.getPhone_number());
        activeUser.setUserName(bean.getUsername());
        activeUser.setGender(bean.getGender());
        activeUser.setAddress(bean.getAddress());
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

    public String typeToString() {
        switch (type) {
            case VISITOR:
                return "游客";
            case COMPANY:
                return "企业用户";
            case PERSONAL:
                return "个人用户";
        }
        return "错误代码";
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
