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

public class HomePageActivityCardAdapter extends RecyclerView.Adapter<HomePageActivityCardAdapter.ActivityCardViewHolder> {

    private Context mContext;

    public HomePageActivityCardAdapter(Context context) {
        this.mContext = context;
    }
    @NonNull
    @Override
    public ActivityCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_home_activtity_item, parent, false);
        return new ActivityCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityCardViewHolder holder, int position) {
        holder.setActivityInfo(mContext);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

     class ActivityCardViewHolder extends RecyclerView.ViewHolder  {

        @BindView(R.id.activity_card_activty_name)
        TextView mActivityName;
        @BindView(R.id.activity_card_activty_time)
        TextView mActivityTime;
        @BindView(R.id.activity_card_activty_location)
        TextView mActivityLocation;
        @BindView(R.id.activity_card_logo)
        ImageView mActivityLogo;

        private ActivityCardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        // 传入Activity对象
        public void setActivityInfo(Context context) {
            // 动态加载图片
            Glide.with(context)
                    .load("http://139.219.14.146/avatar/227af7b8-c7fc-11e9-ba32-887873aca633.png")
                    .into(mActivityLogo);
            mActivityName.setText("会议名称");
            mActivityLocation.setText("江苏 南京");
            mActivityTime.setText("2019-10-1 9:00");
        }
    }
}
