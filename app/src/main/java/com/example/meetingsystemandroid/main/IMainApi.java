package com.example.meetingsystemandroid.main;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IMainApi {

    @GET("api/check/")
    Call<LoginCheckResponseBean> checkLogin();

    @GET("api/personal_center/showInfo")
    Call<UserBean> getUserInfo();
}
