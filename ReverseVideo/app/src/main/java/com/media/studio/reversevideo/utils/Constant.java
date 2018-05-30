package com.media.studio.reversevideo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.github.hiteshsondhi88.libffmpeg.FFmpeg;

/**
 * Created by DaiPhongPC on 9/8/2017.
 */

public class Constant {
    public static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/video.mp4";
    //    public static final String PATHFOLDER = Environment.getExternalStorageDirectory().getPath() + "/Pictures/FFMPEG";
    //
    public static final String PATHFOLDER = Environment.getExternalStorageDirectory().getPath() + "/Reverse Video";
    public static final String PATHTEMP = Environment.getExternalStorageDirectory().getPath() + "/Reverse Video/Temp";
    public static final String PATHMYVIDEO = Environment.getExternalStorageDirectory().getPath() + "/Reverse Video/Videos";
    public static final String PATHTEMPIMAGE = Environment.getExternalStorageDirectory().getPath() + "/Reverse Video/Temp/image";
    public static final String PATHTEMPIMAGES = Environment.getExternalStorageDirectory().getPath() + "/Reverse Video/Temp/images";
    public static final String PATHTEMPVIDEO = Environment.getExternalStorageDirectory().getPath() + "/Reverse Video/Temp/video";
    public static final String PATHTEMPCUTVIDEO = Environment.getExternalStorageDirectory().getPath() + "/Reverse Video/Temp/video/CUT_VIDEO.mp4";
    public static final String DATAINTENT = "intentdata";
    public static FFmpeg ffmpeg = null;

    public static Bitmap getBitmapFromLocalPath(String path, int sampleSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize;
        Log.d("PATH", path);
        return BitmapFactory.decodeFile(path, options);
    }
}
