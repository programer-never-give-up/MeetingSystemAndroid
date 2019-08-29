package com.example.meetingsystemandroid.register;

import android.content.Context;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.meetingsystemandroid.utils.RetrofitClient;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterPresenterCompl implements IRegisterPresenter {

    private IRegisterView mRegisterView;
    private Context mContext;

    public RegisterPresenterCompl(IRegisterView view, Context context) {
        mRegisterView = view;
        mContext = context;
    }

    @Override
    public boolean checkConfirmPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            toastMessage("两次密码输入不一致");
            return false;
        }
        return true;
    }

    @Override
    public void register(HashMap<String, String> form) {
        Retrofit retrofit = RetrofitClient.getInstance(mContext);
        IRegisterApi api = retrofit.create(IRegisterApi.class);
        Call<RegisterResponseBean> call = api.register(form);
        call.enqueue(new Callback<RegisterResponseBean>() {
            @Override
            public void onResponse(Call<RegisterResponseBean> call, Response<RegisterResponseBean> response) {
                RegisterResponseBean bean = response.body();
                if (bean.isStatus_username()) {
                    toastMessage("用户名已被注册");
                    return;
                }
                if (bean.isStatus()) {
                    toastMessage("注册成功");
                    mRegisterView.registerFinish();
                } else {
                    toastMessage(bean.getMessage());
                    return;
                }

            }

            @Override
            public void onFailure(Call<RegisterResponseBean> call, Throwable t) {
                toastMessage("请求失败");
            }
        });
    }

    public void toastMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendMail(String email) {

        Retrofit retrofit = RetrofitClient.getInstance(mContext);
        IRegisterApi api = retrofit.create(IRegisterApi.class);
        Call<SendMailResponseBean> sendMailResponse = api.sendMail(email);
        sendMailResponse.enqueue(new Callback<SendMailResponseBean>() {
            @Override
            public void onResponse(Call<SendMailResponseBean> call, Response<SendMailResponseBean> response) {
                SendMailResponseBean bean = response.body();
                if (bean != null) {
                    if (bean.isStatus_mail()) {
                        toastMessage("邮箱已被注册!");
                        return;
                    }
                    // 邮件发送成功 disable button开启120秒计时器
                    if (bean.isSended()) {
                        // disable 和 设置button定时
                        mRegisterView.sendMailButtonChange();
                    } else {
                        toastMessage(bean.getMessage());
                    }
                } else {
                    toastMessage("请求错误");
                }
            }

            @Override
            public void onFailure(Call<SendMailResponseBean> call, Throwable t) {
                toastMessage("请求失败");
            }
        });
    }

    @Override
    public void checkCode(String code) {
        Retrofit retrofit = RetrofitClient.getInstance(mContext);
        IRegisterApi api = retrofit.create(IRegisterApi.class);
        Call<CheckCodeResponseBean> call = api.checkCode(code);
        call.enqueue(new Callback<CheckCodeResponseBean>() {
            @Override
            public void onResponse(Call<CheckCodeResponseBean> call, Response<CheckCodeResponseBean> response) {
                CheckCodeResponseBean bean = response.body();
                if (bean.isStatus_check()) {
                    toastMessage("验证码正确");
                    mRegisterView.enableRegister();
                } else {
                    toastMessage("验证码错误");
                }
            }

            @Override
            public void onFailure(Call<CheckCodeResponseBean> call, Throwable t) {
                toastMessage("请求失败");
            }
        });
    }
}
