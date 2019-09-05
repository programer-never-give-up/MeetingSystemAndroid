package com.example.meetingsystemandroid.main.home;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.meetingsystemandroid.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomePageFragment extends Fragment implements IHomePageView{


    @BindView(R.id.home_page_recommend_activity)
    RecyclerView mRecommendActivityList;
    @BindView(R.id.home_page_recent_activity)
    RecyclerView mRecentActivityList;

    private HomePageRecentActivityCardAdapter mRecentAdapter;
    private HomePageRecommendActivityCardAdapter mRecommendAdapter;

    private Unbinder unbinder;
    private HomePagePresenter mPresenter;

    public HomePageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new HomePagePresenter(this, getContext());
        mRecommendAdapter = new HomePageRecommendActivityCardAdapter(getContext(), mPresenter);
        mRecentAdapter = new HomePageRecentActivityCardAdapter(getContext(), mPresenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_page, container, false);
        unbinder = ButterKnife.bind(this, v);
        setRecyclerView();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mRecentAdapter.isNeedUpdate()) {
            mPresenter.getRecentActivies();
        }
    }

    private void setRecyclerView() {
        mRecommendActivityList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecommendActivityList.setAdapter(mRecommendAdapter);
//        mRecommendActivityList.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        mRecommendActivityList.setNestedScrollingEnabled(false);
        mRecentActivityList.setLayoutManager(new LinearLayoutManager(getContext()));
//        mRecentActivityList.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        mRecentActivityList.setAdapter(mRecentAdapter);
        mRecentActivityList.setNestedScrollingEnabled(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void updateRecentActivities(RecentActivitiesBean bean) {
        mRecentAdapter.setNeedUpdate(false);
        mRecentAdapter.addRecentActivities(new ArrayList<RecentActivitiesBean.RecentActivity>(Arrays.asList(bean.getList_activity())));
        mRecentAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateRecommendActivities(RecommendAcivitiesBean bean) {
    }
}
