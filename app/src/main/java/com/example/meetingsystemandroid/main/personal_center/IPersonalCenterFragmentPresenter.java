package com.example.meetingsystemandroid.main.personal_center;

public interface IPersonalCenterFragmentPresenter {
    // 判断用户登录状态 获取用户信息 设置view样式
    public void checkUserStatus();

    // 跳转查看用户详细信息
    public void toAllUserInfo();

    // 跳转查看参与历史
    public void toAttendHistory();

    // 跳转举办历史
    public void toOrganizeHistory();

    // 登出
    public void logout();

    // 登录
    public void toLogin();
}
