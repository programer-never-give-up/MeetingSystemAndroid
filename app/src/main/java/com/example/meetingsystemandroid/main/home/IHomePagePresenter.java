package com.example.meetingsystemandroid.main.home;

public interface IHomePagePresenter {
    // 跳转到活动详情
    public void toActivityInfo(String id);
    // 获取最近活动信息
    public void getRecentActivies();
    // 获取推荐活动信息
    public void getRecommendActivies();
}
