package com.example.meetingsystemandroid.activity_manager;

public interface IManagerView {
    public void setArrayList(ManagerActivityInfoResponseBean bean);

    public void setActionBarTitle();

    // 请求失败时
    public void onFailed();

    public void deleteActivityList(String id);
}
