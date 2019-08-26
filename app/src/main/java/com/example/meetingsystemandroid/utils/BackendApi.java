package com.example.meetingsystemandroid.utils;

import com.example.meetingsystemandroid.bean.LoginBean;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BackendApi {

    @FormUrlEncoded
    @POST("api/login/")
    Call<LoginBean> login(@Field("username") String username, @Field("password") String password);
}
