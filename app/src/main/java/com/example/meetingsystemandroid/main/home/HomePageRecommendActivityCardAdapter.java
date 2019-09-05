package com.example.meetingsystemandroid.main.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meetingsystemandroid.R;
import com.example.meetingsystemandroid.utils.RetrofitClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageRecommendActivityCardAdapter extends RecyclerView.Adapter<HomePageRecommendActivityCardAdapter.RecommendActivityCardViewHolder> {

    private Context mContext;
    private HomePagePresenter mPrensenter;
    private boolean isNeedUpdate = true;
    private ArrayList<HomePageActivitiesBean.HomePageActivity> mList;

    public boolean isNeedUpdate() {
        return isNeedUpdate;
    }

    public void setNeedUpdate(boolean needUpdate) {
        isNeedUpdate = needUpdate;
    }

    public HomePageRecommendActivityCardAdapter(Context context, HomePagePresenter presenter) {
        this.mContext = context;
        mPrensenter = presenter;
        mList = new ArrayList<>();
    }
    @NonNull
    @Override
    public RecommendActivityCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_home_recommend_activtity_item, parent, false);
        return new RecommendActivityCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendActivityCardViewHolder holder, int position) {
        holder.setActivityInfo(mList.get(position));
    }

    public void addRecommendActivities(ArrayList<HomePageActivitiesBean.HomePageActivity> list) {
        // 留下函数之后填充
        mList.addAll(list);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

     class RecommendActivityCardViewHolder extends RecyclerView.ViewHolder  {

        @BindView(R.id.activity_card_activty_name)
        TextView mActivityName;
        @BindView(R.id.activity_card_activty_time)
        TextView mActivityTime;
        @BindView(R.id.activity_card_activty_location)
        TextView mActivityLocation;
        @BindView(R.id.activity_card_logo)
        ImageView mActivityLogo;

        private String mActivityId;

        RecommendActivityCardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        // 传入Activity对象
        void setActivityInfo(HomePageActivitiesBean.HomePageActivity bean) {
            // 动态加载图片
            Glide.with(this.itemView)
                    .load(RetrofitClient.BASE_URL + bean.getLogo())
                    .into(mActivityLogo);
            mActivityName.setText(bean.getName_act());
            mActivityLocation.setText(bean.getLocation());
            mActivityTime.setText(bean.getStart_time());
            mActivityId = bean.getUuid_act();
        }
    }
}
