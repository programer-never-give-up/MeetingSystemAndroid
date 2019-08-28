package com.example.meetingsystemandroid.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.meetingsystemandroid.main.MainActivity;
import com.example.meetingsystemandroid.R;
import com.example.meetingsystemandroid.register.RegisterActivity;
import com.example.meetingsystemandroid.utils.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edit_login_username)  TextInputEditText mUsernameEdit;
    @BindView(R.id.edit_login_password)  TextInputEditText mPasswordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void login(View view) {
        // 获取用户名密码
        String username = mUsernameEdit.getText().toString();
        String password = mPasswordEdit.getText().toString();
        // retrofit 测试
        Retrofit retrofit = RetrofitClient.getInstance();
        ILoginApi api = retrofit.create(ILoginApi.class);
        Call<LoginResponseBean> loginResponse = api.login(username, password);
        loginResponse.enqueue(new Callback<LoginResponseBean>() {
            @Override
            public void onResponse(Call<LoginResponseBean> call, Response<LoginResponseBean> response) {
                LoginResponseBean loginResponseBean = response.body();
                if (loginResponseBean.isStatus()) {
                    // 登录成功跳转到主页面
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this,
                            response.body().getMessage(),
                            Toast.LENGTH_LONG)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseBean> call, Throwable t) {
            }
        });
    }
    @OnClick(R.id.btn_register)
    public void toRegister(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
