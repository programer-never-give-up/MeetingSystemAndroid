package com.example.meetingsystemandroid.user_history;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IGetUserHistoryApi {

//    @GET("api/personal_center/history_attend/")
//    Call<UserHistoryBean> getAttendHistory();
//
//    @GET("api/personal_center/history_organize/")
//    Call<UserHistoryBean> getOrganizeHistory();

    @POST("api/personal_center/history_attend/")
    Call<UserHistoryBean> getAttendHistory();

    @POST("api/personal_center/history_organize/")
    Call<UserHistoryBean> getOrganizeHistory();

}
