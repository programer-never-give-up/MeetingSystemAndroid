package com.example.meetingsystemandroid.user_history;


import java.util.ArrayList;

public interface IUserHistoryView {
    // 根据上一个activity的信息，决定标题为参会历史还是举办历史
    public void setActionBarTitle(String title);

    // 请求历史数据成功后进行recyclerview更新
    public void onSuccess(ArrayList<UserHistoryBean.HistoryActivity> historyList);

    // 请求数据失败，toast message 并返回
    public void onFailed();

}
