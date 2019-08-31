package com.example.meetingsystemandroid.user_history;

public interface IUserHistoryPresenter {

    // 根据类型初始化界面
    public void init(int typeId);
    // 获取用户参与历史
    public void getUserAttendHistory();

    // 获取用户举办历史
    public void getUserOrganizeHistory();

    public void toActivityInfo(String id);
}
