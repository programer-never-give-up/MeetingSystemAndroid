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
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityListAdapter extends RecyclerView.Adapter<ActivityListAdapter.ActivityListItem> {

    private ManagerPresenter mPresenter;
    private Context mContext;
    private String mType;
    private ArrayList<ManagerActivityInfoResponseBean.ActivityInfo> mActivityList;

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
        holder.setActivityCard(mType, mActivityList.get(position));
    }

    @Override
    public int getItemCount() {
        return mActivityList.size();
//        return 5;
    }

    public void addActivities(ArrayList<ManagerActivityInfoResponseBean.ActivityInfo> activityList) {
        mActivityList.addAll(activityList);
        notifyDataSetChanged();
    }

    public void deleteActivity(String id) {
        Iterator<ManagerActivityInfoResponseBean.ActivityInfo> iter = mActivityList.iterator();
        while(iter.hasNext()) {
            ManagerActivityInfoResponseBean.ActivityInfo info = iter.next();
            if (info.getId().equals(id)) {
                iter.remove();
                break;
            }
        }
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
        private String mActivityLogo;
        private String mName;

        public ActivityListItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setActivityCard(String type, ManagerActivityInfoResponseBean.ActivityInfo info) {
            mActivityId = info.getId();
            mActivityLogo = info.getLogoSrc();
            mName = info.getActivityName();
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
            // 为了测试注释掉了
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
            mPresenter.onDelete(mType, mActivityId);
        }

        // 未发布转为发布
        @OnClick(R.id.manager_activity_publish)
        public void onClickPublish() {
            mPresenter.publishActivity(mActivityId);
        }

        // 查看入场门票
        @OnClick(R.id.manager_activity_view_ticket)
        public void onClickViewTicket() {
            mPresenter.getTicket(mActivityId, mName, mActivityLogo);
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
