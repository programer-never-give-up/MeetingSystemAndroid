package com.example.meetingsystemandroid.main.home;

import android.content.Context;
import android.content.Intent;

import com.example.meetingsystemandroid.activity_info.ShowActivityInfoActivity;
import com.example.meetingsystemandroid.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomePagePresenter implements IHomePagePresenter{
    private Context mContext;
    private IHomePageView mView;

    public HomePagePresenter(IHomePageView view, Context context) {
        mContext = context;
        mView = view;
    }


    @Override
    public void toActivityInfo(String id) {
        Intent intent = new Intent(mContext, ShowActivityInfoActivity.class);
        intent.putExtra(ShowActivityInfoActivity.SHOW_ACTIVITY_TAG, id);
        mContext.startActivity(intent);
    }

    @Override
    public void getRecentActivies() {
        Retrofit retrofit = RetrofitClient.getInstance(mContext);
        IHomePageApi api = retrofit.create(IHomePageApi.class);
        Call<RecentActivitiesBean> call = api.getRecentActivities();
        call.enqueue(new Callback<RecentActivitiesBean>() {
            @Override
            public void onResponse(Call<RecentActivitiesBean> call, Response<RecentActivitiesBean> response) {
                RecentActivitiesBean bean = response.body();
                if (bean != null) {
                    mView.updateRecentActivities(bean);
                }
                // 其他情况为请求失败，可以弹toast
            }

            @Override
            public void onFailure(Call<RecentActivitiesBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void getRecommendActivies() {
        // 获取推荐活动 api还未定义
    }
}
