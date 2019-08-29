package com.example.meetingsystemandroid.main.personal_center;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.meetingsystemandroid.R;
import com.example.meetingsystemandroid.main.home.HomePageFragment;
import com.example.meetingsystemandroid.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PersonalCenterFragment extends Fragment implements IPersonalCenterFragmentView{


    private PersonalCenterFragmentPresenter mPresenter;
    private Unbinder unbinder;

    @BindView(R.id.image_personal_card_avatar)
    ImageView mAvatar;
    @BindView(R.id.text_personal_card_username)
    TextView mUsername;
    @BindView(R.id.text_personal_card_type)
    TextView mUserType;
    @BindView(R.id.text_personal_center_login_logout)
    TextView mLoginLogoutText;
    @BindView(R.id.personal_center_organize_history)
    RelativeLayout mOrganizeHistory;


    public PersonalCenterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_personal_center, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new PersonalCenterFragmentPresenter(this, this.getContext());
        // 初始化
        mPresenter.checkUserStatus();
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
    public void setUserCard(String avatar, String username, String type) {
        Glide.with(this)
                .load(avatar)
                .into(mAvatar);
        mUsername.setText(username);
        mUserType.setText(type);
    }

    @Override
    public void logoutButtonChange(String notice) {
        mLoginLogoutText.setText(notice);
    }

    @Override
    public void setOrganizeVisible(boolean visible) {
        if (visible) {
            mOrganizeHistory.setVisibility(View.VISIBLE);
        } else {
            mOrganizeHistory.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void close() {
        getActivity().finish();
    }

    @OnClick(R.id.personal_center_login_logout)
    public void loginOrlogout() {
        mPresenter.loginOrLogout();
    }

    @OnClick(R.id.personal_center_attend_history)
    public void attendHistory() {
        mPresenter.toAttendHistory();
    }

    @OnClick(R.id.personal_center_organize_history)
    public void organizeHistory() {
        mPresenter.toOrganizeHistory();
    }

    @OnClick(R.id.personal_center_user_card)
    public void allUserInfo() {
        mPresenter.toAllUserInfo();
    }


}
