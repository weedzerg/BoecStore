package com.media.convert.videotomp3aacc.activities;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;
import com.media.convert.videotomp3aacc.R;
import com.media.convert.videotomp3aacc.dialog.ConvertingDialog;
import com.media.convert.videotomp3aacc.interfaces.ResultCommand;
import com.media.convert.videotomp3aacc.interfaces.UpdateProressBar;
import com.media.convert.videotomp3aacc.objects.Mp3Format;
import com.media.convert.videotomp3aacc.utils.Ads;
import com.media.convert.videotomp3aacc.utils.AnimationTranslate;
import com.media.convert.videotomp3aacc.utils.Constant;
import com.media.convert.videotomp3aacc.utils.ConvertAudioUtils;
import com.media.convert.videotomp3aacc.utils.TrimVideo;
import com.media.convert.videotomp3aacc.utils.Utils;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.io.File;
import java.io.IOException;

public class ConvertActivity extends AppCompatActivity implements ResultCommand, UpdateProressBar, AdapterView.OnItemSelectedListener {
    private VideoView video;
    private Mp3Format mp3Format;
    private ImageView iv_back;
    private TextView tv_start, tv_end, tv_second_trim, title;
    private int duration;
    //    private DiscreteSeekBar quality;
    private RangeSeekBar rangeSeekBar;
    private Runnable r;
    private FrameLayout ln_convert;
    private RelativeLayout rlads;
    private FFmpeg ffmpeg;
    private ConvertingDialog dialog;
    private ConvertAudioUtils convertAudio;
    private long start, end;
    private LinearLayout ln_mp3, ln_aac;
    private String pathout;
    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    private int id = -1;
    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;
    private String[] arr_format = new String[]{"aac", "mp3", "flac", "ogg", "m4a", "wav"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

//        String pathvideo = Environment.getExternalStorageDirectory().getPath() + "/video.mp4";
        String pathvideo = getIntent().getStringExtra(Constant.INTENTDATA);
        String namevideo = getIntent().getStringExtra(Constant.INTENTDATANAME);
        mp3Format = new Mp3Format(pathvideo, "mp3", 192000);
        mp3Format.setNamemp3(namevideo);
        loadFFMpegBinary();
        convertAudio = new ConvertAudioUtils(ffmpeg, this, this);
        init();

    }

