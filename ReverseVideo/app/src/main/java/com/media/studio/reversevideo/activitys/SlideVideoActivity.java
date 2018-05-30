package com.media.studio.reversevideo.activitys;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;
import com.media.studio.reversevideo.R;
import com.media.studio.reversevideo.dialog.ProressDialog;
import com.media.studio.reversevideo.dialog.ShareDialog;
import com.media.studio.reversevideo.interfaces.ReceiveResultComand;
import com.media.studio.reversevideo.interfaces.UpdateProcessing;
import com.media.studio.reversevideo.utils.AnimationTranslate;
import com.media.studio.reversevideo.utils.Constant;
import com.media.studio.reversevideo.utils.EditVideo;
import com.media.studio.reversevideo.utils.ShowAds;
import com.media.studio.reversevideo.utils.Utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class SlideVideoActivity extends AppCompatActivity implements ReceiveResultComand, UpdateProcessing {
    private VideoView video;
    private FFmpeg ffmpeg;
    private LinearLayout ln_reverse, ln_slowmotion;
    private ImageView iv_back, iv_share;
    private TextView title;
    private EditVideo editVideo;
    private String pathvideo;
    private int postion = 0;
    private String choice = "";
    private String pathout;
    private String typevideo = "video/*";
    private ShareDialog sharedialog;
    private Bitmap bitmap;
    private ImageView iv_preview;
    private RelativeLayout rlads;
    private ProressDialog diaglog;
    private int duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_video);
