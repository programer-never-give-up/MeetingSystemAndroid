package com.example.meetingsystemandroid.main.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetingsystemandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivityAdapter extends RecyclerView.Adapter<SearchActivityAdapter.SearchItemViewHolder> {

    private Context mContext;

    public SearchActivityAdapter(Context context) {
        mContext = context;
    }
    @NonNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_search_result, parent, false);
        return new SearchItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemViewHolder holder, int position) {
        // 先占位看效果 之后变成加载搜索结果
        holder.setActivityName("活动名称");
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class SearchItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.search_activity_item_name)
        TextView mActivityName;

        public SearchItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setActivityName(String name) {
            mActivityName.setText(name);
        }
    }
}
