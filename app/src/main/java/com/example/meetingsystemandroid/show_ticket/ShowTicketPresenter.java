package com.example.meetingsystemandroid.show_ticket;

import android.content.Context;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.meetingsystemandroid.activity_info.IShowAcitivityView;
import com.example.meetingsystemandroid.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShowTicketPresenter implements IShowTicketPresenter{
    private Context mContext;
    private IShowTicketView mView;

    public ShowTicketPresenter(IShowTicketView view, Context context) {
        mView = view;
        mContext = context;
    }


    @Override
    public void getQRCode(String id) {
        Retrofit retrofit = RetrofitClient.getInstance(mContext);
        IShowTicketApi api = retrofit.create(IShowTicketApi.class);
        Call<QRCodeResponseBean> call = api.getQRcode(id);
        call.enqueue(new Callback<QRCodeResponseBean>() {
            @Override
            public void onResponse(Call<QRCodeResponseBean> call, Response<QRCodeResponseBean> response) {
                QRCodeResponseBean bean = response.body();
                if (bean != null) {
                    mView.setQRCode(bean.getQrcode());
                } else {
                    Toast.makeText(mContext, "请求二维码失败！", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<QRCodeResponseBean> call, Throwable t) {

            }
        });
    }
}
