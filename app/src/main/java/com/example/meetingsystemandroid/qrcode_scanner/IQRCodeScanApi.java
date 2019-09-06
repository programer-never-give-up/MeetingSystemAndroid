package com.example.meetingsystemandroid.qrcode_scanner;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IQRCodeScanApi {
    @FormUrlEncoded
    @POST("api/yw/checkAttend/")
    Call<CheckQRCodeResponseBean> checkQRCode(@Field("uuid_act") String uuid_act, @Field("uuid_user") String uuid_user);
}