//        pathvideo = Constant.PATH;
        pathvideo = getIntent().getStringExtra(Constant.DATAINTENT);
        bitmap = ThumbnailUtils.createVideoThumbnail(pathvideo,
                MediaStore.Images.Thumbnails.MINI_KIND);
        ffmpeg = Constant.ffmpeg;
        loadFFMpegBinary();
        editVideo = new EditVideo(ffmpeg, this, this);
        init();

    }


    private void loadFFMpegBinary() {
        try {
            if (ffmpeg == null) {
                Log.d("DEBUG", "ffmpeg : era nulo");
                ffmpeg = FFmpeg.getInstance(this);
            }
            ffmpeg.loadBinary(new LoadBinaryResponseHandler() {
                @Override
                public void onFailure() {
                    Log.d("DEBUG", "ffmpeg : fail Loaded");
                }

                @Override
                public void onSuccess() {
                    Log.d("DEBUG", "ffmpeg : correct Loaded");
                }
            });
        } catch (FFmpegNotSupportedException e) {
        } catch (Exception e) {
            Log.d("DEBUG", "EXception no controlada : " + e);
        }
    }

    public void init() {

        rlads = (RelativeLayout) findViewById(R.id.layout_ads);
        ShowAds.showAdsNative(rlads, SlideVideoActivity.this);
        diaglog = new ProressDialog(SlideVideoActivity.this, new ProressDialog.OnButtonClicked() {
            @Override
            public void onRunClicked() {

            }
        }, true);
        diaglog.setCanceledOnTouchOutside(false);
        sharedialog = new ShareDialog(this, new ShareDialog.OnButtonClicked() {
            @Override
            public void onShareFBClicked() {
                if (isPackageInstalled("com.facebook.katana")) {
                    if (pathout != null) {
                        creatFacebook(typevideo, pathout);
                    }
                } else {
                    Toast.makeText(SlideVideoActivity.this, getString(R.string.fb_notavainle), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onShareISClicked() {
                if (isPackageInstalled("com.instagram.android")) {
                    if (pathout != null) {
                        creatInInstagram(typevideo, pathout);
                    }
                } else {
                    Toast.makeText(SlideVideoActivity.this, getString(R.string.is_notavaible), Toast.LENGTH_SHORT).show();
                }
            }
        });
        iv_preview = (ImageView) findViewById(R.id.iv_preview);
        iv_preview.setImageBitmap(bitmap);

        video = (VideoView) findViewById(R.id.vv_video);
        ln_reverse = (LinearLayout) findViewById(R.id.ln_reverse);
        ln_slowmotion = (LinearLayout) findViewById(R.id.ln_slow);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_share = (ImageView) findViewById(R.id.iv_share);
        iv_share.setVisibility(View.VISIBLE);
        title = (TextView) findViewById(R.id.tv_title);
        title.setText(getString(R.string.slide_video));
        ln_reverse.setOnClickListener(on_click);
        ln_slowmotion.setOnClickListener(on_click);
        iv_back.setOnClickListener(on_click);
        iv_share.setOnClickListener(on_click);
        video.setVideoPath(pathvideo);
        video.start();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                duration = mp.getDuration() / 1000;
            }
        });
    }

    private View.OnClickListener on_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ln_reverse: {
                    choice = "reverse";
                    if (video.isPlaying()) {
                        video.pause();
                    }
                    String pathimage = Constant.PATHTEMPIMAGE + "/image%04d.JPEG";
                    editVideo.extractImagesVideo(pathvideo, pathimage, duration);
                    diaglog.show();
                    break;
                }
                case R.id.ln_slow: {
                    choice = "slowmotion";
                    if (video.isPlaying()) {
                        video.pause();
                    }
                    pathout = Constant.PATHMYVIDEO + "/" + Utils.convertSLOWMOTION();
                    editVideo.executeSlowMotionVideoCommand(pathvideo, pathout);
                    diaglog.show();
                    break;
                }
                case R.id.iv_back: {
                    finish();
                    AnimationTranslate.previewAnimation(SlideVideoActivity.this);
                    break;
                }
                case R.id.iv_share: {
                    sharedialog.show();
                    break;
                }
            }
        }
    };

    @Override
    public void loadVideo(String result) {
        if (result.equals("ok")) {
            if (choice.equals("slowmotion")) {
                diaglog.dismiss();
                video.setVideoPath(pathout);
                video.start();
                Toast.makeText(this, pathout, Toast.LENGTH_SHORT).show();
            } else if (choice.equals("reverse")) {
                if (postion == 0) {
                    postion++;
                    Utils.reverseFileImage();
                    pathout = Constant.PATHMYVIDEO + "/" + Utils.convertNameVideo();
                    editVideo.creatvideotoImage(Constant.PATHTEMPIMAGES + "/image%04d.JPEG", pathout);
                } else if (postion == 1) {
                    diaglog.dismiss();
                    video.setVideoPath(pathout);
                    video.start();
                    Toast.makeText(this, pathout, Toast.LENGTH_SHORT).show();
                    ln_reverse.setVisibility(View.VISIBLE);
                    ln_slowmotion.setVisibility(View.VISIBLE);
                    try {
                        FileUtils.deleteDirectory(new File(Constant.PATHTEMPIMAGES));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    @Override
    public void showVideo(String result) {

    }

    private boolean isPackageInstalled(String packagename) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public void creatInInstagram(String type, String mediaPath) {
        // Create the new Intent using the 'Send' action.
        Intent share = new Intent(Intent.ACTION_SEND);

        // Set the MIME type
        share.setType(type);
        share.setPackage("com.instagram.android");

        // Create the URI from the media
        File media = new File(mediaPath);
        Uri uri = Uri.fromFile(media);

        // Add the URI to the Intent.
        share.putExtra(Intent.EXTRA_STREAM, uri);

        // Broadcast the Intent.
        startActivity(Intent.createChooser(share, "Share to"));

    }

    public void creatFacebook(String type, String mediaPath) {
        // Create the new Intent using the 'Send' action.
        Intent share = new Intent(Intent.ACTION_SEND);

        // Set the MIME type
        share.setType(type);
        share.setPackage("com.facebook.katana");

        // Create the URI from the media
        File media = new File(mediaPath);
        Uri uri = Uri.fromFile(media);

        // Add the URI to the Intent.
        share.putExtra(Intent.EXTRA_STREAM, uri);

        // Broadcast the Intent.
        startActivity(Intent.createChooser(share, "Share to"));

    }

    @Override
    public void updateUI(String s) {
        if (!s.equals("")) {
            Log.d("DEBUG", s);
            if (diaglog.isShowing()) {
                if (choice.equals("reverse") && postion == 1) {
                    int convert = ConvertFloattoString(s);
                    diaglog.updatepro(false);
                    diaglog.updatePro(convert);
                } else {
                    diaglog.updatepro(true);
                }
            } else {
//                int c = ConvertFloattoString(s);
//                if (c == 100) {
//                    mBuilder.setContentText(getString(R.string.convert_complete))
//                            .setProgress(100, 100, false);
//                    mNotifyManager.cancel(id);
//                } else {
//
//                    mBuilder.setContentText(c + "%").setProgress(100, c, false);
//                    mNotifyManager.notify(id, mBuilder.build());
//                }
//
            }
        }
    }

    public int ConvertFloattoString(String n) {
        String[] slip = n.split(":");
        float h = Float.parseFloat(slip[0]);
        float m = Float.parseFloat(slip[1]);
        float s = Float.parseFloat(slip[2]);
        float time = (h * 3600 + m * 60 + s);
        Log.d("DRUATION " + duration, time + "\t" + time / duration);
        float pt = (float) (time / (float) duration) * 100;
        return (int) pt;
    }
}
