package com.example.meetingsystemandroid.activity_info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meetingsystemandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivityInfoActivity extends AppCompatActivity {

    @BindView(R.id.show_activity_logo)
    ImageView mLogo;
    @BindView(R.id.show_activity_title)
    TextView mTitle;
    @BindView(R.id.show_activity_introduction)
    TextView mIntroduction;
    @BindView(R.id.show_activity_location)
    TextView mLocation;
    @BindView(R.id.show_activity_time)
    TextView mTime;
    @BindView(R.id.show_activity_organizer)
    TextView mOrganizer;
    @BindView(R.id.show_activity_file_list)
    RecyclerView mFileList;

    private ShowFileListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_activity_info);
        ButterKnife.bind(this);
        mAdapter = new ShowFileListAdapter(this);
        mFileList.setLayoutManager(new LinearLayoutManager(this));
        mFileList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mFileList.setAdapter(mAdapter);
        mFileList.setNestedScrollingEnabled(false);
    }
}
