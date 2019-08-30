package com.example.meetingsystemandroid.user_history;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IGetUserHistoryApi {

    @GET("api/personal_center/history_attend/")
    Call<UserHistoryBean> getAttendHistory();

    @GET("api/personal_center/history_organize")
    Call<UserHistoryBean> getOrganizeHistory();
}
