package com.example.meetingsystemandroid.register;

public class CheckCodeResponseBean {
    private boolean status_check;
    private String message;

    public boolean isStatus_check() {
        return status_check;
    }

    public void setStatus_check(boolean status_check) {
        this.status_check = status_check;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}