package com.example.meetingsystemandroid.qrcode_scanner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.meetingsystemandroid.R;

import java.util.List;

import butterknife.Action;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class QRCodeScannerActivity extends AppCompatActivity implements QRCodeView.Delegate, EasyPermissions.PermissionCallbacks,IQRCodeScannerView {


    public static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
    public static final String QRCODE_TAG = "id";

    @BindView(R.id.zxingview)
    ZXingView mZXingView;

    private QRCodeScannerPresenter mPresenter;
    private SweetAlertDialog pDialog;

    // 当前会议的id
    private String mActivityId;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_scanner);
        ButterKnife.bind(this);
        mActivityId = getIntent().getStringExtra(QRCODE_TAG);
        mZXingView.setDelegate(this);
        mPresenter = new QRCodeScannerPresenter(this, this, mActivityId);
        mPresenter.requestQRCodePermissions();

        setActionBar();
    }

    private void setActionBar() {
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setTitle(R.string.manager_activity_scan);
            bar.setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        mZXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//        mZXingView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别
        mZXingView.startSpot(); // 显示扫描框，并开始识别
    }

    @Override
    protected void onStop() {
        mZXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }



    @Override
    protected void onDestroy() {
        mZXingView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }


    @Override
    public void onScanQRCodeSuccess(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        mPresenter.onScanSuccess(result);
//        mZXingView.startSpotAndShowRect();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        // 权限申请成功时打开摄像头
        mZXingView.startCamera();
        mZXingView.startSpot();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void showLoading() {
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (pDialog != null) {
            pDialog.dismiss();
        }
    }

    @Override
    public void showResult(boolean isSuccess) {
        if (isSuccess) {
            new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("验证通过")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            mZXingView.startSpot();
                            sweetAlertDialog.dismiss();
                        }
                    })
                    .show();
        } else {
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("验证不通过")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            mZXingView.startSpot();
                            sweetAlertDialog.dismiss();
                        }
                    })
                    .show();
        }

    }
}
