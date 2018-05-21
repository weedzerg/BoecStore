package com.lx.ltuddd.boecstore.client.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 5/21/2018.
 */

public class PaperAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> ls;

    public PaperAdapter(FragmentManager fm, ArrayList<Fragment> ls) {
        super(fm);
        this.ls = ls;
    }

    @Override
    public Fragment getItem(int position) {
        return ls.get(position);
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Home";
                break;
            case 1:
                title = "Books";
                break;
            case 2:
                title = "Electronics";
                break;
            case 3:
                title = "Clothes";
                break;
        }

        return title;
    }
}
