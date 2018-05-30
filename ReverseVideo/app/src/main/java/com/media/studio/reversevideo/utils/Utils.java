package com.media.studio.reversevideo.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.media.studio.reversevideo.objects.InfoFile;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public static String convertNameVideo() {
        String s = "";
        long timestamp = Calendar.getInstance().getTimeInMillis();
        DateFormat fomat = new SimpleDateFormat("_MMddyyyy_HHmmss");
        s = "VIDEO_REVERSE" + fomat.format(new Date(timestamp)) + ".mp4";
        return s;
    }

    public static void reverseFileImage() {
        File folder = new File(Constant.PATHTEMPIMAGE);
        File folders = new File(Constant.PATHTEMPIMAGES);
        String name1, name2;
        if (!folders.exists()) {
            folders.mkdirs();
        }
        File[] listfile = folder.listFiles();
        int size = listfile.length;
        Log.d("SIZE", size + "");
        try {

            for (int i = 0; i < listfile.length / 2; i++) {

                name1 = listfile[i].getName();
                name2 = listfile[size - 1 - i].getName();
                Log.d("SIZE", name1 + "\t" + name2);
                FileUtils.copyFile(listfile[i], new File(folders, name2));
                FileUtils.copyFile(listfile[size - 1 - i], new File(folders, name1));
            }
            if (size % 2 != 0) {
                name2 = listfile[(size / 2)].getName();
                FileUtils.copyFile(listfile[(size / 2) + 1], new File(folders, name2));
            }
            FileUtils.deleteDirectory(folder);
            folder.mkdirs();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String convertSLOWMOTION() {
        String s = "";
        long timestamp = Calendar.getInstance().getTimeInMillis();
        DateFormat fomat = new SimpleDateFormat("_MMddyyyy_HHmmss");
        s = "VIDEO_SLOW" + fomat.format(new Date(timestamp)) + ".mp4";
        return s;
    }

    public static String convertNameTrimVideo() {
        String s = "";
        long timestamp = Calendar.getInstance().getTimeInMillis();
        DateFormat fomat = new SimpleDateFormat("_MMddyyyy_HHmmss");
        s = "VIDEO_CUT" + fomat.format(new Date(timestamp)) + ".mp4";
        return s;
    }

    public static String convertNameGIF() {
        String s = "";
        long timestamp = Calendar.getInstance().getTimeInMillis();
        DateFormat fomat = new SimpleDateFormat("_MMddyyyy_HHmmss");
        s = "GIF_LOOP" + fomat.format(new Date(timestamp));
        return s;
    }

    public static ArrayList<InfoFile> getAllFileVideo(Context context) {
        ArrayList<InfoFile> lsFile = new ArrayList<>();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.VideoColumns.DATA};
        Cursor c = context.getContentResolver().query(uri, projection, null, null, null);
        int vidsCount = 0;
        if (c != null) {
            while (c.moveToNext()) {
                String namefile = c.getString(0);
                String namepath = c.getString(1);
                lsFile.add(new InfoFile(namefile, namepath, false));
            }
            c.close();
        }
        return lsFile;
    }
}
