package com.example.meetingsystemandroid.activity_manager;

public class ChangeActivityStateResponseBean {
    private boolean act_status;
    private String message;

    public boolean isAct_status() {
        return act_status;
    }

    public void setAct_status(boolean act_status) {
        this.act_status = act_status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
