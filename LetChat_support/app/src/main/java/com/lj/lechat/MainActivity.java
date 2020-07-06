package com.lj.lechat;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.lj.lechat.adapter.SectionsPagerAdapter;
import com.lj.lechat.fragment.FragmentChat;
import com.lj.lechat.fragment.FragmentDynamic;
import com.lj.lechat.fragment.FragmentFriends;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager main_viewpager;
    private BottomNavigationView main_nav;
    private List<Fragment> fragments;
    private Button chat,friends,dynamic;
    private int lastFragment = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBottomView();
        initViewPager();
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

    private void initBottomView() {
        main_viewpager = findViewById(R.id.main_viewpager);
        main_nav = findViewById(R.id.bottomNavigationView);

        main_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener ;

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
}
