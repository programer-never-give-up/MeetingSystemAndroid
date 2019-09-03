package com.example.meetingsystemandroid.qrcode_scanner;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import java.util.logging.LogRecord;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static java.lang.Thread.sleep;

public class QRCodeScannerPresenter implements IQRCodeScannerPresenter{

    private Context mContext;
    private IQRCodeScannerView mView;

    public QRCodeScannerPresenter(IQRCodeScannerView view, Context context) {
        mContext = context;
        mView = view;
    }


    @Override
    public void onScanSuccess(String id) {
        mView.showLoading();
        // 为了调试 延迟执行看效果 之后直接请求服务器
        boolean handler = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.dismissLoading();
                mView.showResult(false);
            }
        }, 3*1000);
    }

    @AfterPermissionGranted(QRCodeScannerActivity.REQUEST_CODE_QRCODE_PERMISSIONS)
    @Override
    public void requestQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(mContext, perms)) {
            EasyPermissions.requestPermissions((Activity) mContext, "扫描二维码需要打开相机和散光灯的权限", QRCodeScannerActivity.REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }
}
