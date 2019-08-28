package com.example.meetingsystemandroid.register;

public class SendMailResponseBean {
    private boolean isSended;
    private boolean status_mail;
    private String message;

    public boolean isSended() {
        return isSended;
    }

    public void setSended(boolean sended) {
        isSended = sended;
    }

    public boolean isStatus_mail() {
        return status_mail;
    }

    public void setStatus_mail(boolean status_mail) {
        this.status_mail = status_mail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
