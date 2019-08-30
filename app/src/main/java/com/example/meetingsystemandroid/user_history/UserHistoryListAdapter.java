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

public class UserHistoryListAdapter extends RecyclerView.Adapter<UserHistoryListAdapter.UserHistoryListItemViewHolder> {

    public ArrayList<UserHistoryBean.HistoryActivity> mHistoryList;
    public Context mContext;
    public UserHistoryListAdapter(Context context) {
        mContext = context;
        mHistoryList = new ArrayList<>();
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

        public UserHistoryListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
