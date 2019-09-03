package com.example.meetingsystemandroid.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.meetingsystemandroid.R;
import com.example.meetingsystemandroid.main.search.SearchFragment;
import com.example.meetingsystemandroid.main.home.HomePageFragment;
import com.example.meetingsystemandroid.main.management.MeetingManagerFragment;
import com.example.meetingsystemandroid.main.personal_center.PersonalCenterFragment;
import com.example.meetingsystemandroid.model.User;
import com.example.meetingsystemandroid.utils.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        mAdapter.addFragment(new SearchFragment());
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
        getUserInfo();
    }

    private void setActionBar() {
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setTitle(R.string.main);
        }
    }
    private void getUserInfo() {
        Retrofit retrofit = RetrofitClient.getInstance(this);
        IMainApi api = retrofit.create(IMainApi.class);
        Call<LoginCheckResponseBean> call = api.checkLogin();
        call.enqueue(new Callback<LoginCheckResponseBean>() {
            @Override
            public void onResponse(Call<LoginCheckResponseBean> call, Response<LoginCheckResponseBean> response) {
                LoginCheckResponseBean bean = response.body();
                if (bean != null) {
                    if (bean.isStatus()) {
                        // 处于登陆状态, 继续获取用户信息
                        Call<UserBean> call2 = api.getUserInfo();
                        call2.enqueue(new Callback<UserBean>() {
                            @Override
                            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                                UserBean bean1 = response.body();
                                if (bean1 != null) {
                                    User.setUserbeanToUser(bean1);
                                } else {
                                    Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<UserBean> call, Throwable t) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginCheckResponseBean> call, Throwable t) {

            }
        });

    }
}
