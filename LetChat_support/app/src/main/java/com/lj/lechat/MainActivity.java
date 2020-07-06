package com.lj.lechat;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lj.lechat.adapter.SectionsPagerAdapter;
import com.lj.lechat.fragment.FragmentChat;
import com.lj.lechat.fragment.FragmentDynamic;
import com.lj.lechat.fragment.FragmentFriends;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, BottomNavigationBar.OnTabSelectedListener {
    private ViewPager main_viewpager;
    private BottomNavigationBar main_nav;
    private List<Fragment> fragments;
    private Button chat, friends, dynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initBottomBar();
        initViewPager();
    }

    private void initBottomBar() {
        main_nav.setTabSelectedListener(this);
        main_nav.clearAll();
        main_nav.setMode(BottomNavigationBar.MODE_FIXED);
        main_nav.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        main_nav.setBarBackgroundColor(R.color.whitecolor)
                .setActiveColor(R.color.blue)
                .setInActiveColor(R.color.black);

        main_nav.addItem(new BottomNavigationItem(R.drawable.ic_huihua, "会话")).setInActiveColor(R.color.black);
        main_nav.addItem(new BottomNavigationItem(R.drawable.ic_users, "联系人").setInActiveColor(R.color.black));
        main_nav.addItem(new BottomNavigationItem(R.drawable.ic_dongtai, "动态")).setInActiveColor(R.color.black);

        main_nav.setFirstSelectedPosition(0);
        main_nav.initialise();
    }

    private void initViewPager() {
        main_viewpager.setOffscreenPageLimit(3);

        fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentChat());
        fragments.add(new FragmentFriends());
        fragments.add(new FragmentDynamic());

        main_viewpager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), fragments));

        main_viewpager.addOnPageChangeListener(this);

        main_viewpager.setCurrentItem(0);

    }

    private void initView() {

        main_viewpager = findViewById(R.id.main_viewpager);
        main_nav = findViewById(R.id.bottomNavigationView);
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        main_viewpager.setCurrentItem(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onTabSelected(int position) {
        main_nav.selectTab(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
