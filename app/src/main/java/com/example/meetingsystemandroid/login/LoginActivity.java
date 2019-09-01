package com.example.meetingsystemandroid.login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meetingsystemandroid.main.MainActivity;
import com.example.meetingsystemandroid.R;
import com.example.meetingsystemandroid.register.RegisterActivity;
import com.example.meetingsystemandroid.utils.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView{

    @BindView(R.id.edit_login_username)
    TextInputEditText mUsernameEdit;
    @BindView(R.id.edit_login_password)
    TextInputEditText mPasswordEdit;
    @BindView(R.id.check_login_auto)
    CheckBox mAutoLoginCheck;
    @BindView(R.id.check_login_remember_pass)
    CheckBox mRememberPassword; //记住密码

    private AlertDialog mLoadingDialog;
    private LoginPresenterCompl mLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenterCompl(this, this);
        mLoginPresenter.autoLogin();
        setActionBarTitle();
    }

    @OnClick(R.id.btn_login)
    public void login(View view) {
        // 获取用户名密码
        String username = mUsernameEdit.getText().toString();
        String password = mPasswordEdit.getText().toString();
        if (mAutoLoginCheck.isChecked()) {
            mLoginPresenter.saveLoginInfo(username, password);
        }
        mLoginPresenter.login(username, password);

    }
    @OnClick(R.id.btn_register)
    public void toRegister(View view) {
        mLoginPresenter.redirectToRegister();
    }

    // view管理
    @Override
    public void showLoading() {
        int llPadding = 30;
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setPadding(llPadding, llPadding, llPadding, llPadding);
        ll.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams llParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        llParam.gravity = Gravity.CENTER;
        ll.setLayoutParams(llParam);

        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(true);
        progressBar.setPadding(0, 0, llPadding, 0);
        progressBar.setLayoutParams(llParam);

        llParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        llParam.gravity = Gravity.CENTER;
        TextView tvText = new TextView(this);
        tvText.setText("Loading ...");
        tvText.setTextColor(Color.parseColor("#000000"));
        tvText.setTextSize(20);
        tvText.setLayoutParams(llParam);

        ll.addView(progressBar);
        ll.addView(tvText);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setView(ll);

        mLoadingDialog = builder.create();
        mLoadingDialog.show();
        Window window = mLoadingDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(mLoadingDialog.getWindow().getAttributes());
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            mLoadingDialog.getWindow().setAttributes(layoutParams);
        }
    }

    @Override
    public void dismissLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void closeLogin() {
        finish();
    }

    @Override
    public void setActionBarTitle() {
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setTitle(R.string.login);
        }
    }


}
