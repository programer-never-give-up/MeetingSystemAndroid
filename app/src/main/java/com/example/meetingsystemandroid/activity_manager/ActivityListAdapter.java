package com.example.meetingsystemandroid.activity_manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetingsystemandroid.R;

import java.util.ArrayList;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class ActivityListAdapter extends RecyclerView.Adapter<ActivityListAdapter.ActivityListItem> {

    private ManagerPresenter mPresenter;
    private Context mContext;
    private String mType;
    private ArrayList<ManagerResponseBean.ActivityInfo> mActivityList;

    public ActivityListAdapter(Context context, ManagerPresenter presenter, String type) {
        mContext = context;
        mPresenter = presenter;
        mType = type;
        mActivityList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ActivityListItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_manager_activity_list_item, parent, false);
        return new ActivityListItem(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityListItem holder, int position) {
//        holder.setActivityCard(mType, mActivityList.get(position));
    }

    @Override
    public int getItemCount() {
//        return mActivityList.size();
        return 5;
    }

    public void addActivities(ArrayList<ManagerResponseBean.ActivityInfo> activityList) {
        mActivityList.addAll(activityList);
        notifyDataSetChanged();
    }

    class ActivityListItem extends RecyclerView.ViewHolder {

        @BindView(R.id.manager_activity_list_item_name)
        TextView mActivityName;
        @BindView(R.id.manager_activity_list_item_start_time)
        TextView mActivityStartTime;
        @BindView(R.id.manager_activity_delete)
        Button mDeleteButton;
        @BindView(R.id.manager_activity_publish)
        Button mPublishButton;
        @BindView(R.id.manager_activity_detail)
        Button mDetailButton;
        @BindView(R.id.manager_activity_view_ticket)
        Button mViewTickectButton;
        @BindView(R.id.manager_activity_scan)
        Button mScanButton;

        private String mActivityId;

        public ActivityListItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setActivityCard(String type, ManagerResponseBean.ActivityInfo info) {
            mActivityId = info.getId();
            mActivityName.setText(info.getActivityName());
            mActivityStartTime.setText(info.getStartTime());
            // 设置删除按钮
            if (type.equals(ManagerActivity.ATTEND_PROCESSING) || type.equals(ManagerActivity.ORGANIZE_PROCESSING)) {
                mDeleteButton.setVisibility(View.GONE);
            }
            if (type.equals(ManagerActivity.ORGANIZE_INCHECK)) {
                mDeleteButton.setText("撤销审核");
            }
            // 设置主办方二维码扫描按钮
            if (!type.equals(ManagerActivity.ORGANIZE_PROCESSING)) {
                mScanButton.setVisibility(View.GONE);
            }
            // 参加者查看二维码按钮
            if (!type.equals(ManagerActivity.ATTEND_PROCESSING) && !type.equals(ManagerActivity.ATTEND_NOT_START)) {
                mViewTickectButton.setVisibility(View.GONE);
            }

            if (!type.equals(ManagerActivity.ORGANIZE_UNPUBLISHED)) {
                mPublishButton.setVisibility(View.GONE);
            }
        }

        // 删除活动或撤销审核
        @OnClick(R.id.manager_activity_delete)
        public void onClickDelete() {
            mPresenter.onDelete(mType);
        }

        // 未发布转为发布
        @OnClick(R.id.manager_activity_publish)
        public void onClickPublish() {
            mPresenter.publishActivity(mActivityId);
        }

        // 查看入场门票
        @OnClick(R.id.manager_activity_view_ticket)
        public void onClickViewTicket() {
            mPresenter.getTicket(mActivityId);
        }

        // 查看活动详情
        @OnClick(R.id.manager_activity_detail)
        public void onClickDetail() {
            mPresenter.showActivityInfo(mActivityId);
        }

        @OnClick(R.id.manager_activity_scan)
        public void onClickScan() {
            mPresenter.scanQRCode(mActivityId);
        }
    }
}
