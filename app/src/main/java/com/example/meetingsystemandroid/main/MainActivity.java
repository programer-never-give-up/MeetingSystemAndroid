package com.example.meetingsystemandroid.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.meetingsystemandroid.R;
import com.example.meetingsystemandroid.main.fragment.CommunityFragment;
import com.example.meetingsystemandroid.main.fragment.HomePageFragment;
import com.example.meetingsystemandroid.main.fragment.MeetingManagerFragment;
import com.example.meetingsystemandroid.main.fragment.PersonalCenterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{

    private BottomNavigationView mNavbar;
    private ViewPager mViewPager;
    private MainActivityViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new MainActivityViewPagerAdapter(getSupportFragmentManager());
        mNavbar = findViewById(R.id.navbar);
        mViewPager = findViewById(R.id.view_pager);
        mAdapter.addFragment(new HomePageFragment());
        mAdapter.addFragment(new CommunityFragment());
        mAdapter.addFragment(new MeetingManagerFragment());
        mAdapter.addFragment(new PersonalCenterFragment());
        mViewPager.setAdapter(mAdapter);
        mNavbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab_home_page:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.tab_community:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.tab_meeting_mamager:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.tab_personal_center:
                        mViewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                当 ViewPager 滑动后设置BottomNavigationView 选中相应选项
                mNavbar.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
