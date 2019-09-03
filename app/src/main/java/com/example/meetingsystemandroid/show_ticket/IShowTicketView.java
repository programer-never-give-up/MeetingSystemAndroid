package com.example.meetingsystemandroid.show_ticket;

public interface IShowTicketView {
    // 设置intent中的信息(logo, name)
    public void init();

    public void setQRCode(String url);
}
