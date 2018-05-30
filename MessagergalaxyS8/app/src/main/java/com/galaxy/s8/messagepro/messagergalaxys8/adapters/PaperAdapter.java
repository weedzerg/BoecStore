package com.galaxy.s8.messagepro.messagergalaxys8.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.galaxy.s8.messagepro.messagergalaxys8.R;

/**
 * Created by DaiPhongPC on 9/30/2017.
 */

public class PaperAdapter extends FragmentStatePagerAdapter {
    private Fragment[] lsfragment;
    private Context context;

    public PaperAdapter(FragmentManager fm, Fragment[] lsfragment, Context
            context) {
        super(fm);
        this.lsfragment = lsfragment;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fra = null;
        switch (position) {
            case 0: {
                fra = lsfragment[0];
                break;
            }
            case 1: {
                fra = lsfragment[1];
                break;
            }
        }
        return fra;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        if (position == 0) {
            title =context.getString(R.string.messenger);
        } else {
            title = context.getString(R.string.contact);
        }
        return title;
    }
}
