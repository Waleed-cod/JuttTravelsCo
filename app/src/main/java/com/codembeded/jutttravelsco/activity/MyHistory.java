package com.codembeded.jutttravelsco.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.adapter.ViewPagerHistoryAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class MyHistory extends AppCompatActivity {

    Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    ViewPagerHistoryAdapter viewPagerHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_history);

        mToolbar = findViewById(R.id.toolbar_history);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        setViewPager();

    }

    private void setViewPager() {

        mViewPager = findViewById(R.id.view_pager_history);
        mTabLayout = findViewById(R.id.tabs_history);

        viewPagerHistoryAdapter = new ViewPagerHistoryAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(viewPagerHistoryAdapter);

        mTabLayout.setupWithViewPager(mViewPager);


    }
}