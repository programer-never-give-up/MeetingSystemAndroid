package com.example.meetingsystemandroid.activity_info;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.meetingsystemandroid.R;
import com.example.meetingsystemandroid.login.LoginActivity;
import com.example.meetingsystemandroid.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowActivityInfoActivity extends AppCompatActivity implements IShowAcitivityView{

    @BindView(R.id.show_activity_logo)
    ImageView mLogo;
    @BindView(R.id.show_activity_title)
    TextView mTitle;
    @BindView(R.id.show_activity_introduction)
    TextView mIntroduction;
    @BindView(R.id.show_activity_location)
    TextView mLocation;
    @BindView(R.id.show_activity_time)
    TextView mTime;
    @BindView(R.id.show_activity_organizer)
    TextView mOrganizer;
    @BindView(R.id.show_activity_file_list)
    RecyclerView mFileList;

    public static final String SHOW_ACTIVITY_TAG = "id";
    private ShowFileListAdapter mAdapter;
    private ShowActivityPresenter mPresenter;
    private String mActivityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_activity_info);
        ButterKnife.bind(this);
        mPresenter = new ShowActivityPresenter(this, this);
        mAdapter = new ShowFileListAdapter(this, mPresenter);
        mFileList.setLayoutManager(new LinearLayoutManager(this));
        mFileList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mFileList.setAdapter(mAdapter);
        mFileList.setNestedScrollingEnabled(false);
        mActivityId = getIntent().getStringExtra(SHOW_ACTIVITY_TAG);
        mPresenter.setActivity(mActivityId);
        setActionBarTitle();
    }


    @Override
    public void onGetInfoSuccess(ActivityBean bean) {
        Glide.with(this)
                .load(RetrofitClient.BASE_URL + bean.getLogo())
                .into(mLogo);
        mLocation.setText(bean.getLocation());
        mTitle.setText(bean.getName());
        mOrganizer.setText(bean.getOrganizer());
        mIntroduction.setText(bean.getIntroduction());
        mTime.setText(bean.getStart_time()+" 至 "+bean.getEnd_time());
        mAdapter.setFileList(new ArrayList<ActivityBean.ActivityFile>(Arrays.asList(bean.getFiles())));
    }

    @Override
    public void onGetInfoFailed() {
        Toast.makeText(this, "获取数据失败！", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void setActionBarTitle() {
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void toLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
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

    // 参加会议
    @OnClick(R.id.btn_show_activity_attend)
    void attendActivity() {
        mPresenter.attendActivity(mActivityId);
    }

    @OnClick(R.id.btn_show_activity_collect)
    void collectActivity() {
        mPresenter.collectActivity(mActivityId);
    }
}
