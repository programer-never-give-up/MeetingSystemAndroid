package com.example.meetingsystemandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.meetingsystemandroid.bean.LoginBean;
import com.example.meetingsystemandroid.utils.BackendApi;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private Button mLoginButton;
    private Button mRegisterButton;
    private TextInputEditText mUsernameEdit;
    private TextInputEditText mPasswordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginButton = findViewById(R.id.btn_login);
        mRegisterButton = findViewById(R.id.btn_register);
        mUsernameEdit = findViewById(R.id.edit_login_username);
        mPasswordEdit = findViewById(R.id.edit_login_password);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取用户名密码
                String username = mUsernameEdit.getText().toString();
                String password = mPasswordEdit.getText().toString();
                // retrofit 测试
                Retrofit retrofit =new Retrofit.Builder()
                        .baseUrl("http://139.219.14.146/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                BackendApi api = retrofit.create(BackendApi.class);
                Call<LoginBean> loginResponse = api.login(username, password);
                loginResponse.enqueue(new Callback<LoginBean>() {
                    @Override
                    public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                        LoginBean loginBean = response.body();
                        if (loginBean.isStatus()) {
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
                    public void onFailure(Call<LoginBean> call, Throwable t) {
                    }
                });
            }
        });
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
