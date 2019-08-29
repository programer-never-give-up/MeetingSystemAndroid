package com.example.meetingsystemandroid.main.personal_center;

import com.example.meetingsystemandroid.model.User;

public interface IPersonalCenterFragmentView {
    // 已登录用户显示用户名和用户类型和头像 未登录用户显示游客和默认头像
    public void setUserCard(String avatar, String username,String type);
    // 将取消登录字符变为登录 (对游客账户)
    public void logoutButtonChange(String notice);

    public void setOrganizeVisible(boolean visible);

    public void close();

}
