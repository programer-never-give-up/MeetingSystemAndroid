package com.example.meetingsystemandroid.register;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IRegisterApi {

    @FormUrlEncoded
    @POST("api/register/")
    Call<RegisterResponseBean> register(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("api/sendMail/")
    Call<SendMailResponseBean> sendMail(@Field("email") String email);

    @FormUrlEncoded
    @POST("api/checkMail")
    Call<CheckCodeResponseBean> checkCode(@Field("code") String code);

}
