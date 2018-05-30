package com.security.filelocker.utils;

import android.net.Uri;
import android.os.Environment;

/**
 * Created by DaiPhongPC on 9/8/2017.
 */

public class Contants {
    public static final String TAG1 = "DEBUG_decrypt";
    public static final String KEY = "hailoan9643";
    public static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/.Security/";
    public static final String FILE_INTENT = "FILE";
    public static final String DOCUMENT_INTENT = "document";
    public static final String VALUE = "value";
    public static final String VIDEO = "video";
    public static final String AUDIO = "audio";
    public static final String IMAGE = "image";
    public static final String DOCUMENT = "document";
    public static String ID_DOCUMENT = null;
    public static String PATH_DOCUMENT = null;
    public static Uri uriSD = null;
    public static final String CHECK_SD = "CHECKSD";
    public static final String ID_DOCUMENT_CACHE = "IDDOCUMENT";
    public static final String PATH_DOCUMENT_CACHE = "PATHDOCUMENT";
    public static final String URISD_CACHE = "URISD";

}
