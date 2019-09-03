package com.example.meetingsystemandroid.activity_info;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.example.meetingsystemandroid.model.User;
import com.example.meetingsystemandroid.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShowActivityPresenter implements IShowActivityPresenter{

    private IShowAcitivityView mView;
    private Context mContext;

    public ShowActivityPresenter(IShowAcitivityView view, Context context) {
        mContext = context;
        mView = view;
    }

    @Override
    public void setActivity(String id) {
        Retrofit retrofit = RetrofitClient.getInstance(mContext);
        IGetActivityInfoApi api = retrofit.create(IGetActivityInfoApi.class);
        Call<ActivityBean> call = api.getActivityInfo(id);
        call.enqueue(new Callback<ActivityBean>() {
            @Override
            public void onResponse(Call<ActivityBean> call, Response<ActivityBean> response) {
                ActivityBean bean = response.body();
                if (bean != null) {
                    mView.onGetInfoSuccess(bean);
                } else {
                    mView.onGetInfoFailed();
                }
            }

            @Override
            public void onFailure(Call<ActivityBean> call, Throwable t) {
                mView.onGetInfoFailed();
            }
        });
    }

    @Override
    public void downloadActivityFile(String filePath) {
        Uri uri = Uri.parse(RetrofitClient.BASE_URL+filePath);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        mContext.startActivity(intent);
    }

    @Override
    public void attendActivity(String id) {
        User user = User.getInstance();
        // 如果是游客则跳转到登录界面
        if (user.getType() == User.UserType.VISITOR) {
            mView.toLogin();
        } else {
            Retrofit retrofit = RetrofitClient.getInstance(mContext);
            IGetActivityInfoApi api = retrofit.create(IGetActivityInfoApi.class);
            Call<ApplyAndCollectResponseBean> call = api.apply(id);
            call.enqueue(new Callback<ApplyAndCollectResponseBean>() {
                @Override
                public void onResponse(Call<ApplyAndCollectResponseBean> call, Response<ApplyAndCollectResponseBean> response) {
                    ApplyAndCollectResponseBean bean = response.body();
                    if (bean != null) {
                        Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApplyAndCollectResponseBean> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void collectActivity(String id) {
        User user = User.getInstance();
        // 如果是游客则跳转到登录界面
        if (user.getType() == User.UserType.VISITOR) {
            mView.toLogin();
        } else {
            Retrofit retrofit = RetrofitClient.getInstance(mContext);
            IGetActivityInfoApi api = retrofit.create(IGetActivityInfoApi.class);
            Call<ApplyAndCollectResponseBean> call = api.collect(id);
            call.enqueue(new Callback<ApplyAndCollectResponseBean>() {
                @Override
                public void onResponse(Call<ApplyAndCollectResponseBean> call, Response<ApplyAndCollectResponseBean> response) {
                    ApplyAndCollectResponseBean bean = response.body();
                    if (bean != null) {
                        Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApplyAndCollectResponseBean> call, Throwable t) {

                }
            });
        }
    }
}
