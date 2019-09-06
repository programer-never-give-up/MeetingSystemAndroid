package com.example.meetingsystemandroid.qrcode_scanner;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.CheckBox;

import com.example.meetingsystemandroid.utils.RetrofitClient;

import java.util.logging.LogRecord;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static java.lang.Thread.sleep;

public class QRCodeScannerPresenter implements IQRCodeScannerPresenter{

    private Context mContext;
    private IQRCodeScannerView mView;
    private String mActivityId;

    public QRCodeScannerPresenter(IQRCodeScannerView view, Context context, String id) {
        mContext = context;
        mView = view;
        mActivityId = id;
    }


    @Override
    public void onScanSuccess(String id) {
        mView.showLoading();
        // 为了调试 延迟执行看效果 之后直接请求服务器
        Retrofit retrofit = RetrofitClient.getInstance(mContext);
        IQRCodeScanApi api = retrofit.create(IQRCodeScanApi.class);
        Call<CheckQRCodeResponseBean> call = api.checkQRCode(mActivityId, id);
        call.enqueue(new Callback<CheckQRCodeResponseBean>() {
            @Override
            public void onResponse(Call<CheckQRCodeResponseBean> call, Response<CheckQRCodeResponseBean> response) {
                CheckQRCodeResponseBean bean = response.body();
                if (bean != null) {
                    mView.dismissLoading();
                    if (bean.isStatus()) {
                        mView.showResult(true);
                    } else {
                        mView.showResult(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<CheckQRCodeResponseBean> call, Throwable t) {

            }
        });
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
