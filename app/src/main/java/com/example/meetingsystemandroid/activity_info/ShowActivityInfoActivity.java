package com.example.meetingsystemandroid.activity_info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meetingsystemandroid.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    private ShowFileListAdapter mAdapter;
    private ShowActivityPresenter mPresenter;

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
        mPresenter.setActivity(getIntent().getStringExtra("id"));
    }


    @Override
    public void onGetInfoSuccess(ActivityBean bean) {
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
}
