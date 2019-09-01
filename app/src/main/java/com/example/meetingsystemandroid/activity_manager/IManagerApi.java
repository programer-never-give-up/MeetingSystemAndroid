package com.example.meetingsystemandroid.activity_manager;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IManagerApi {

    // 获取控制台列表信息
    @FormUrlEncoded
    @POST("api/activity/pageDisplay")
    public Call<ManagerResponseBean> getActivityList(@Field("btn-type") String type, @Field("page-id") int page, @Field("per-page") int perPageNum);
}
