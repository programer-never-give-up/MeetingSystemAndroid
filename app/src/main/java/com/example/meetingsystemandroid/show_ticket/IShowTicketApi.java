package com.example.meetingsystemandroid.show_ticket;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IShowTicketApi {
    @GET("api/yw/getQRcode/")
    Call<QRCodeResponseBean> getQRcode(@Query("uuid_act") String id);
}
