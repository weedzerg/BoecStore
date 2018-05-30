package com.security.filelocker.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by DaiPhongPC on 9/18/2017.
 */

public class DocumentContactsFile {
    public static Uri createDirectory(Activity activity, Uri uri,
                                      String idDir, String directoryName,
                                      String typeFile) {
        ContentResolver contentResolver = activity.getContentResolver();
        Uri docUri = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            docUri = DocumentsContract.buildDocumentUriUsingTree(uri, idDir);
            Uri directoryUri = null;
            try {
                directoryUri = DocumentsContract.createDocument(contentResolver, docUri, typeFile, directoryName);
                return directoryUri;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    public static Uri coppyDocumentContanst(ContentResolver contentResolver, Uri uri1, Uri uri2) {
        Uri docnull = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                docnull = DocumentsContract.copyDocument(contentResolver, uri1, uri2);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return docnull;
    }

    public static Uri renameDocumentContanst(ContentResolver contentResolver, Uri uri, String namedisplay) {
        Uri docnull = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                docnull = DocumentsContract.renameDocument(contentResolver, uri, namedisplay);
                Log.d("DEBUG",docnull.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return docnull;
    }

    public static FileOutputStream alterDocument(Activity activity, Uri uri) {
        try {
            ParcelFileDescriptor pfd = activity.getContentResolver().
                    openFileDescriptor(uri, "w");
            FileOutputStream fileOutputStream =
                    new FileOutputStream(pfd.getFileDescriptor());
            return fileOutputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
