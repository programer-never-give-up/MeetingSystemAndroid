package com.example.meetingsystemandroid.main.management;

import android.content.Context;

public class ManagerFragmentPresenter implements IManagerFragmentPresenter{
    private Context mContext;
    private IManagerFragmentView mView;

    public ManagerFragmentPresenter(IManagerFragmentView view, Context context) {
        mContext = context;
        mView = view;
    }


}
