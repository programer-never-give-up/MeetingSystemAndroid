package com.example.meetingsystemandroid.show_ticket;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.meetingsystemandroid.R;
import com.example.meetingsystemandroid.utils.RetrofitClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowTicketActivity extends AppCompatActivity implements IShowTicketView{

    public static final String TICKET_ACTIVITY_ID = "ticket_id";
    public static final String TICKET_ACTIVITY_LOGO = "ticket_logo";
    public static final String TICKET_ACTIVITY_NAME = "ticket_name";

    @BindView(R.id.show_ticket_activity_logo)
    ImageView mActivityLogo;
    @BindView(R.id.show_ticket_activity_name)
    TextView mActivityName;
    @BindView(R.id.show_ticket_qrcode)
    ImageView mQRCode;

    private String mActivityId;
    private ShowTicketPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ticket);
        ButterKnife.bind(this);
        mActivityId = getIntent().getStringExtra(TICKET_ACTIVITY_ID);
        mPresenter = new ShowTicketPresenter(this, this);
        setActionBar();
        init();
        mPresenter.getQRCode(mActivityId);
//        setQRCode("user/227af7b8-c7fc-11e9-ba32-887873aca633/theqrcode.png");
    }

    @Override
    public void init() {
        mActivityName.setText(getIntent().getStringExtra(TICKET_ACTIVITY_NAME));
        Glide.with(this)
                .load(RetrofitClient.BASE_URL + getIntent().getStringExtra(TICKET_ACTIVITY_LOGO))
                .into(mActivityLogo);
    }

    @Override
    public void setQRCode(String url) {
        Glide.with(this)
                .load(RetrofitClient.BASE_URL + url)
                .into(mQRCode);
    }

    public void setActionBar() {
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setTitle("二维码查看");
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
}
