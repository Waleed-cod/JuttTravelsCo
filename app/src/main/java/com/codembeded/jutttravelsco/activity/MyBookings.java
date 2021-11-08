package com.codembeded.jutttravelsco.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class MyBookings extends AppCompatActivity {


    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        mToolbar = findViewById(R.id.toolbar_my_bookings);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        setViewPager();

    }

    private void setViewPager() {

        mViewPager = findViewById(R.id.pager);
        mTabLayout = findViewById(R.id.tab);

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

    }

}