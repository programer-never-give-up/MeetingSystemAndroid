package com.example.meetingsystemandroid.activity_info;


import android.support.v4.media.session.PlaybackStateCompat;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IGetActivityInfoApi {
    @GET("api/activity/showActivity")
    Call<ActivityBean> getActivityInfo(@Query("uuid") String uuid);
//
//    @FormUrlEncoded
//    @POST("api/activity/showActivity/")
//    Call<ActivityBean> getActivityInfo(@Field("uuid") String uuid);
}
