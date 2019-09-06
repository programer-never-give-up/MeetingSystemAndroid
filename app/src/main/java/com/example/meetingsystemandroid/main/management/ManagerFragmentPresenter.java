package com.example.meetingsystemandroid.main.management;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.meetingsystemandroid.activity_manager.ManagerActivity;
import com.example.meetingsystemandroid.model.User;

public class ManagerFragmentPresenter implements IManagerFragmentPresenter{
    private Context mContext;
    private IManagerFragmentView mView;

    public ManagerFragmentPresenter(IManagerFragmentView view, Context context) {
        mContext = context;
        mView = view;
    }

    @Override
    public void toManagerActivity(String type) {
        Intent intent = new Intent(mContext, ManagerActivity.class);
        intent.putExtra(ManagerActivity.ACTIVITY_TYPE_TAG, type);
        mContext.startActivity(intent);
    }

    @Override
    public void setFragmentUI() {
        User user = User.getInstance();
        if (user.getType() != User.UserType.COMPANY) {
            mView.disablePublishModule();
        }
    }
}
