package com.codembeded.jutttravelsco.adapter;

import com.codembeded.jutttravelsco.fragments.MyParcelHistory;
import com.codembeded.jutttravelsco.fragments.MySpecialBookingHistory;
import com.codembeded.jutttravelsco.fragments.MyTicketsHistory;
import com.codembeded.jutttravelsco.fragments.MyToursHistory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerHistoryAdapter extends FragmentStatePagerAdapter {

    private static final int TAB_COUNT = 4;


    public ViewPagerHistoryAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return MyTicketsHistory.newInstance();
            case 1:
                return MyParcelHistory.newInstance();
            case 2:
                return MySpecialBookingHistory.newInstance();
            case 3:
                return MyToursHistory.newInstance();
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
        switch (position){
            case 0:
                return MyTicketsHistory.TITLE;
            case 1:
                return MyParcelHistory.TITLE;
            case 2:
                return MySpecialBookingHistory.TITLE;
            case 3:
                return MyToursHistory.TITLE;
        }
        return super.getPageTitle(position);
    }
}
