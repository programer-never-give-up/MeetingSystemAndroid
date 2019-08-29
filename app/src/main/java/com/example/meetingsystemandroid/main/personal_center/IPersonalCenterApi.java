package com.example.meetingsystemandroid.main.personal_center;

import android.util.Log;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IPersonalCenterApi {

    @GET("api/logout/")
    Call<LogoutBean> logout();

}
