package com.example.meetingsystemandroid.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private Retrofit retrofit;
    private static RetrofitClient client;
    private static final String BASE_URL = "http://139.219.14.146/";


    // 返回retrofit对象
    public static Retrofit getInstance() {
        if(client == null) {
            client = new RetrofitClient();
        }
        return client.getRetrofit();
    }

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private Retrofit getRetrofit() {
        return retrofit;
    }
}
