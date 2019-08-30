package com.example.meetingsystemandroid.activity_info;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface IGetActivityInfoApi {
    @GET("api/activity/showActivity")
    Call<ActivityBean> getActivityInfo(@Field("uuid") String uuid);
}
