package com.example.meetingsystemandroid;

import android.app.Application;

import com.example.meetingsystemandroid.utils.RetrofitClient;

import retrofit2.Retrofit;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化http client
        Retrofit retrofit = RetrofitClient.getInstance(this);
    }
}
