package com.example.meetingsystemandroid.login;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.meetingsystemandroid.main.MainActivity;
import com.example.meetingsystemandroid.register.RegisterActivity;
import com.example.meetingsystemandroid.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginPresenterCompl implements ILoginPresenter {

    private ILoginView mloginView;
    private Context mContext;

    public LoginPresenterCompl(ILoginView view, Context context) {
        mloginView = view;
        mContext = context;
    }
    @Override
    public void login(String username, String password) {
        // retrofit 测试
        mloginView.showLoading();
        Retrofit retrofit = RetrofitClient.getInstance(mContext);
        ILoginApi api = retrofit.create(ILoginApi.class);
        Call<LoginResponseBean> loginResponse = api.login(username, password);
        loginResponse.enqueue(new Callback<LoginResponseBean>() {
            @Override
            public void onResponse(Call<LoginResponseBean> call, Response<LoginResponseBean> response) {
                LoginResponseBean loginResponseBean = response.body();
                if (loginResponseBean != null) {
                    if (loginResponseBean.isStatus()) {
                        // 登录成功跳转到主页面
                        Intent intent = new Intent(mContext, MainActivity.class);
                        mContext.startActivity(intent);
                    } else {
                        toastMessage(response.body().toString());
                    }
                }
                mloginView.dismissLoading();

            }

            @Override
            public void onFailure(Call<LoginResponseBean> call, Throwable t) {
                toastMessage("网络请求失败");
                mloginView.dismissLoading();
            }
        });
    }

    @Override
    public void autoLogin() {
    }

    @Override
    public void toastMessage(String message) {
        Toast.makeText(mContext,
                message,
                Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void redirectToRegister() {
        Intent intent = new Intent(mContext, RegisterActivity.class);
        mContext.startActivity(intent);
    }
}
