package com.media.convert.videotomp3aacc.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;

import com.media.convert.videotomp3aacc.objects.InfoFile;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by DaiPhongPC on 9/11/2017.
 */

public class FileExternalStorage {

    public static ArrayList<InfoFile> getAllFileMusic(Context context) {
        ArrayList<InfoFile> lsFile = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Log.d("AUDIO", "Type=" + MediaStore.Audio.Media.MIME_TYPE);
        String[] projection = {
                MediaStore.Audio.AudioColumns.ALBUM,
                MediaStore.Audio.AudioColumns.DISPLAY_NAME,
                MediaStore.Audio.AudioColumns.DATA,
                MediaStore.Audio.AudioColumns.SIZE,
                MediaStore.Audio.AudioColumns.DATE_ADDED};
        Cursor c = context.getContentResolver().query(uri, projection, null, null, null);
        int vidsCount = 0;
        if (c != null) {
            vidsCount = c.getCount();
            while (c.moveToNext()) {
                Log.d("AUDIO", "path:" + c.getString(2));
                String namealbum = c.getString(0);
                String namefile = c.getString(1);
                String namepath = c.getString(2);
                String sizes = c.getString(3);
                String dates = c.getString(4);
                int size = Integer.parseInt(sizes);
                size /= 1024;
                dates = convertDate(dates);
                InfoFile infoFile = new InfoFile(namealbum, namefile, namepath, size, dates);
                lsFile.add(infoFile);
            }
            c.close();
        }
        return lsFile;
    }


    public static ArrayList<InfoFile> getAllFileVideo(Context context) {
        ArrayList<InfoFile> lsFile = new ArrayList<>();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Video.VideoColumns.BUCKET_DISPLAY_NAME,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.VideoColumns.DATA,
                MediaStore.Video.VideoColumns.SIZE,
                MediaStore.Video.VideoColumns.DATE_ADDED
        };
        Cursor c = context.getContentResolver().query(uri, projection, null, null, null);
        int vidsCount = 0;
        if (c != null) {
            vidsCount = c.getCount();
            while (c.moveToNext()) {
                Log.d("VIDEO", c.getString(2));
                String namealbum = c.getString(0);
                String namefile = c.getString(1);
                String namepath = c.getString(2);
                String sizes = c.getString(3);
                String dates = c.getString(4);
                float size = Float.parseFloat(sizes);
                size /= 1024 * 1000;
                dates = convertDate(dates);
                lsFile.add(new InfoFile(namealbum, namefile, namepath, size, dates));
            }
            c.close();
        }
        return lsFile;
    }

    public static String convertDate(String myDate) {
        // or you already have long value of date, use this instead of milliseconds variable.
        String dateString = null;
        try {
            long millisecond = Long.parseLong(myDate) * 1000L;
            dateString = DateFormat.format("dd/MM/yyyy", new Date(millisecond)).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }


}