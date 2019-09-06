package com.example.meetingsystemandroid.activity_manager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.meetingsystemandroid.activity_info.ShowActivityInfoActivity;
import com.example.meetingsystemandroid.qrcode_scanner.QRCodeScannerActivity;
import com.example.meetingsystemandroid.show_ticket.ShowTicketActivity;
import com.example.meetingsystemandroid.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
        Call<ManagerActivityInfoResponseBean> call = api.getActivityList(type, 1, 5);
        call.enqueue(new Callback<ManagerActivityInfoResponseBean>() {
            @Override
            public void onResponse(Call<ManagerActivityInfoResponseBean> call, Response<ManagerActivityInfoResponseBean> response) {

                ManagerActivityInfoResponseBean bean = response.body();
                if (bean != null) {
                    mView.setArrayList(bean);
                } else {
                    mView.onFailed();
                }
            }

            @Override
            public void onFailure(Call<ManagerActivityInfoResponseBean> call, Throwable t) {
                mView.onFailed();
            }
        });
    }

    @Override
    public void showActivityInfo(String id) {
        Intent intent = new Intent(mContext, ShowActivityInfoActivity.class);
        intent.putExtra(ShowActivityInfoActivity.SHOW_ACTIVITY_TAG, id);
        mContext.startActivity(intent);
    }

    @Override
    public void onDelete(String type, String id) {
        Retrofit retrofit = RetrofitClient.getInstance(mContext);
        IManagerApi api = retrofit.create(IManagerApi.class);
        if (type.equals(ManagerActivity.ORGANIZE_INCHECK)) {
            // 审核中的活动 调用撤销审核api
            Call<ChangeActivityStateResponseBean> call = api.cancelApplication(id);
            call.enqueue(new Callback<ChangeActivityStateResponseBean>() {
                @Override
                public void onResponse(Call<ChangeActivityStateResponseBean> call, Response<ChangeActivityStateResponseBean> response) {
                    ChangeActivityStateResponseBean bean = response.body();
                    if (bean != null) {
                        if (bean.isAct_status()) {
                            mView.deleteActivityList(id);
                        } else {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ChangeActivityStateResponseBean> call, Throwable t) {

                }
            });
        } else if (type.equals(ManagerActivity.ORGANIZE_UNPUBLISHED) || type.equals(ManagerActivity.ORGANIZE_PUBLISHED)){
            // 未审核或已发布的活动，调用删除api
            Call<ChangeActivityStateResponseBean> call = api.deleteActivity(id);
            call.enqueue(new Callback<ChangeActivityStateResponseBean>() {
                @Override
                public void onResponse(Call<ChangeActivityStateResponseBean> call, Response<ChangeActivityStateResponseBean> response) {
                    ChangeActivityStateResponseBean bean = response.body();
                    if (bean != null) {
                        if (bean.isAct_status()) {
                            mView.deleteActivityList(id);
                        } else {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ChangeActivityStateResponseBean> call, Throwable t) {

                }
            });
        } else if (type.equals(ManagerActivity.ATTEND_NOT_START)) {
            // 未开始的参加活动撤销报名
            Call<CancelApplyOrCollectResponseBean> call = api.cancelApply(id);
            call.enqueue(new Callback<CancelApplyOrCollectResponseBean>() {
                @Override
                public void onResponse(Call<CancelApplyOrCollectResponseBean> call, Response<CancelApplyOrCollectResponseBean> response) {
                    CancelApplyOrCollectResponseBean bean = response.body();
                    if (bean != null) {
                        // 判断删除状态，成功则数据更改
                        if (bean.isStatus()) {
                            mView.deleteActivityList(id);
                        } else {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                       }
                    }
                }

                @Override
                public void onFailure(Call<CancelApplyOrCollectResponseBean> call, Throwable t) {

                }
            });
        } else {
            // 收藏活动 随时可以取消
            Call<CancelApplyOrCollectResponseBean> call = api.cancelCollect(id);
            call.enqueue(new Callback<CancelApplyOrCollectResponseBean>() {
                @Override
                public void onResponse(Call<CancelApplyOrCollectResponseBean> call, Response<CancelApplyOrCollectResponseBean> response) {
                    // 调用view函数更新adapter数据列
                    CancelApplyOrCollectResponseBean bean = response.body();
                    if (bean != null) {
                        if (bean.isStatus()) {
                            // 取消成功更新数据
                            mView.deleteActivityList(id);
                        } else {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<CancelApplyOrCollectResponseBean> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void publishActivity(String id) {
        // 调用发布api
        Retrofit retrofit = RetrofitClient.getInstance(mContext);
        IManagerApi api = retrofit.create(IManagerApi.class);
        Call<ChangeActivityStateResponseBean> call = api.publishActivity(id);
        call.enqueue(new Callback<ChangeActivityStateResponseBean>() {
            @Override
            public void onResponse(Call<ChangeActivityStateResponseBean> call, Response<ChangeActivityStateResponseBean> response) {
                ChangeActivityStateResponseBean bean = response.body();
                if (bean != null) {
                    if (bean.isAct_status()) {
                        mView.deleteActivityList(id);
                    } else {
                        Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ChangeActivityStateResponseBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void getTicket(String id, String name, String logo) {
        // 获取门票信息 弹框显示
        Intent intent = new Intent(mContext, ShowTicketActivity.class);
        intent.putExtra(ShowTicketActivity.TICKET_ACTIVITY_ID, id);
        intent.putExtra(ShowTicketActivity.TICKET_ACTIVITY_LOGO, logo);
        intent.putExtra(ShowTicketActivity.TICKET_ACTIVITY_NAME, name);
        mContext.startActivity(intent);
    }

    @Override
    public void scanQRCode(String id) {
        // 跳转到二维码扫描模块
        Intent intent = new Intent(mContext, QRCodeScannerActivity.class);
        intent.putExtra(QRCodeScannerActivity.QRCODE_TAG, id);
        mContext.startActivity(intent);
    }

}
