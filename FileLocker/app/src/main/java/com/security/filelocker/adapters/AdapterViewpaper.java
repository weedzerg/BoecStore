package com.security.filelocker.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.security.filelocker.fragments.PermissionSD1Fragment;
import com.security.filelocker.fragments.PermissionSD2Fragment;

/**
 * Created by DaiPhongPC on 9/23/2017.
 */

public class AdapterViewpaper extends FragmentStatePagerAdapter {
    public AdapterViewpaper(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;
        switch (position) {
            case 0:
                frag = new PermissionSD1Fragment();
                break;
            case 1:
                frag = new PermissionSD2Fragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 2;
    }


}