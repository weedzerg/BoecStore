package com.galaxy.s8.messagepro.messagergalaxys8.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DaiPhongPC on 9/8/2017.
 */

public class Utils {

    public static void getCache(Cache cache) {
        String statusbar = cache.getLink(Contants.CACHE_STATUS_BAR);
        String background = cache.getLink(Contants.CACHE_BACKGROUND);
        if (statusbar != null) {
            Contants.COLOR_STATUS_BAR = statusbar;
        }
        if (background != null) {
            try {
                Contants.BACKGROUND = Integer.parseInt(background);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    public static int checkPermission(String[] permissions, Context context) {
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (String permission : permissions) {
            permissionCheck += ContextCompat.checkSelfPermission(context, permission);
        }
        return permissionCheck;
    }

    public static void rateApp(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    public static String convertDate(long s) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date(s));
    }

    public static String checkNumberPhone(String s) {
        try {
            if (s.substring(0, 1).equals("0")) {
                s = "+84" + s.substring(1);
            }
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return s;
        }
    }
}
