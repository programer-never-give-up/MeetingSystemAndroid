package com.example.meetingsystemandroid.user_history;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meetingsystemandroid.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserHistoryActivity extends AppCompatActivity implements IUserHistoryView{

    @BindView(R.id.user_history_list)
    RecyclerView mHistoryList;


    private UserHistoryListAdapter mAdapter;

    public static final int ORGANIZE_HISTORY = 0;
    public static final int ATTEND_HISTORY = 1;

    private UserHistoryPresenter mPresenter;

    public static final String USER_HISTORY_TAG = "type";

    private int mType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history);
        ButterKnife.bind(this);
        mPresenter = new UserHistoryPresenter(this, this);
        mHistoryList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new UserHistoryListAdapter(this, mPresenter);
        mHistoryList.setAdapter(mAdapter);

        // 初始化数据
        mType = getIntent().getIntExtra(USER_HISTORY_TAG, 0);
        mPresenter.init(mType);
    }

    @Override
    public void onSuccess(ArrayList<UserHistoryBean.HistoryActivity> historyList) {
        mAdapter.addHistoryList(historyList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed() {
        Toast.makeText(this,"请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setActionBarTitle(String title) {
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setTitle(title);
            // 设置返回按钮
            bar.setDisplayHomeAsUpEnabled(true);
        }
    }

    // 设置顶部导航栏返回按钮的监听事件
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
