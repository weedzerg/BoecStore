package com.funnystudio.whistleaction.common;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

/**
 * Created by DevPro on 6/23/2017.
 */

public class Common {

    public static boolean CheckServiesRuning(Class<?> serviceClass, Activity activity) {
        ActivityManager manager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.d("DEBUG Service", "da duoc tao");
                return true;
            }
        }
        Log.d("DEBUG Service", "chua duoc tao");
        return false;
    }
}
