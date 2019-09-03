package com.example.meetingsystemandroid.qrcode_scanner;

public interface IQRCodeScannerView {
    // 开始请求服务器确认，弹出对话框处于loading状态
    public void showLoading();
    // 关闭loading对话框 显示结果
    public void dismissLoading();
    // 弹出结果对话框 设置成功还是失败
    public void showResult(boolean isSuccess);
}
