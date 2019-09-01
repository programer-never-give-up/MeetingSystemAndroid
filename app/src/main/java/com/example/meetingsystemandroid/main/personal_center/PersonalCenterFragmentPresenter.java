package com.example.meetingsystemandroid.main.personal_center;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.meetingsystemandroid.login.LoginActivity;
import com.example.meetingsystemandroid.model.User;
import com.example.meetingsystemandroid.user_history.UserHistoryActivity;
import com.example.meetingsystemandroid.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PersonalCenterFragmentPresenter implements IPersonalCenterFragmentPresenter {

    private User mUser;
    private IPersonalCenterFragmentView mView;
    private Context mContext;

    public PersonalCenterFragmentPresenter(IPersonalCenterFragmentView view, Context context) {
        mView = view;
        mContext = context;
    }

    @Override
    public void checkUserStatus() {
        mUser = User.getInstance();
        mView.setUserCard(mUser.getAvatar(),
                mUser.getUserName(),
                mUser.typeToString());
        if (mUser.getType() == User.UserType.VISITOR) {
            mView.logoutButtonChange("登录");
        } else {
            mView.logoutButtonChange("取消登录");
        }
    }

    @Override
    public void toAllUserInfo() {

    }

    @Override
    public void toAttendHistory() {
        Intent intent = new Intent(mContext, UserHistoryActivity.class);
        intent.putExtra(UserHistoryActivity.USER_HISTORY_TAG, UserHistoryActivity.ATTEND_HISTORY);
        mContext.startActivity(intent);
    }

    @Override
    public void toOrganizeHistory() {
        Intent intent = new Intent(mContext, UserHistoryActivity.class);
        intent.putExtra(UserHistoryActivity.USER_HISTORY_TAG, UserHistoryActivity.ORGANIZE_HISTORY);
        mContext.startActivity(intent);
    }

    @Override
    public void loginOrLogout() {
        if (mUser.getType() == User.UserType.VISITOR) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            mContext.startActivity(intent);
            mView.close();
        } else {
            logout();
        }
    }

    public void logout() {

        Retrofit retrofit = RetrofitClient.getInstance(mContext);
        IPersonalCenterApi api = retrofit.create(IPersonalCenterApi.class);
        Call<LogoutBean> call = api.logout();
        call.enqueue(new Callback<LogoutBean>() {
            @Override
            public void onResponse(Call<LogoutBean> call, Response<LogoutBean> response) {
                LogoutBean bean = response.body();
                if (bean.isStatus()) {
                    // 退出登录 用户变为游客
                    User.setDefault();
                    checkUserStatus();
                    SharedPreferences sharedPreferences = mContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                } else {
                    Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LogoutBean> call, Throwable t) {
                Toast.makeText(mContext, "请求失败!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
