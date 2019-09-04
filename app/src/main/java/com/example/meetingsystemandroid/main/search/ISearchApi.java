package com.example.meetingsystemandroid.main.search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ISearchApi {
    @GET("api/yw/search")
    Call<SearchResultBean> getSearchResult(@Query("key") String name);
}
