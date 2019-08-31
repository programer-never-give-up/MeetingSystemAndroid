package com.example.meetingsystemandroid.user_history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetingsystemandroid.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserHistoryListAdapter extends RecyclerView.Adapter<UserHistoryListAdapter.UserHistoryListItemViewHolder> {

    private ArrayList<UserHistoryBean.HistoryActivity> mHistoryList;
    private Context mContext;
    private UserHistoryPresenter mPresenter;
    public UserHistoryListAdapter(Context context, UserHistoryPresenter presenter) {
        mContext = context;
        mHistoryList = new ArrayList<>();
        mPresenter = presenter;
    }
    @NonNull
    @Override
    public UserHistoryListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_user_history_item, parent, false);
        return new UserHistoryListItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHistoryListItemViewHolder holder, int position) {
        UserHistoryBean.HistoryActivity item = mHistoryList.get(position);
        holder.mActivityName.setText(item.getName_act());
        holder.mActivityStartTime.setText("开始时间:"+item.getStart_time());
        holder.mActivityEndTime.setText("结束时间:"+item.getEnd_time());
        holder.mActivityId = item.getUuid_act();
    }

    public void addHistoryList(ArrayList<UserHistoryBean.HistoryActivity> list) {
        mHistoryList.addAll(list);
    }

    @Override
    public int getItemCount() {
        return mHistoryList.size();
    }

    class UserHistoryListItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.user_history_list_item_name)
        TextView mActivityName;
        @BindView(R.id.user_history_list_item_start_time)
        TextView mActivityStartTime;
        @BindView(R.id.user_history_list_item_end_time)
        TextView mActivityEndTime;

        private String mActivityId;

        public UserHistoryListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        @OnClick(R.id.user_history_list_item)
        public void toActiviyInfo() {
            mPresenter.toActivityInfo(mActivityId);
        }
    }
}
