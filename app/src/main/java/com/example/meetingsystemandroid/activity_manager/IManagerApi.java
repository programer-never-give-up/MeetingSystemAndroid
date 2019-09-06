package com.example.meetingsystemandroid.activity_manager;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IManagerApi {

    // 获取控制台列表信息
    @GET("api/activity/pageDisplay/")
    Call<ManagerActivityInfoResponseBean> getActivityList(@Query("btn-type") String type, @Query("page-id") int page, @Query("per-page") int perPageNum);

    @FormUrlEncoded
    @POST("api/activity/publishActivity/")
    Call<ChangeActivityStateResponseBean> publishActivity(@Field("act_uuid")String id);

    // 未发布和已发布的活动删除
    @FormUrlEncoded
    @POST("api/activity/deleteActivity/")
    Call<ChangeActivityStateResponseBean> deleteActivity(@Field("act_uuid")String id);

    // 撤回审核
    @FormUrlEncoded
    @POST("api/activity/cancelApplication/")
    Call<ChangeActivityStateResponseBean> cancelApplication(@Field("act_uuid") String id);

    @FormUrlEncoded
    @POST("api/yw/cancelApply/")
    Call<CancelApplyOrCollectResponseBean> cancelApply(@Field("uuid_act") String id);

    @FormUrlEncoded
    @POST("api/yw/cancelCollection/")
    Call<CancelApplyOrCollectResponseBean> cancelCollect(@Field("uuid_act") String id);
}
