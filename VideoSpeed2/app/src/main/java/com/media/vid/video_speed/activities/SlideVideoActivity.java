package com.media.vid.video_speed.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.media.vid.video_speed.R;
import com.media.vid.video_speed.utils.AnimationTranslate;
import com.media.vid.video_speed.utils.Constant;
import com.media.vid.video_speed.utils.ShowAds;

import java.io.File;

public class SlideVideoActivity extends AppCompatActivity {
    private VideoView video;
    private String pathvideo;
    private LinearLayout ln_fb, ln_in, ln_share;
    private ImageView iv_back;
    private String packFB, packIN;
    private TextView tv_link, tv_title, tv_next;
    private RelativeLayout rlads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_video);
        pathvideo = getIntent().getStringExtra(Constant.DATAINTENT);
        packFB = "com.facebook.katana";
        packIN = "com.instagram.android";
        init();
    }

    public void init() {
        rlads = (RelativeLayout) findViewById(R.id.layout_ads);
        ShowAds.showAdsNative(rlads, SlideVideoActivity.this);
        video = (VideoView) findViewById(R.id.video);
        ln_fb = (LinearLayout) findViewById(R.id.ln_fb);
        ln_in = (LinearLayout) findViewById(R.id.ln_in);
        ln_share = (LinearLayout) findViewById(R.id.ln_more);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_next = (TextView) findViewById(R.id.tv_next);
        tv_next.setText(getString(R.string.finish));
        tv_title.setText(getString(R.string.fs));
        tv_link = (TextView) findViewById(R.id.tv_link);
        tv_link.setText(pathvideo);
        ln_fb.setOnClickListener(on_click);
        ln_in.setOnClickListener(on_click);
        ln_share.setOnClickListener(on_click);
        iv_back.setOnClickListener(on_click);
        setupvideo();
    }

    private View.OnClickListener on_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_back:
                    finish();
                    AnimationTranslate.previewAnimation(SlideVideoActivity.this);
                    break;
                case R.id.ln_fb:
                    if (isPackageInstalled(packFB)) {
                        creatShare(packFB, pathvideo);
                    } else {
                        Toast.makeText(SlideVideoActivity.this, getString(R.string.fb_notavainle), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.ln_in:
                    if (isPackageInstalled(packIN)) {
                        creatShare(packIN, pathvideo);
                    } else {
                        Toast.makeText(SlideVideoActivity.this, getString(R.string.is_notavaible), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.ln_more:
                    creatShare("", pathvideo);
                    break;
            }
        }
    };

    public void creatShare(String packagename, String mediaPath) {
        // Create the new Intent using the 'Send' action.
        Intent share = new Intent(Intent.ACTION_SEND);

        // Set the MIME type
        share.setType("video/*");
        share.setPackage(packagename);

        // Create the URI from the media
        File media = new File(mediaPath);
        Uri uri = Uri.fromFile(media);

        // Add the URI to the Intent.
        share.putExtra(Intent.EXTRA_STREAM, uri);

        // Broadcast the Intent.
        startActivity(Intent.createChooser(share, "Share to"));

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

    public void setupvideo() {
        video.setVideoPath(pathvideo);
        video.start();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                // TODO Auto-generated method stub
                mp.setLooping(true);

            }
        });
    }

    @Override
    protected void onPause() {
        if (video != null) {
            if (video.isPlaying()) {

                video.pause();
            }
        }
        super.onPause();
    }

    @Override
    protected void onRestart() {
        if (video != null) {
            if (!video.isPlaying()) {
                video.pause();
            }
        }
        super.onRestart();
    }
}
