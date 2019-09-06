package com.example.meetingsystemandroid.main.management;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.meetingsystemandroid.R;
import com.example.meetingsystemandroid.activity_manager.ManagerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class MeetingManagerFragment extends Fragment implements IManagerFragmentView{


    @BindView(R.id.manager_fragment_publish_module)
    LinearLayout mPublishModule;

    private Unbinder unbinder;
    private ManagerFragmentPresenter mPresenter;

    public MeetingManagerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meeting_manager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        mPresenter = new ManagerFragmentPresenter(this, getContext());
        mPresenter.setFragmentUI();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void disablePublishModule() {
        mPublishModule.setVisibility(View.GONE);
    }

    @OnClick(R.id.manager_fragment_attend_not_start)
    public void showNotStartAttendActivity() {
        mPresenter.toManagerActivity(ManagerActivity.ATTEND_NOT_START);
    }
    @OnClick(R.id.manager_fragment_attend_processing)
    public void showProcessingAttendActivity() {
        mPresenter.toManagerActivity(ManagerActivity.ATTEND_PROCESSING);
    }
    @OnClick(R.id.manager_fragment_publish_unpublish)
    public void showUnpublishActivity() {
        mPresenter.toManagerActivity(ManagerActivity.ORGANIZE_UNPUBLISHED);
    }
    @OnClick(R.id.manager_fragment_publish_published)
    public void showPublishedActivity() {
        mPresenter.toManagerActivity(ManagerActivity.ORGANIZE_PUBLISHED);

    }
    @OnClick(R.id.manager_fragment_publish_processing)
    public void showNProcessingPublishedActivity() {
        mPresenter.toManagerActivity(ManagerActivity.ORGANIZE_PROCESSING);

    }
    @OnClick(R.id.manager_fragment_publish_in_check)
    public void showInCheckActivity() {
        mPresenter.toManagerActivity(ManagerActivity.ORGANIZE_INCHECK);

    }
    @OnClick(R.id.manager_fragment_collection_finished)
    public void showFinishedCollectionActivity() {
        mPresenter.toManagerActivity(ManagerActivity.COLLECTION_FINISHED);

    }
    @OnClick(R.id.manager_fragment_collection_not_start)
    public void showNotStartCollectionActivity() {
        mPresenter.toManagerActivity(ManagerActivity.COLLECTION_NOT_START);

    }
    @OnClick(R.id.manager_fragment_collection_processing)
    public void showProcessingCollectionActivity() {
        mPresenter.toManagerActivity(ManagerActivity.COLLECTION_PROCESSING);

    }


}
