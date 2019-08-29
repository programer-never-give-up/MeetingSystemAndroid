package com.example.meetingsystemandroid.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.meetingsystemandroid.R;
import com.example.meetingsystemandroid.utils.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.edit_register_company)
    TextInputEditText mCompanyEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_register_send_mail)
    public void sendMail() {
        String email = mEmailEdit.getText().toString();
        Retrofit retrofit = RetrofitClient.getInstance(this);
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
                toastMessage("请求失败");
            }
        });
    }

    @OnTextChanged(R.id.edit_register_check_code)
    public void sendCheckCode() {
        String checkCode = mCheckCodeEdit.getText().toString();
        Retrofit retrofit = RetrofitClient.getInstance(this);
        IRegisterApi api = retrofit.create(IRegisterApi.class);
        Call<CheckCodeResponseBean> call = api.checkCode(checkCode);
        call.enqueue(new Callback<CheckCodeResponseBean>() {
            @Override
            public void onResponse(Call<CheckCodeResponseBean> call, Response<CheckCodeResponseBean> response) {
                CheckCodeResponseBean bean = response.body();
                if (bean.isStatus_check()) {
                    toastMessage("验证码正确");
                    mRegisterBtn.setEnabled(true);
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
    @OnClick(R.id.btn_register_submit)
    public void register() {
        // 验证两次密码是否一致
        String password = mPasswordEdit.getText().toString();
        String confirmPassword = mConfirmPasswordEdit.getText().toString();
        if (!password.equals(confirmPassword)) {
            toastMessage("两次密码输入不一致");
            return;
        }
        // 表单制作
        HashMap<String, String> form = new HashMap<>();
        String username = mUsernameEdit.getText().toString();
        String email = mEmailEdit.getText().toString();
        String phoneNumber = mPhoneNumberEdit.getText().toString();
        String address = mAddressEdit.getText().toString();
        String profession = mProfessionEdit.getText().toString();
        String company = mCompanyEdit.getText().toString();
        RadioButton checkedGender = (RadioButton)findViewById(mGenderRadio.getCheckedRadioButtonId());
        String gender = checkedGender.getText().toString();
        RadioButton checkedType = (RadioButton)findViewById(mTypeRadio.getCheckedRadioButtonId());
        String type = checkedType.getText().toString();
        form.put("username", username);
        form.put("password", password);
        form.put("email", email);
        form.put("phone_number", phoneNumber);
        form.put("address", address);
        form.put("company", company);
        form.put("profession", profession);
        form.put("gender", gender);
        form.put("type", type);
        // post
        Retrofit retrofit = RetrofitClient.getInstance(this);
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
                    finish();
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

    private void toastMessage(String message) {
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
