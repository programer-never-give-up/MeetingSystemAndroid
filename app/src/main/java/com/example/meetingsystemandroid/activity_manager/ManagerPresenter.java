package com.example.meetingsystemandroid.activity_manager;

import android.content.Context;
import android.content.Intent;

import com.example.meetingsystemandroid.activity_info.ShowActivityInfoActivity;
import com.example.meetingsystemandroid.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Retrofit;

public class ManagerPresenter implements IMangerPresenter{
    private Context mContext;
    private IManagerView mView;

    public ManagerPresenter(IManagerView view, Context context) {
        mView = view;
        mContext = context;
    }

    @Override
    public void getActivityList(String type) {
        Retrofit retrofit = RetrofitClient.getInstance(mContext);
        IManagerApi api = retrofit.create(IManagerApi.class);
        Call<ManagerResponseBean> call = api.getActivityList(type, 1, 5);
    }

    @Override
    public void showActivityInfo(String id) {
        Intent intent = new Intent(mContext, ShowActivityInfoActivity.class);
        intent.putExtra(ShowActivityInfoActivity.SHOW_ACTIVITY_TAG, id);
        mContext.startActivity(intent);
    }

    @Override
    public void onDelete(String type) {
        if (type.equals(ManagerActivity.ORGANIZE_INCHECK)) {
            // 审核中的活动 调用撤销审核api
        } else {
            // 未审核中的活动，调用删除api
        }
    }

    @Override
    public void publishActivity(String id) {
        // 调用发布api
    }

    @Override
    public void getTicket(String id) {
        // 获取门票信息 弹框显示
    }

    @Override
    public void scanQRCode(String id) {
        // 跳转到二维码扫描模块
    }

}
