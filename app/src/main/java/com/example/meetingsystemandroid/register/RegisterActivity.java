package com.example.meetingsystemandroid.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.meetingsystemandroid.R;
import com.example.meetingsystemandroid.utils.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edit_register_username)
    TextInputEditText mUsernameEdit;
    @BindView(R.id.edit_register_password)
    TextInputEditText mPasswordEdit;
    @BindView(R.id.edit_register_email)
    TextInputEditText mEmailEdit;
    @BindView(R.id.edit_register_confirm_password)
    TextInputEditText mConfirmPasswordEdit;
    @BindView(R.id.edit_register_check_code)
    TextInputEditText mCheckCodeEdit;
    @BindView(R.id.edit_register_phone_number)
    TextInputEditText mPhoneNumberEdit;
    @BindView(R.id.edit_register_profession)
    TextInputEditText mProfessionEdit;
    @BindView(R.id.edit_register_address)
    TextInputEditText mAddressEdit;
    @BindView(R.id.radio_register_gender)
    RadioGroup mGenderRadio;
    @BindView(R.id.radio_register_type)
    RadioGroup mTypeRadio;
    @BindView(R.id.btn_register_send_mail)
    Button mSendMailBtn;
    @BindView(R.id.btn_register_submit)
    Button mRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @OnClick(R.id.btn_register_send_mail)
    public void sendMail() {
        String email = mEmailEdit.getText().toString();
        Retrofit retrofit = RetrofitClient.getInstance();
        IRegisterApi api = retrofit.create(IRegisterApi.class);
        Call<SendMailResponseBean> sendMailResponse = api.sendMail(email);
        sendMailResponse.enqueue(new Callback<SendMailResponseBean>() {
            @Override
            public void onResponse(Call<SendMailResponseBean> call, Response<SendMailResponseBean> response) {
                SendMailResponseBean bean = response.body();
                if (bean.isStatus_mail()) {
                    toastMessage("邮箱已被注册!");
                    return;
                }
                // 邮件发送成功 disable button开启120秒计时器
                if (bean.isSended()) {
                    // disable 和 设置button定时
                    mSendMailBtn.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        private int time = 120;
                        @Override
                        public void run() {
                            time--;
                            if (time <= 0) {
                                mSendMailBtn.setText("重新发送");
                                mSendMailBtn.setEnabled(true);
                                handler.removeCallbacks(this);
                            } else {
                                mSendMailBtn.setText(String.valueOf(time) + "秒后重新发送");
                                handler.postDelayed(this, 1000);
                            }
                        }
                    }, 1000);
                } else {
                    toastMessage(bean.getMessage());
                }
            }

            @Override
            public void onFailure(Call<SendMailResponseBean> call, Throwable t) {

            }
        });
    }

    @OnTextChanged(R.id.edit_register_check_code)
    public void sendCheckCode() {
        String checkCode = mCheckCodeEdit.getText().toString();
        Retrofit retrofit = RetrofitClient.getInstance();
        IRegisterApi api = retrofit.create(IRegisterApi.class);
        Call<CheckCodeResponseBean> call = api.checkCode(checkCode);
        call.enqueue(new Callback<CheckCodeResponseBean>() {
            @Override
            public void onResponse(Call<CheckCodeResponseBean> call, Response<CheckCodeResponseBean> response) {
                CheckCodeResponseBean bean = response.body();
                if (bean.isStatus_check()) {
                    toastMessage("验证码正确");
                    mSendMailBtn.setEnabled(true);
                } else {
                    toastMessage("验证码错误");
                }
            }

            @Override
            public void onFailure(Call<CheckCodeResponseBean> call, Throwable t) {

            }
        });
    }
    @OnClick(R.id.btn_register_submit)
    public void register() {
        HashMap<String, String> form = new HashMap<>();
        

    }

    private void toastMessage(String message) {
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
