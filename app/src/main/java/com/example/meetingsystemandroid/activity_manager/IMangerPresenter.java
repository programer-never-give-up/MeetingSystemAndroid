package com.example.meetingsystemandroid.activity_manager;

public interface IMangerPresenter {

    public void getActivityList(String type);

    public void showActivityInfo(String id);

    public void onDelete(String type, String id);

    public void publishActivity(String id); // 传入会议id

    public void getTicket(String id, String name, String logo); // 通过活动id获取进场二维码

    public void scanQRCode(String id);
}
