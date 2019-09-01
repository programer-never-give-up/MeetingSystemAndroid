package com.example.meetingsystemandroid.activity_info;

public interface IShowAcitivityView {
    // 请求数据成功时调用，设置页面数据
    public void onGetInfoSuccess(ActivityBean bean);

    // 请求失败跳回原来页面
    public void onGetInfoFailed();

    public void setActionBarTitle();
}
