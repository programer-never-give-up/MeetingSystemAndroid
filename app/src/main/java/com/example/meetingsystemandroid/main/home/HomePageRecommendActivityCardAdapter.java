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

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageRecommendActivityCardAdapter extends RecyclerView.Adapter<HomePageRecommendActivityCardAdapter.RecommendActivityCardViewHolder> {

    private Context mContext;
    private HomePagePresenter mPrensenter;
    private boolean isNeedUpdate = false;

    public boolean isNeedUpdate() {
        return isNeedUpdate;
    }

    public void setNeedUpdate(boolean needUpdate) {
        isNeedUpdate = needUpdate;
    }

    public HomePageRecommendActivityCardAdapter(Context context, HomePagePresenter presenter) {
        this.mContext = context;
        mPrensenter = presenter;
    }
    @NonNull
    @Override
    public RecommendActivityCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_home_recommend_activtity_item, parent, false);
        return new RecommendActivityCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendActivityCardViewHolder holder, int position) {
        holder.setActivityInfo(mContext);
    }

    public void addRecommendActivities() {
        // 留下函数之后填充
    }

    @Override
    public int getItemCount() {
        return 5;
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

        RecommendActivityCardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        // 传入Activity对象
        void setActivityInfo(Context context) {
            // 动态加载图片
            Glide.with(context)
                    .load("http://139.219.14.146:8001/default.jpg")
                    .into(mActivityLogo);
            mActivityName.setText("东南大学九龙湖校区软件发布会");
            mActivityLocation.setText("江苏 南京");
            mActivityTime.setText("2019-10-1 9:00");
        }
    }
}
