package com.example.meetingsystemandroid.utils;

import android.content.Context;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private Retrofit retrofit;
    private static RetrofitClient client;
    private static final String BASE_URL = "http://139.219.14.146/";

    // 返回retrofit对象
    public static Retrofit getInstance(Context context) {
        if(client == null) {
            client = new RetrofitClient(context);
        }
        return client.getRetrofit();
    }

    private RetrofitClient(Context context) {
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context)))
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private Retrofit getRetrofit() {
        return retrofit;
    }
}
