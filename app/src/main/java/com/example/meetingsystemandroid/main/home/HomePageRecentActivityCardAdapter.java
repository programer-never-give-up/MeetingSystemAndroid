package com.example.meetingsystemandroid.main.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meetingsystemandroid.R;
import com.example.meetingsystemandroid.utils.RetrofitClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomePageRecentActivityCardAdapter extends RecyclerView.Adapter<HomePageRecentActivityCardAdapter.RecentActivityCardViewHolder> {

    private Context mContext;
    private HomePagePresenter mPresenter;
    private ArrayList<HomePageActivitiesBean.HomePageActivity> mList;
    private boolean isNeedUpdate = true;

    public boolean isNeedUpdate() {
        return isNeedUpdate;
    }

    public void setNeedUpdate(boolean needUpdate) {
        isNeedUpdate = needUpdate;
    }

    HomePageRecentActivityCardAdapter(Context context, HomePagePresenter presenter) {
        mContext = context;
        mPresenter = presenter;
        mList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecentActivityCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_home_recent_activity_item, parent, false);
        return new RecentActivityCardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentActivityCardViewHolder holder, int position) {
        holder.setRecentActivityCard(mList.get(position));
    }

    public void addRecentActivities(ArrayList<HomePageActivitiesBean.HomePageActivity> list) {
        mList.addAll(list);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class RecentActivityCardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recent_activity_card_logo)
        ImageView mLogo;
        @BindView(R.id.recent_activity_card_activty_name)
        TextView mActivityName;
        @BindView(R.id.recent_activity_card_activty_time)
        TextView mActivityTime;

        private String mActivityId;

        public RecentActivityCardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setRecentActivityCard(HomePageActivitiesBean.HomePageActivity bean) {
            Glide.with(mContext).load(RetrofitClient.BASE_URL + bean.getLogo()).into(mLogo);
            mActivityId = bean.getUuid_act();
            mActivityName.setText(bean.getName_act());
            mActivityTime.setText(bean.getStart_time());
        }

        @OnClick(R.id.recent_activity_card_activty_click)
        public void toActivityInfo() {
            if (mPresenter == null) {
                Toast.makeText(mContext, "presenter null", Toast.LENGTH_SHORT).show();
                return;
            }
            mPresenter.toActivityInfo(mActivityId);
        }
    }
}
