package com.example.meetingsystemandroid.main.personal_center;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meetingsystemandroid.R;
import com.example.meetingsystemandroid.main.home.HomePageFragment;
import com.example.meetingsystemandroid.model.User;

public class PersonalCenterFragment extends Fragment implements IPersonalCenterFragmentView{

    public PersonalCenterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal_center, container, false);
    }

    public void onButtonPressed(Uri uri) {

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
    public void setUserCard() {

    }

    @Override
    public void logoutButtonChange() {

    }
}
