package com.example.meetingsystemandroid.main.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

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
        Call<HomePageActivitiesBean> call = api.getRecentActivities();
        call.enqueue(new Callback<HomePageActivitiesBean>() {
            @Override
            public void onResponse(Call<HomePageActivitiesBean> call, Response<HomePageActivitiesBean> response) {
                HomePageActivitiesBean bean = response.body();
                if (bean != null) {
                    mView.updateRecentActivities(bean);
                }
                // 其他情况为请求失败，可以弹toast
            }

            @Override
            public void onFailure(Call<HomePageActivitiesBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void getRecommendActivies() {
        // 获取推荐活动
        Retrofit retrofit = RetrofitClient.getInstance(mContext);
        IHomePageApi api = retrofit.create(IHomePageApi.class);
        Call<HomePageActivitiesBean> call = api.getRecommendActivities();
        call.enqueue(new Callback<HomePageActivitiesBean>() {
            @Override
            public void onResponse(Call<HomePageActivitiesBean> call, Response<HomePageActivitiesBean> response) {
                HomePageActivitiesBean bean = response.body();
                if (bean != null) {
                    Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                    mView.updateRecommendActivities(bean);
                }
            }

            @Override
            public void onFailure(Call<HomePageActivitiesBean> call, Throwable t) {

            }
        });
    }
}
