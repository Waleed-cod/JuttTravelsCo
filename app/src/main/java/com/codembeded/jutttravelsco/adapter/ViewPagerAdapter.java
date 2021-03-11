package com.codembeded.jutttravelsco.adapter;

import com.codembeded.jutttravelsco.fragments.MyParcels;
import com.codembeded.jutttravelsco.fragments.MySpecialBookings;
import com.codembeded.jutttravelsco.fragments.MyTickets;
import com.codembeded.jutttravelsco.fragments.MyTours;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    private static final int TAB_COUNT = 4;

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MyTickets.newInstance();
            case 1:
                return MyParcels.newInstance();
            case 2:
                return MySpecialBookings.newInstance();
            case 3:
                return MyTours.newInstance();
        }


        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {


        switch (position) {
            case 0:
                return MyTickets.TITLE;
            case 1:
                return MyParcels.TITLE;
            case 2:
                return MySpecialBookings.TITLE;
            case 3:
                return MyTours.TITLE;

        }
        return super.getPageTitle(position);
    }
}
