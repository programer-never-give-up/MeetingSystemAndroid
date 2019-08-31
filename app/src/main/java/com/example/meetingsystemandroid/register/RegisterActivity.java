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

public class RegisterActivity extends AppCompatActivity implements IRegisterView{

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

    private RegisterPresenterCompl mRegisterPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mRegisterPresenter = new RegisterPresenterCompl(this, this);
    }

    @OnClick(R.id.btn_register_send_mail)
    public void sendMail() {
        String email = mEmailEdit.getText().toString();
        Log.d("aaa", "sendMail:"+email);
        mRegisterPresenter.sendMail(email);
    }

    @OnClick(R.id.btn_register_check_code_confirm)
    public void sendCheckCode() {
        String checkCode = mCheckCodeEdit.getText().toString();
        mRegisterPresenter.checkCode(checkCode);
    }
    @OnClick(R.id.btn_register_submit)
    public void register() {
        // 验证两次密码是否一致
        String password = mPasswordEdit.getText().toString();
        String confirmPassword = mConfirmPasswordEdit.getText().toString();
        if (!mRegisterPresenter.checkConfirmPassword(password, confirmPassword)) {
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

        mRegisterPresenter.register(form);
    }

    @Override
    public void sendMailButtonChange() {
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
    }

    @Override
    public void enableRegister() {
        mRegisterBtn.setEnabled(true);
    }

    @Override
    public void registerFinish() {
        finish();
    }


}
