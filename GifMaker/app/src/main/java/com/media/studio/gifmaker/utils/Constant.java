package com.media.studio.gifmaker.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;


/**
 * Created by DaiPhongPC on 9/8/2017.
 */

public class Constant {
    public static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/video.mp4";
    //    public static final String PATHFOLDER = Environment.getExternalStorageDirectory().getPath() + "/Pictures/FFMPEG";
    //
    public static final String PATHFOLDER = Environment.getExternalStorageDirectory().getPath() + "/GifMaker_Video/";
    public static final String PATHTEMP = Environment.getExternalStorageDirectory().getPath() + "/GifMaker_Video/Temp";
    public static final String PATHMYGIF = Environment.getExternalStorageDirectory().getPath() + "/GifMaker_Video/Videos";
    public static final String PATHTEMPIMAGE = Environment.getExternalStorageDirectory().getPath() + "/GifMaker_Video/Temp/image";
    public static final String PATHTEMPIMAGES = Environment.getExternalStorageDirectory().getPath() + "/GifMaker_Video/Temp/images";
    public static final String PATHTEMPVIDEO = Environment.getExternalStorageDirectory().getPath() + "/GifMaker_Video/Temp/video";
    public static final String PATHTEMPCUTVIDEO = Environment.getExternalStorageDirectory().getPath() + "/GifMaker_Video/Temp/video/CUT_VIDEO.mp4";
    public static final String DATAINTENT = "intentdata";

    public static Bitmap getBitmapFromLocalPath(String path, int sampleSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize;
        Log.d("PATH", path);
        return BitmapFactory.decodeFile(path, options);
    }
}
