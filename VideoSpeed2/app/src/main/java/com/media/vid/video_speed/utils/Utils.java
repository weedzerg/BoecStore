package com.media.vid.video_speed.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;

import com.media.vid.video_speed.objects.InfoFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

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


    public static ArrayList<InfoFile> getAllFileVideo(Context context) {
        ArrayList<InfoFile> lsFile = new ArrayList<>();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.VideoColumns.DATA};
        Cursor c = context.getContentResolver().query(uri, projection, null, null, null);
        int vidsCount = 0;
        if (c != null) {
            while (c.moveToNext()) {
                String namefile = c.getString(0);
                String duration_ = c.getString(1);
                String size_ = c.getString(2);
                String namepath = c.getString(3);
                long duration = 0;
                try {
                    duration = Long.parseLong(duration_);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                long size = Long.parseLong(size_);
                lsFile.add(new InfoFile(namefile, namepath, duration, size));
            }
            c.close();
        }
        return lsFile;
    }

    public static ArrayList<InfoFile> getFSVideo(Context context) {
        ArrayList<InfoFile> lsFile = new ArrayList<>();
        File dir = new File(Constant.PATHVIDEO);
        for (File child : dir.listFiles()) {
            String name = child.getName();
            String path = child.getPath();
            long size = child.length();
            MediaPlayer mediaPlayer = MediaPlayer.create(context, Uri.parse(path));
            long duration = mediaPlayer.getDuration();
            InfoFile f = new InfoFile(name, path, duration, size);
            lsFile.add(f);
        }
        return lsFile;
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

    public static String MMStohhmmss(long millis) {
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        return hms;
    }

    public static String sizeMB(long size) {
        float si = (float) (size / (1024 * 1000));
        String hms = String.format("%.1f", si) + "" +
                "MB";
        return hms;
    }

    public static void creatFile() {
        File f = new File(Constant.PATHFOLDER);
        if (!f.exists()) {
            f.mkdirs();
            File f1 = new File(Constant.PATHTEMP);
            if (!f1.exists()) {
                f1.mkdirs();
            }
            File f2 = new File(Constant.PATHVIDEO);
            if (!f2.exists()) {
                f2.mkdirs();
            }
        }
    }

    public static String creatNameFileVideo(String folder, boolean check, String type) {
        Calendar c = Calendar.getInstance();
        String name;
        if (check) {
            name = folder + "/" + "VID_SLOW_" + c.getTimeInMillis() + type;
        } else {
            name = folder + "/" + "VID_FAST_" + c.getTimeInMillis() + type;
        }
        return name;
    }

    public static void removeFolder() {
        File dir = new File(Constant.PATHTEMP);
        for (File child : dir.listFiles()) {
            child.delete();
        }
    }
}