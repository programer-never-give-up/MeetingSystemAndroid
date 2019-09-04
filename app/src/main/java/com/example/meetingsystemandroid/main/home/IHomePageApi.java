package com.example.meetingsystemandroid.main.home;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IHomePageApi {
    // 最近活动信息获取
    @GET("api/yw/showRecent/")
    Call<RecentActivitiesBean> getRecentActivities();

    // 推荐活动信息获取
    @GET("api/yw/showRecommend")
    Call<RecommendAcivitiesBean> getRecommendActivities();
}
