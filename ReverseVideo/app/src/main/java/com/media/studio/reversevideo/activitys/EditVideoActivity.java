package com.media.studio.reversevideo.activitys;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.media.studio.reversevideo.R;
import com.media.studio.reversevideo.utils.AnimationTranslate;
import com.media.studio.reversevideo.utils.Constant;
import com.media.studio.reversevideo.utils.ShowAds;
import com.media.studio.reversevideo.utils.TrimVideo;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.io.File;
import java.io.IOException;

public class EditVideoActivity extends AppCompatActivity {
    private VideoView video;
    private String pathvideo;
    private ImageView iv_back, iv_next;
    private TextView tv_start, tv_end, tv_second_trim, title;
    private int duration;
    private RangeSeekBar rangeSeekBar;
    private Runnable r;
    private LinearLayout ln_trim_video;
    private LinearLayout ln_process;
    private RelativeLayout rlads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        pathvideo = getIntent().getStringExtra(Constant.DATAINTENT);
        init();
    }


    public void init() {
        rlads = (RelativeLayout) findViewById(R.id.layout_ads);
        ShowAds.showAdsNative(rlads, EditVideoActivity.this);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        iv_next.setVisibility(View.VISIBLE);
        iv_back.setOnClickListener(on_click);
        iv_next.setOnClickListener(on_click);
        title = (TextView) findViewById(R.id.tv_title);
        title.setText(getString(R.string.edit_video));
        ln_process = (LinearLayout) findViewById(R.id.ln_pro);
        ln_process.setVisibility(View.GONE);
        ln_trim_video = (LinearLayout) findViewById(R.id.ln_trim);
        video = (VideoView) findViewById(R.id.video);
        tv_start = (TextView) findViewById(R.id.tv_start);
        tv_end = (TextView) findViewById(R.id.tv_end);
        tv_second_trim = (TextView) findViewById(R.id.tv_second_cut);
        tv_second_trim.setVisibility(View.GONE);
        rangeSeekBar = (RangeSeekBar) findViewById(R.id.sb_seek);
        video.setVideoPath(pathvideo);
        rangeSeekBar.setEnabled(false);
        video.start();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                // TODO Auto-generated method stub
                duration = mp.getDuration() / 1000;
                tv_start.setText("00:00:00");
                tv_end.setText(getTime(mp.getDuration() / 1000));
                mp.setLooping(true);
                rangeSeekBar.setRangeValues(0, duration);
                rangeSeekBar.setSelectedMinValue(0);
                rangeSeekBar.setSelectedMaxValue(duration);
                rangeSeekBar.setEnabled(true);

                rangeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
                    @Override
                    public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Number minValue, Number maxValue) {
                        video.seekTo((int) minValue * 1000);
                        tv_second_trim.setVisibility(View.VISIBLE);
                        int start = (int) bar.getSelectedMinValue();
                        int end = (int) bar.getSelectedMaxValue();
                        tv_second_trim.setText(getTime(end - start));
                        Log.d("DEBUG", (end - start) + "");
                        tv_start.setText(getTime((int) bar.getSelectedMinValue()));

                        tv_end.setText(getTime((int) bar.getSelectedMaxValue()));

                    }

                });

                final Handler handler = new Handler();
                handler.postDelayed(r = new Runnable() {
                    @Override
                    public void run() {

                        if (video.getCurrentPosition() >= rangeSeekBar.getSelectedMaxValue().intValue() * 1000)
                            video.seekTo(rangeSeekBar.getSelectedMinValue().intValue() * 1000);
                        handler.postDelayed(r, 1000);
                    }
                }, 1000);

            }
        });
        ln_trim_video.setOnClickListener(on_click);
    }

    private View.OnClickListener on_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_back: {
                    finish();
                    AnimationTranslate.previewAnimation(EditVideoActivity.this);

                    break;
                }
                case R.id.iv_next: {
                    Intent intent = new Intent(EditVideoActivity.this, SlideVideoActivity.class);
                    intent.putExtra(Constant.DATAINTENT, pathvideo);
                    startActivity(intent);
                    AnimationTranslate.nextAnimation(EditVideoActivity.this);

                    break;
                }
                case R.id.ln_trim: {
                    ln_process.setVisibility(View.VISIBLE);
                    video.pause();
                    new AsynTrimVideo().execute();
                    break;
                }
            }
        }
    };

    private String getTime(int seconds) {
        int hr = seconds / 3600;
        int rem = seconds % 3600;
        int mn = rem / 60;
        int sec = rem % 60;
        return String.format("%02d", hr) + ":" + String.format("%02d", mn) + ":" + String.format("%02d", sec);
    }


    public class AsynTrimVideo extends AsyncTask<Void, Void, Uri> {
        @Override
        protected Uri doInBackground(Void... voids) {
            try {
                long start = rangeSeekBar.getSelectedMinValue().intValue() * 1000;
                long end = rangeSeekBar.getSelectedMaxValue().intValue() * 1000;
                File in = new File(pathvideo);
                File out = new File(Constant.PATHTEMPCUTVIDEO);
                return TrimVideo.startTrim(in, out, start, end);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(Uri uri) {
            super.onPostExecute(uri);
            ln_process.setVisibility(View.GONE);
            if (uri != null) {
                Intent intent = new Intent(EditVideoActivity.this, SlideVideoActivity.class);
                intent.putExtra(Constant.DATAINTENT, Constant.PATHTEMPCUTVIDEO);
                startActivity(intent);
                AnimationTranslate.nextAnimation(EditVideoActivity.this);
            }
        }


    }
}