    public void init() {

        dialog = new ConvertingDialog(ConvertActivity.this, new ConvertingDialog.OnButtonClicked() {
            @Override
            public void onRunClicked() {
                creatNotification("video");
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        rlads = (RelativeLayout) findViewById(R.id.layout_ads);
        Ads.b(this, rlads, new Ads.OnAdsListener() {
            @Override
            public void onError() {
                rlads.setVisibility(View.GONE);
                Log.d("DEBUG", "error");
            }

            @Override
            public void onAdLoaded() {
                rlads.setVisibility(View.VISIBLE);
                Log.d("DEBUG", "loaded");

            }

            @Override
            public void onAdOpened() {
                rlads.setVisibility(View.VISIBLE);
                Log.d("DEBUG", "opened");

            }
        });
//        quality = (DiscreteSeekBar) findViewById(R.id.sb_quality);
//        quality.setProgress(4);
        iv_back = (ImageView) findViewById(R.id.iv_back);

        title = (TextView) findViewById(R.id.tv_title);
//        tv_quality = (TextView) findViewById(R.id.tv_quality);
        title.setText(getString(R.string.convert));
        ln_convert = (FrameLayout) findViewById(R.id.ln_convert);
        ln_aac = (LinearLayout) findViewById(R.id.ln_formataac);
        ln_mp3 = (LinearLayout) findViewById(R.id.ln_formatmp3);
        video = (VideoView) findViewById(R.id.video);
        tv_start = (TextView) findViewById(R.id.tv_start);
        tv_end = (TextView) findViewById(R.id.tv_end);
        tv_second_trim = (TextView) findViewById(R.id.tv_second_cut);
        tv_second_trim.setVisibility(View.GONE);
        rangeSeekBar = (RangeSeekBar) findViewById(R.id.sb_seek);
        ln_convert.setOnClickListener(on_click);
        ln_mp3.setOnClickListener(on_click);
        ln_aac.setOnClickListener(on_click);
        iv_back.setOnClickListener(on_click);
        spinner = (Spinner) findViewById(R.id.sp_audio);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.audio_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        setupvideo();
        mp3Format.setQualitys(128000);
//        quality.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
//            @Override
//            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
//                getQualty(seekBar.getProgress());
//            }
//        });

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        video.start();
    }

    private View.OnClickListener on_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_back: {
                    finish();
                    AnimationTranslate.previewAnimation(ConvertActivity.this);
                    break;
                }

                case R.id.ln_convert: {
                    video.pause();
                    dialog.show();
                    if (rangeSeekBar.getSelectedMaxValue().intValue() == duration) {
                        //no trim video
                        convertaudio();
                    } else {
                        new AsynTrimVideo().execute();
                    }
                    break;
                }
//                case R.id.ln_formataac: {
//                    mp3Format.setTypeFormat("aac");
//                    iv_aac.setImageResource(R.drawable.ic_radio);
//                    iv_mp3.setImageResource(R.drawable.ic_unradio);
//                    ln_quality.setVisibility(View.GONE);
//                    break;
//                }
//                case R.id.ln_formatmp3: {
//                    mp3Format.setTypeFormat("mp3");
//                    iv_aac.setImageResource(R.drawable.ic_unradio);
//                    iv_mp3.setImageResource(R.drawable.ic_radio);
//                    ln_quality.setVisibility(View.VISIBLE);
//                    break;
//                }
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

    public void setupvideo() {
        video.setVideoPath(mp3Format.getPathin());
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

    @Override
    public void resultCommand(String result) {
        if (result.equals("ok")) {
            if (pathout.endsWith(".mp4")) {
                //
                convertaudio();
            } else {
                if (dialog.isShowing()) {
                    try {
                        Intent intent = new Intent(ConvertActivity.this, ListAudioActivity.class);
                        startActivity(intent);
                        Ads.f(ConvertActivity.this);
                        finish();
                        AnimationTranslate.previewAnimation(ConvertActivity.this);
                        dialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        } else {
            Toast.makeText(this, getString(R.string.no_sound), Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            finish();
        }
    }

    @Override
    public void updateProress(String pt) {
        if (!pt.equals("")) {
            if (dialog.isShowing()) {
                dialog.updatePro(mp3Format.getTypeFormat()
                        , ConvertFloattoString(pt));
            } else {
                int c = ConvertFloattoString(pt);
                if (c >= 100) {
                    mBuilder.setContentText(getString(R.string.convert_complete))
                            .setProgress(100, 100, false);
                    mNotifyManager.cancel(id);
                } else {
                    mBuilder.setContentText(c + "%").setProgress(100, c, false);
                    mNotifyManager.notify(id, mBuilder.build());
                }

            }
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mp3Format.setTypeFormat(arr_format[i]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public class AsynTrimVideo extends AsyncTask<Void, Void, Uri> {
        @Override
        protected Uri doInBackground(Void... voids) {
            try {
                start = rangeSeekBar.getSelectedMinValue().intValue();
                end = rangeSeekBar.getSelectedMaxValue().intValue();

                File in = new File(mp3Format.getPathin());
                File out = new File(Constant.PATHVID);
                return TrimVideo.startTrim(in, out, start, end);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(Uri uri) {
            super.onPostExecute(uri);
            if (uri != null) {
                MediaPlayer mp = MediaPlayer.create(getBaseContext(), uri);
                duration = mp.getDuration();
                Log.d("duration", duration + "");
                mp.release();
                if (duration == 0) {
                    if (end == 0) {
                        end = 1000;
                    }
                    pathout = Constant.PATHVID;
                    convertAudio.executeCutVideoCommand((int) start, (int) end, mp3Format.getPathin(), Constant.PATHVID);
                } else {
                    duration = duration / 1000;
                    mp3Format.setPathin(Constant.PATHVID);
                    convertaudio();
                }
                this.cancel(true);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!dialog.isShowing()) {
            super.onBackPressed();
            Ads.f(ConvertActivity.this);
            AnimationTranslate.previewAnimation(ConvertActivity.this);
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

    public void convertaudio() {
        pathout = Constant.PATHAUD + "/" + Utils.convertNameAudio(mp3Format.getNamemp3(),
                mp3Format.getTypeFormat());
        mp3Format.setPathout(pathout);
        convertAudio.convertToAudio(mp3Format);
    }

    @Override
    protected void onPause() {
        super.onPause();
        video.pause();
    }

    public void creatNotification(String name) {
        mNotifyManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle(name + " " + getString(R.string.convert_to))
                .setContentText(getString(R.string.convert_in))
                .setSmallIcon(R.drawable.ic_launcher);
        mBuilder.setProgress(100, 0, false);
        mNotifyManager.notify(id, mBuilder.build());

        mNotifyManager.notify(id, mBuilder.build());
    }
}



