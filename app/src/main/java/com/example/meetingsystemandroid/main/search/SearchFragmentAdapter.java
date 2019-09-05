package com.example.meetingsystemandroid.main.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetingsystemandroid.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchFragmentAdapter extends RecyclerView.Adapter<SearchFragmentAdapter.SearchItemViewHolder> {

    private Context mContext;
    private ArrayList<SearchResultBean.ActivityInfo> mList;
    private SearchFragmentPresenter mPresenter;

    public SearchFragmentAdapter(Context context, SearchFragmentPresenter presenter) {
        mContext = context;
        mList = new ArrayList<>();
        mPresenter = presenter;
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
        SearchResultBean.ActivityInfo info = mList.get(position);
        holder.setActivityName(info.getName_act());
        holder.setActivityId(info.getUuid_act());
    }

    public void updateActivitiesList(ArrayList<SearchResultBean.ActivityInfo> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class SearchItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.search_activity_item_name)
        TextView mActivityName;

        private String mActivityId;

        public SearchItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setActivityName(String name) {
            mActivityName.setText(name);
        }

        public void setActivityId(String id) {
            mActivityId = id;
        }
        @OnClick(R.id.recycler_search_result)
        public void showActivityInfo(){
            mPresenter.toActivityInfo(mActivityId);
        }
    }
}
