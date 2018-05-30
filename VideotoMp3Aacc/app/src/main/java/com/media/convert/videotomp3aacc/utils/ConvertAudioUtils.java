package com.media.convert.videotomp3aacc.utils;

import android.util.Log;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.media.convert.videotomp3aacc.interfaces.ResultCommand;
import com.media.convert.videotomp3aacc.interfaces.UpdateProressBar;
import com.media.convert.videotomp3aacc.objects.Mp3Format;

/**
 * Created by DaiPhongPC on 11/13/2017.
 */

public class ConvertAudioUtils {
    private FFmpeg ffmpeg;
    private ResultCommand resultComand;
    private UpdateProressBar update;

    public ConvertAudioUtils(FFmpeg ffmpeg, ResultCommand resultComand, UpdateProressBar update) {
        this.ffmpeg = ffmpeg;
        this.resultComand = resultComand;
        this.update = update;
    }

    private void execFFmpegBinary(final String[] command, final String type) {
        try {
            ffmpeg.execute(command, new ExecuteBinaryResponseHandler() {
                @Override
                public void onFailure(String s) {
                    Log.d("DEBUG " + type, "FAILED with output : " + s);
                    resultComand.resultCommand("fail");
                }

                @Override
                public void onSuccess(String s) {
                    Log.d("DEBUG", "SUCCESS with output : " + s);
                    resultComand.resultCommand("ok");
                }

                @Override
                public void onProgress(String s) {
                    Log.d("DEBUG", "Process command : ffmpeg " + s);
                    if (update != null) {
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
                        update.updateProress(resutl);
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

//    public void convertToAudioAAC(Mp3Format mp3Format) {
//        //ffmpeg -i video.mp4 -b:a 192K -vn music.mp3
//        String[] complexCommand = {"-y", "-i", mp3Format.getPathin(), "-vn", "-acodec", "copy", mp3Format.getPathout()};
//        execFFmpegBinary(complexCommand, "aac");
//    }
//
//    public void convertToAudioFLAC(Mp3Format mp3Format) {
//        //ffmpeg -i video.mp4 -b:a 192K -vn music.mp3
//        String[] complexCommand = {"-y", "-i", mp3Format.getPathin(), "-vn", "-acodec", "copy", mp3Format.getPathout()};
//        execFFmpegBinary(complexCommand, "aac");
//    }
//
//    public void convertToMp3(Mp3Format mp3Format) {
//        int kpsrate = (int) (mp3Format.getQualitys() / 1000);
//        String[] complexCommand = {"-y", "-i", mp3Format.getPathin(), "-vn", "-ar", "44100", "-ac", "2",
//                "-b:a", kpsrate + "k", "-f", "mp3", mp3Format.getPathout()};
//        execFFmpegBinary(complexCommand, "mp3");
//
//    }

    public void executeCutVideoCommand(int startMs, int endMs, String pathin, String pathout) {
        //String[] complexCommand = {"-i", yourRealPath, "-ss", "" + startMs / 1000, "-t", "" + endMs / 1000, dest.getAbsolutePath()};
        String[] complexCommand = {"-ss", "" + startMs / 1000, "-y", "-i", pathin, "-t", "" + (endMs - startMs) / 1000, "-vcodec",
                "mpeg4", "-b:v", "2097152", "-b:a", "48000", "-ac", "2", "-ar", "22050", pathout};
        execFFmpegBinary(complexCommand, "cutvideo");

    }

    public void convertToAudio(Mp3Format mp3Format) {

        switch (mp3Format.getTypeFormat()) {
            case "aac": {
                String[] complexCommand = {"-y", "-i", mp3Format.getPathin(), "-vn", "-acodec", "copy", mp3Format.getPathout()};
                execFFmpegBinary(complexCommand, "aac");

                break;
            }
            case "mp3": {
                int kpsrate = (int) (mp3Format.getQualitys() / 1000);
                String[] complexCommand = {"-y", "-i", mp3Format.getPathin(), "-vn", "-ar", "44100", "-ac", "2",
                        "-b:a", kpsrate + "k", "-f", "mp3", mp3Format.getPathout()};
                execFFmpegBinary(complexCommand, "mp3");
                break;
            }
            case "flac": {
                //ffmpeg -i audio.xxx -c:a flac audio.flac
                String[] complexCommand = {"-i", mp3Format.getPathin(), "-c:a", "flac", mp3Format.getPathout()};
                execFFmpegBinary(complexCommand, "flac");
                break;
            }
            case "ogg": {
                //ffmpeg -i videofile.mp4 -vn -acodec libvorbis audiofile.ogg
                String[] complexCommand = {"-i", mp3Format.getPathin(), "-vn",mp3Format.getPathout()};
                execFFmpegBinary(complexCommand, "ogg");
                break;
            }
            case "m4a": {
                //ffmpeg -i video.mp4 -vn -acodec copy audio.m4a
                String[] complexCommand = {"-i", mp3Format.getPathin(),"-vn",mp3Format.getPathout()};
                execFFmpegBinary(complexCommand, "m4a");
                break;
            }
            case "wav": {
                //ffmpeg -i 111.mp3 -acodec pcm_s16le -ac 1 -ar 16000 out.wav
                String[] complexCommand = {"-i", mp3Format.getPathin(), "-acodec", "pcm_s16le", "-ac", 1 + "", "-ar", 16000 + "", mp3Format.getPathout()};
                execFFmpegBinary(complexCommand, "wav");
                break;
            }
        }
    }
}
