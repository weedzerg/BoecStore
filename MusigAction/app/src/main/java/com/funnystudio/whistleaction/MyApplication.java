package com.funnystudio.whistleaction;

import android.app.Application;

import com.zer.android.ZAndroidSDK;

/**
 * Created by DaiPhongPC on 8/22/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ZAndroidSDK.initApplication(this, getPackageName());
    }
}
