package com.example.meetingsystemandroid.activity_manager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.meetingsystemandroid.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ManagerActivity extends AppCompatActivity implements IManagerView{

    public static final String ACTIVITY_TYPE_TAG = "type";
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
        mType = getIntent().getStringExtra(ACTIVITY_TYPE_TAG);
        mAdapter = new ActivityListAdapter(this, mPresenter, mType);
        mActivityList.setAdapter(mAdapter);
        setActionBarTitle();
    }

    @Override
    public void setArrayList(ManagerResponseBean bean) {
        mAdapter.addActivities(new ArrayList<ManagerResponseBean.ActivityInfo>(Arrays.asList(bean.getActivities())));
    }

    @Override
    public void showTicket() {

    }

    @Override
    public void setActionBarTitle() {
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            String title;
            switch (mType) {
                case ORGANIZE_INCHECK:
                    title = "活动发布(审核中)";
                    break;
                case ORGANIZE_PROCESSING:
                    title = "活动发布(进行中)";
                    break;
                case ORGANIZE_PUBLISHED:
                    title = "活动发布(已发布)";
                    break;
                case ORGANIZE_UNPUBLISHED:
                    title = "活动发布(未发布)";
                    break;
                case ATTEND_NOT_START:
                    title = "活动参加(未开始)";
                    break;
                case ATTEND_PROCESSING:
                    title = "活动参加(进行中)";
                    break;
                case COLLECTION_FINISHED:
                    title = "活动收藏(已结束)";
                    break;
                case COLLECTION_NOT_START:
                    title = "活动收藏(未开始)";
                    break;
                case COLLECTION_PROCESSING:
                    title = "活动收藏(进行中)";
                    break;
                default:
                    title = "活动列表";
            }
            bar.setTitle(title);
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
