package com.blogspot.quanlytomatoads.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.blogspot.quanlytomatoads.fragment.QLApp;
import com.blogspot.quanlytomatoads.fragment.QLUser;
import com.blogspot.quanlytomatoads.fragment.Thietlap;

/**
 * Created by DaiPhongPC on 6/3/2017.
 */

public class PaperAdapter extends FragmentStatePagerAdapter {
    public PaperAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fra = null;
        switch (position) {
            case 0: {
                fra = new QLApp();
                break;
            }
            case 1: {
                fra = new QLUser();
                break;
            }
            case 2: {
                fra = new Thietlap();
                break;
            }

        }

        return fra;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Xét duyệt";
                break;
            case 1:
                title = "User";
                break;
            case 2:
                title = "Thiết lập";
                break;
        }
        return title;
    }
}
