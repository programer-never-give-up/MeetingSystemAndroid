package com.example.meetingsystemandroid.login;

public interface ILoginPresenter {
    // 输入密码登录
    public void login(String username, String password);

    // 自动登录
    public void autoLogin();

    public void toastMessage(String message);

    public void redirectToRegister();
}
