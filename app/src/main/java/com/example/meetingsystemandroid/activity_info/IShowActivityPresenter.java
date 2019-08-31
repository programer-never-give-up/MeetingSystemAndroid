package com.example.meetingsystemandroid.activity_info;

public interface IShowActivityPresenter {
    // 通过uuid请求活动数据
   public void setActivity(String id);

   public void downloadActivityFile(String filePath);
}
