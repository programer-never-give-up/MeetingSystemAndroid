package com.example.meetingsystemandroid.main.personal_center;

public interface IPersonalCenterFragmentView {
    // 已登录用户显示用户名和用户类型和头像 未登录用户显示游客和默认头像
    public void setUserCard();
    // 已登录用户显示取消登录 未登录用户显示登录
    public void logoutButtonChange();
}
