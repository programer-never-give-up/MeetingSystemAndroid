package com.example.meetingsystemandroid.user_history;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.meetingsystemandroid.activity_info.ShowActivityInfoActivity;
import com.example.meetingsystemandroid.utils.RetrofitClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserHistoryPresenter implements IUserHistoryPresenter {
    private Context mContext;
    private IUserHistoryView mView;
    public UserHistoryPresenter(IUserHistoryView view, Context context) {
        mView = view;
        mContext = context;
    }


    @Override
    public void init(int typeId) {
        if (typeId == UserHistoryActivity.ORGANIZE_HISTORY) {
            mView.setTitle("举办历史");
            getUserOrganizeHistory();
        } else {
            mView.setTitle("参会历史");
            getUserAttendHistory();
        }
    }

    @Override
    public void getUserAttendHistory() {
        Retrofit retrofit = RetrofitClient.getInstance(mContext);
        IGetUserHistoryApi api = retrofit.create(IGetUserHistoryApi.class);
        Call<UserHistoryBean> call = api.getAttendHistory();
        call.enqueue(new Callback<UserHistoryBean>() {
            @Override
            public void onResponse(Call<UserHistoryBean> call, Response<UserHistoryBean> response) {
                UserHistoryBean bean = response.body();

                if (bean != null) {
                    mView.onSuccess(new ArrayList<UserHistoryBean.HistoryActivity>(Arrays.asList(bean.getList_activity())));
                } else {
                    mView.onFailed();
                }
            }

            @Override
            public void onFailure(Call<UserHistoryBean> call, Throwable t) {
                mView.onFailed();
            }
        });
    }

    @Override
    public void getUserOrganizeHistory() {
        Retrofit retrofit = RetrofitClient.getInstance(mContext);
        IGetUserHistoryApi api = retrofit.create(IGetUserHistoryApi.class);
        Call<UserHistoryBean> call = api.getOrganizeHistory();
        call.enqueue(new Callback<UserHistoryBean>() {
            @Override
            public void onResponse(Call<UserHistoryBean> call, Response<UserHistoryBean> response) {
                UserHistoryBean bean = response.body();
                if (bean != null) {
                    mView.onSuccess(new ArrayList<UserHistoryBean.HistoryActivity>(Arrays.asList(bean.getList_activity())));
                } else {
                    mView.onFailed();
                }
            }

            @Override
            public void onFailure(Call<UserHistoryBean> call, Throwable t) {
                mView.onFailed();
            }
        });
    }

    @Override
    public void toActivityInfo(String id) {
        Intent intent = new Intent(mContext, ShowActivityInfoActivity.class);
        intent.putExtra("id", id);
        mContext.startActivity(intent);
    }
}
