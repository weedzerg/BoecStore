package com.media.vid.video_speed.utils;

import android.util.Log;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.media.vid.video_speed.interfaces.ResultComand;
import com.media.vid.video_speed.interfaces.UpdateProcessing;

/**
 * Created by DaiPhongPC on 10/17/2017.
 */

public class EditVideo {
    private FFmpeg ffmpeg;
    private ResultComand resultComand;
    private UpdateProcessing updateProcessing;

    public EditVideo(FFmpeg ffmpeg, ResultComand resultComand) {
        this.ffmpeg = ffmpeg;
        this.resultComand = resultComand;
    }

    public EditVideo(FFmpeg ffmpeg, ResultComand resultComand, UpdateProcessing updateProcessing) {
        this.ffmpeg = ffmpeg;
        this.resultComand = resultComand;
        this.updateProcessing = updateProcessing;
    }

    public void executeSlowMotionVideoCommand(String pathin, String pathout, float speed) {
        String[] complexCommand = {"-i", pathin, "-filter_complex", "[0:v]setpts=PTS/" + speed +
                "[v];[0:a]atempo=" + speed + "[a]", "-map", "[v]",
                "-map", "[a]", pathout};
        execFFmpegBinary(complexCommand, "slowmotion");
    }

    public void executeFastMotionVideoCommand(String inpath, String outpath, float speed) {
        String[] complexCommand = {"-i", inpath, "-filter_complex", "[0:v]setpts=PTS/" + speed +
                "[v];[0:a]atempo=" + speed + "[a]", "-map", "[v]",
                "-map", "[a]", outpath};
        execFFmpegBinary(complexCommand, "faster");
    }

    public void excuteMotion(String pathin, String pathour, float speed) {
        String[] commandline = new String[]{"-i", pathin, "-filter:v", "setpts=PTS/" + speed, pathour};
        execFFmpegBinary(commandline, "mute audio");
    }

//    public void excuteMotion(String pathin, String pathour, float speed) {
//        String[] commandline = new String[]{"-i", pathin, "-r", 16 + "", "-filter:v", "setpts=PTS/" + speed, pathour};
//        execFFmpegBinary(commandline, "mute audio");
//    }

    public void executeMuteVideoCommand(String inpath, String outpath) {
        Log.d("DEBUG", "startTrim: src: " + inpath);
        Log.d("DEBUG", "startTrim: dest: " + outpath);
        String[] complexCommand = {"-i", inpath, "-vcodec", "copy", "-an", outpath};
        execFFmpegBinary(complexCommand, "Mute");

    }

    public void executeCutVideoCommand(int startMs, int endMs, String pathin, String pathout) {
        //String[] complexCommand = {"-i", yourRealPath, "-ss", "" + startMs / 1000, "-t", "" + endMs / 1000, dest.getAbsolutePath()};
        String[] complexCommand = {"-ss", "" + startMs / 1000, "-y", "-i", pathin, "-t", "" + (endMs - startMs) / 1000, "-vcodec",
                "mpeg4", "-b:v", "2097152", "-b:a", "48000", "-ac", "2", "-ar", "22050", pathout};
        execFFmpegBinary(complexCommand, "cutvideo");

    }

    private void execFFmpegBinary(final String[] command, final String type) {
        try {
            ffmpeg.execute(command, new ExecuteBinaryResponseHandler() {
                @Override
                public void onFailure(String s) {
                    Log.d("DEBUG " + type, "FAILED with output : " + s);
                    resultComand.resultComand("fail");
                }

                @Override
                public void onSuccess(String s) {
                    Log.d("DEBUG", "SUCCESS with output : " + s);
                    resultComand.resultComand("ok");
                }

                @Override
                public void onProgress(String s) {

                    if (updateProcessing != null) {
                        Log.d("DEBUG", "Process command : ffmpeg " + s);
                        String resutl = "";
                        int index = s.indexOf("time=");
                        if (index != -1) {
                            try {
                                resutl = s.substring(index + 5, index + 16);
                            } catch (Exception e) {
                                resutl = "";
                                e.printStackTrace();
                            }
                        }
                        updateProcessing.updateUI(resutl);
                    }

                }

                @Override
                public void onStart() {
                    Log.d("DEBUG", "Started command : ffmpeg " + command);

                }

                @Override
                public void onFinish() {
                    Log.d("DEBUG", "Finished command : ffmpeg " + command);


                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            // do nothing for now
        }
    }


}
