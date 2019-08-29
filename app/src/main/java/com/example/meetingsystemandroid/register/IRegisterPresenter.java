package com.example.meetingsystemandroid.register;

import java.util.HashMap;

public interface IRegisterPresenter {
    public boolean checkConfirmPassword(String password, String confirmPassword);
    // 注册
    public void register(HashMap <String, String> form);
    // 发送邮件
    public void sendMail(String email);
    // 发送邮件验证码
    public void checkCode(String code);
}
