package com.example.meetingsystemandroid.main.search;

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
import android.widget.SearchView;
import android.widget.Toast;

import com.example.meetingsystemandroid.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SearchFragment extends Fragment implements ISearchFragmentView{

    private Unbinder unbinder;
    @BindView(R.id.search_activity)
    SearchView mSearchInput;
    @BindView(R.id.search_activity_show)
    RecyclerView mSearchResult;

    private SearchFragmentPresenter mPresenter;
    private SearchFragmentAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, v);
        // init recyclerview
        mPresenter = new SearchFragmentPresenter(getContext(),this);
        mAdapter = new SearchFragmentAdapter(getContext(), mPresenter);
        mSearchResult.setLayoutManager(new LinearLayoutManager(getContext()));
        mSearchResult.setAdapter(mAdapter);
        mSearchResult.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 搜索框监听
        mSearchInput.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPresenter.search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void updateSearchResult(ArrayList<SearchResultBean.ActivityInfo> list) {
        mAdapter.updateActivitiesList(list);
    }
}
