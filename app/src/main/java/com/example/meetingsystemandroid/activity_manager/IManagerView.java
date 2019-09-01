package com.example.meetingsystemandroid.activity_manager;

import java.util.ArrayList;

public interface IManagerView {
    public void setArrayList(ManagerResponseBean bean);

    // 获取二维码门票时弹框显示
    public void showTicket();

    public void setActionBarTitle();

    // 请求失败时
    public void onFailed();
}
