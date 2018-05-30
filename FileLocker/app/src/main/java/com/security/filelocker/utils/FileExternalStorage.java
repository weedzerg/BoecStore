package com.security.filelocker.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.security.filelocker.objects.InfoFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
                MediaStore.Audio.AudioColumns.DATE_ADDED,
                MediaStore.Audio.AudioColumns.MIME_TYPE,
                MediaStore.Audio.AudioColumns.ALBUM_ID};
        Cursor c = context.getContentResolver().query(uri, projection, null, null, null);
        int vidsCount = 0;
        if (c != null) {
            vidsCount = c.getCount();
            while (c.moveToNext()) {
                Log.d("AUDIO", "path:" + c.getString(2) + "\tmine_type=" + c.getString(5));
                String namealbum = c.getString(0);
                String namefile = c.getString(1);
                String namepath = c.getString(2);
                String sizes = c.getString(3);
                String dates = c.getString(4);
                int size = Integer.parseInt(sizes);
                size /= 1024;
                dates = convertDate(dates);

                boolean status;
                if (size < 1000) {
                    status = true;
                } else {
                    status = false;
                }
                InfoFile infoFile = new InfoFile(namealbum, namefile, namepath, size, dates, false, c.getString(5), status);
                infoFile.setIdAlbum(c.getString(6));
                lsFile.add(infoFile);
            }
            c.close();
        }
        return lsFile;
    }

    public static HashMap<String, ArrayList<InfoFile>> getAllFileDocument(Context context) {
        HashMap<String, ArrayList<InfoFile>> hashMap = new HashMap<>();
        ContentResolver cr = context.getContentResolver();
        Uri uri = MediaStore.Files.getContentUri("external");
        String[] projection = {
                MediaStore.Files.FileColumns.DISPLAY_NAME,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.SIZE,
                MediaStore.Files.FileColumns.DATE_ADDED,
                MediaStore.Files.FileColumns.MIME_TYPE};
        String sortOrder = null; // unordered
        String mimeType;
        String[] selectionArgsPdf;
        Cursor c;
        String selectionMimeType = MediaStore.Files.FileColumns.MIME_TYPE + "=?";
        // only Word
        mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("docx");
        selectionArgsPdf = new String[]{mimeType};
        c = cr.query(uri, projection, selectionMimeType, selectionArgsPdf, sortOrder);
        ArrayList<InfoFile> lsFile = new ArrayList<>();
        if (c != null) {
            while (c.moveToNext()) {
                Log.d("DOCUMENT", c.getString(0) + "type=" + c.getString(4));
                String namefile = c.getString(0);
                String namepath = c.getString(1);
                String sizes = c.getString(2);
                String dates = c.getString(3);
                int size = Integer.parseInt(sizes);
                size /= 1024;
                dates = convertDate(dates);
                boolean status;
                if (size < 1000) {
                    status = true;
                } else {
                    status = false;
                }
                try {
                    if (!namefile.substring(0, 1).equals(".")) {
                        InfoFile infoFile = new InfoFile(namefile, namepath, size, dates, false, c.getString(4));
                        infoFile.setStatus(status);
                        lsFile.add(infoFile);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    InfoFile infoFile = new InfoFile(namefile, namepath, size, dates, false, c.getString(4));
                    infoFile.setStatus(status);
                    lsFile.add(infoFile);
                }

            }
        }
        hashMap.put("Word", lsFile);

        mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf");

        selectionArgsPdf = new String[]{mimeType};
        c = cr.query(uri, projection, selectionMimeType, selectionArgsPdf, sortOrder);
        ArrayList<InfoFile> lsFile1 = new ArrayList<>();
        if (c != null) {
            while (c.moveToNext()) {
                Log.d("PDF", c.getString(0) + "type=" + c.getString(4));
                String namefile = c.getString(0);
                String namepath = c.getString(1);
                String sizes = c.getString(2);
                String dates = c.getString(3);
                int size = Integer.parseInt(sizes);
                size /= 1024;
                dates = convertDate(dates);
                boolean status;
                if (size < 1000) {
                    status = true;
                } else {
                    status = false;
                }
                try {
                    if (!namefile.substring(0, 1).equals(".")) {
                        InfoFile infoFile = new InfoFile(namefile, namepath, size, dates, false, c.getString(4));
                        infoFile.setStatus(status);
                        lsFile1.add(infoFile);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    InfoFile infoFile = new InfoFile(namefile, namepath, size, dates, false, c.getString(4));
                    infoFile.setStatus(status);
                    lsFile1.add(infoFile);
                }
            }
            hashMap.put("PDF", lsFile1);
        }

        mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("xls");

        selectionArgsPdf = new String[]{mimeType};
        c = cr.query(uri, projection, selectionMimeType, selectionArgsPdf, sortOrder);
        ArrayList<InfoFile> lsFile2 = new ArrayList<>();
        if (c != null) {
            while (c.moveToNext()) {
                Log.d("EXCEL", c.getString(0) + "type=" + c.getString(4));
                String namefile = c.getString(0);
                String namepath = c.getString(1);
                String sizes = c.getString(2);
                String dates = c.getString(3);
                int size = Integer.parseInt(sizes);
                size /= 1024;
                dates = convertDate(dates);
                boolean status;
                if (size < 1000) {
                    status = true;
                } else {
                    status = false;
                }
                try {
                    if (!namefile.substring(0, 1).equals(".")) {
                        InfoFile infoFile = new InfoFile(namefile, namepath, size, dates, false, c.getString(4));
                        infoFile.setStatus(status);
                        lsFile2.add(infoFile);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    InfoFile infoFile = new InfoFile(namefile, namepath, size, dates, false, c.getString(4));
                    infoFile.setStatus(status);
                    lsFile2.add(infoFile);
                }
            }
        }
        hashMap.put("Excel", lsFile2);


        return hashMap;
    }

    public static ArrayList<InfoFile> getAllFileImage(Context context) {
        ArrayList<InfoFile> lsFile = new ArrayList<>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                MediaStore.Images.ImageColumns.DISPLAY_NAME,
                MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.ImageColumns.SIZE,
                MediaStore.Images.ImageColumns.DATE_ADDED,
                MediaStore.Images.ImageColumns.MIME_TYPE};
        Cursor c = context.getContentResolver().query(uri, projection, null, null, null);
        int vidsCount = 0;
        if (c != null) {
            vidsCount = c.getCount();
            while (c.moveToNext()) {
                Log.d("IMAGE", "name=" + c.getString(1) + "\t" + c.getString(2));
                String namealbum = c.getString(0);
                String namefile = c.getString(1);
                String namepath = c.getString(2);
                String sizes = c.getString(3);
                String dates = c.getString(4);
                int size = Integer.parseInt(sizes);
                size /= 1024;
                dates = convertDate(dates);
                boolean status;
                if (size < 1000) {
                    status = true;
                } else {
                    status = false;
                }
                lsFile.add(new InfoFile(namealbum, namefile, namepath, size, dates, false, c.getString(5), status));
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
                MediaStore.Video.VideoColumns.DATE_ADDED,
                MediaStore.Video.VideoColumns.MIME_TYPE};
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
                int size = Integer.parseInt(sizes);
                size /= 1024;
                dates = convertDate(dates);
                boolean status;
                if (size < 1000) {
                    status = true;
                } else {
                    status = false;
                }
                lsFile.add(new InfoFile(namealbum, namefile, namepath, size, dates, false, c.getString(5), status));
            }
            c.close();
        }
        return lsFile;
    }

    public static String convertDate(String myDate) {
        // or you already have long value of date, use this instead of milliseconds variable.
        String dateString = null;
        try {
            long millisecond = Long.parseLong(myDate);
            dateString = DateFormat.format("dd/MM/yyyy", new Date(millisecond)).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }


}