package com.example.meetingsystemandroid.main.management;

public interface IManagerFragmentPresenter {

    // 跳转到ManagerActivity 并传入type
    public void toManagerActivity(String type);

    public void setFragmentUI();
}
