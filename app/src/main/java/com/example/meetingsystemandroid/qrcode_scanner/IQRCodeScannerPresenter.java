package com.example.meetingsystemandroid.qrcode_scanner;

public interface IQRCodeScannerPresenter {

    // 获取二维码信息，判断user是否有入场资格 弹出对话框
    public void onScanSuccess(String id);

    // 动态申请权限
    public void requestQRCodePermissions();
}
