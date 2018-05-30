package com.security.filelocker.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.ContextCompat;

import com.security.filelocker.objects.InfoFile;

import java.io.File;
import java.util.ArrayList;

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

    public static ArrayList<InfoFile> removeArrayList(ArrayList<InfoFile> ls1, ArrayList<InfoFile> ls2) {
        for (InfoFile s : ls2) {
            int pos = searchInfoFile(ls1, s.getPathfile());
            if (pos != -1) ls1.remove(pos);
        }
        return ls1;
    }

    public static String renameFile(String s, String name) {
        String s0 = s.substring(0, s.lastIndexOf("/"));
        return s0 + "/" + name;
    }

    public static String convertPathFile(String s) {
        String s0 = s.substring(0, s.lastIndexOf("/"));
        String s1 = s.substring(s.lastIndexOf("/") + 1);
        return s0 + "/." + s1;
    }

    public static String convertPathFile1(String s) {
        String s0 = s.substring(0, s.lastIndexOf("/"));
        String s1 = s.substring(s.lastIndexOf("/") + 1);
        s1 = s1.substring(1);
        return s0 + "/" + s1;
    }

    public static boolean isMicroSD(String path) {
        String s = path.substring(0, path.indexOf("/", 18));
        if (s.equals(Environment.getExternalStorageDirectory().getPath())) return false;
        return true;
    }

    public static String convertToUri(String key, String path) {
        String s = Contants.ID_DOCUMENT + path.substring(key.length(), path.lastIndexOf("/") + 1);
        try {
            String id = s.substring(0, s.lastIndexOf("/"));
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return s;
        }
    }

    public static String convertToUriDecode(String key, String path) {
        String s = Contants.ID_DOCUMENT + path.substring(key.length());
        return s;
    }

    public static int searchInfoFile(ArrayList<InfoFile> ls, String path) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getPathfile().equalsIgnoreCase(path)) return i;
        }
        return -1;
    }

    public static int searchInfoFileEN(ArrayList<InfoFile> ls, String path) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getPathfileCache().equalsIgnoreCase(path)) return i;
        }
        return -1;
    }

    public static void removeFile(String s) {
        File file = new File(s);
        file.delete();
    }

    public static String getNameFile(String s) {
        return s.substring(s.lastIndexOf("/") + 1);
    }

    public static String getTypeFile(String s) {
        return s.substring(s.lastIndexOf(".") + 1);
    }

}
