package com.example.meetingsystemandroid.main.search;

import android.content.Context;

import com.example.meetingsystemandroid.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchFragmentPresenter implements ISearchFragmentPrensenter {
    private Context mContext;
    private ISearchFragmentView mView;

    public SearchFragmentPresenter(Context context, ISearchFragmentView view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void search(String text) {
        Retrofit retrofit = RetrofitClient.getInstance(mContext);
        ISearchApi api = retrofit.create(ISearchApi.class);
        Call<SearchResultBean> call = api.getSearchResult(text);
        call.enqueue(new Callback<SearchResultBean>() {
            @Override
            public void onResponse(Call<SearchResultBean> call, Response<SearchResultBean> response) {
                SearchResultBean bean = response.body();
                if (bean != null) {
                    mView.updateSearchResult(new ArrayList<SearchResultBean.ActivityInfo>(Arrays.asList(bean.getActivities())));
                }
            }

            @Override
            public void onFailure(Call<SearchResultBean> call, Throwable t) {

            }
        });
    }
}
