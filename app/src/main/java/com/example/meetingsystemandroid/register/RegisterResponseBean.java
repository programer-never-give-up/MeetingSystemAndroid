package com.example.meetingsystemandroid.register;

public class RegisterResponseBean {
    private boolean status;
    private String message;
    private boolean status_username;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus_username() {
        return status_username;
    }

    public void setStatus_username(boolean status_username) {
        this.status_username = status_username;
    }
}
