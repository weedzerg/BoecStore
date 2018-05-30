package com.media.convert.videotomp3aacc.utils;

import android.os.Environment;

/**
 * Created by DaiPhongPC on 11/13/2017.
 */

public class Constant {
    public static final String INTENTDATA="intentdata";
    public static final String INTENTDATANAME="intentdataname";
    public static final String PATHFOLDER= Environment.getExternalStorageDirectory().getPath()+"/VideoToAudio";
    public static final String PATHVID_= Environment.getExternalStorageDirectory().getPath()+"/VideoToAudio/video";
    public static final String PATHVID= Environment.getExternalStorageDirectory().getPath()+"/VideoToAudio/video/TRIM_VID.mp4";
    public static final String PATHVIDYOU= Environment.getExternalStorageDirectory().getPath()+"/YOUTUBE_VID.mp4";
    public static final String PATHAUD= Environment.getExternalStorageDirectory().getPath()+"/VideoToAudio/audio";
    public static final String PATHRING= Environment.getExternalStorageDirectory().getPath()+"/media/ringtone";
}
