package com.example.meetingsystemandroid.activity_manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;

import com.example.meetingsystemandroid.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ManagerActivity extends AppCompatActivity implements IManagerView{

    // 发布会议管理
    public static final String ORGANIZE_PROCESSING = " management-processing"; // 进行中
    public static final String ORGANIZE_UNPUBLISHED = "management-unpublished"; // 未发布
    public static final String ORGANIZE_PUBLISHED = "management-published"; // 已发布未开始
    public static final String ORGANIZE_INCHECK = "management-to_be_audited"; // 待审核
    // 参加会议管理
    public static final String ATTEND_PROCESSING = "my-processing"; // 进行中
    public static final String ATTEND_NOT_START = "my-not_start";
    // 我的收藏
    public static final String COLLECTION_NOT_START = "fav-not_start";
    public static final String COLLECTION_PROCESSING = "fav-processing";
    public static final String COLLECTION_FINISHED = "fav-finished";

    // 活动类型
    private String mType;

    @BindView(R.id.manager_activity_list)
    RecyclerView mActivityList;

    private ActivityListAdapter mAdapter;
    private ManagerPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        ButterKnife.bind(this);
        mPresenter = new ManagerPresenter(this, this);
        mActivityList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ActivityListAdapter(this, mPresenter, mType);
        mActivityList.setAdapter(mAdapter);
    }

    @Override
    public void setArrayList(ManagerResponseBean bean) {
        mAdapter.addActivities(new ArrayList<ManagerResponseBean.ActivityInfo>(Arrays.asList(bean.getActivities())));
    }

    @Override
    public void showTicket() {

    }
}
