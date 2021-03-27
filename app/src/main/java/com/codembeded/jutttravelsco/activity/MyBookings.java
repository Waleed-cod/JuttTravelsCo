package com.codembeded.jutttravelsco.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.codembeded.jutttravelsco.R;
import com.codembeded.jutttravelsco.adapter.AdapterForMyBookings;
import com.codembeded.jutttravelsco.adapter.ViewPagerAdapter;
import com.codembeded.jutttravelsco.models.MyBookingsModels;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

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