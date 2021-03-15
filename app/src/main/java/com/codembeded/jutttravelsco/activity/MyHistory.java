package com.codembeded.jutttravelsco.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.adapter.AdapterForHomeSlider;
import com.codembeded.jutttravelsco.adapter.AdapterForMyHistory;
import com.codembeded.jutttravelsco.adapter.ViewPagerAdapter;
import com.codembeded.jutttravelsco.models.MyHistoryModels;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MyHistory extends AppCompatActivity {

    Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_history);

        mToolbar = findViewById(R.id.toolbar_history);

        setSupportActionBar(mToolbar);
        setViewPager();

    }

    private void setViewPager() {

        mViewPager = findViewById(R.id.view_pager_history);
        mTabLayout = findViewById(R.id.tabs_history);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(viewPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);


    }
}