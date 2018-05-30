package com.media.vid.video_speed.utils;

import android.os.Environment;

import com.github.hiteshsondhi88.libffmpeg.FFmpeg;


/**
 * Created by DaiPhongPC on 9/8/2017.
 */

public class Constant {
    public static final String PATHFOLDER = Environment.getExternalStorageDirectory().getPath() + "/VID_Speed/";
    public static final String PATHTEMP= Environment.getExternalStorageDirectory().getPath() + "/VID_Speed/Temp";
    public static final String PATHTEMPVIDEO  = Environment.getExternalStorageDirectory().getPath() + "/VID_Speed/Temp/video_trim";
    public static final String PATHVIDEO= Environment.getExternalStorageDirectory().getPath() + "/VID_Speed/My Video";
    public static final String DATAINTENT = "intentdata";
    public static final String DATAINTENT1 = "intentdata1";
    public static final String DATAINTENT2 = "intentdata2";
    public static FFmpeg fFmpeg=null;


}
