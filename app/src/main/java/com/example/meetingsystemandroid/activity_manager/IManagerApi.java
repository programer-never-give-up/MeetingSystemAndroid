package com.example.meetingsystemandroid.activity_manager;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IManagerApi {

    // 获取控制台列表信息
    @GET("api/activity/pageDisplay/")
    Call<ManagerResponseBean> getActivityList(@Query("btn-type") String type, @Query("page-id") int page, @Query("per-page") int perPageNum);
}
