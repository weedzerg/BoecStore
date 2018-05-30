package com.media.convert.videotomp3aacc.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by DaiPhongPC on 9/8/2017.
 */

public class Utils {
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

    public static void rateApp_(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    public static String convertNameAudio(String name, String type) {
        String s = "";
        long timestamp = Calendar.getInstance().getTimeInMillis();
        DateFormat fomat = new SimpleDateFormat("_MMddyyyy_HHmmss");
        s = name.substring(0, name.indexOf(".")) + fomat.format(new Date(timestamp)) + "." + type;
        return s;
    }

    public static void creatFile() {

        File f = new File(Constant.PATHFOLDER);
        if (!f.exists()) {
            f.mkdirs();
        }
        File f1 = new File(Constant.PATHAUD);
        if (!f1.exists()) {
            f1.mkdirs();
        }
        File f2 = new File(Constant.PATHVID_);
        if (!f1.exists()) {
            f1.mkdirs();
        }

    }

    public static void dowloadVideoYoutube(String path) {
        try {

            URL url = new URL(path);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();
            FileOutputStream f = new FileOutputStream(new File(Constant.PATHVIDYOU));
            InputStream in = c.getInputStream();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = in.read(buffer)) > 0) {
                f.write(buffer, 0, len1);
            }
            f.close();
        } catch (IOException e) {
            Log.d("Error....", e.toString());
        }
    }

    public static String coverName(String name) {
        int index = name.indexOf(".");
        String start = name.substring(0, index);
        String type = name.substring(index, name.length());
        if (index > 12) {
            return start.substring(0, 6) + "..." + start.substring(index - 6, index) + type;

        } else {
            return name;

        }
    }
}
