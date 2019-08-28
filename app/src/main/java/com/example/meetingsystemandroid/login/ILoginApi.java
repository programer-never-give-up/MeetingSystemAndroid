package com.example.meetingsystemandroid.login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ILoginApi {

    @FormUrlEncoded
    @POST("api/login/")
    Call<LoginResponseBean> login(@Field("username") String username, @Field("password") String password);

}
