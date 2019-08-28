package com.example.meetingsystemandroid.main.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import com.example.meetingsystemandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomePageFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.home_page_recommend_activity)
    RecyclerView mRecommendActivityList;
    @BindView(R.id.home_page_recent_activity)
    RecyclerView mRecentActivityList;

    public HomePageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home_page, container, false);
        unbinder = ButterKnife.bind(this, v);
        setRecyclerView();
        return v;
    }

    private void setRecyclerView() {
        mRecommendActivityList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecommendActivityList.setAdapter(new HomePageActivityCardAdapter(getContext()));
        mRecommendActivityList.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        mRecommendActivityList.setNestedScrollingEnabled(false);
        mRecentActivityList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecentActivityList.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        mRecentActivityList.setAdapter(new HomePageActivityCardAdapter(getContext()));
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

    //    public static HomePageFragment newInstance(String param1, String param2) {
//        HomePageFragment fragment = new HomePageFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }
}
