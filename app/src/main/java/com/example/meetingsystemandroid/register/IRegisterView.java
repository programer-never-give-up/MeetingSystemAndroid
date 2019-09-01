package com.example.meetingsystemandroid.register;

public interface IRegisterView {
    // disable发送按钮，并倒计时
    public void sendMailButtonChange();
    public void enableRegister();
    public void registerFinish();
    public void setActionBar();
}
