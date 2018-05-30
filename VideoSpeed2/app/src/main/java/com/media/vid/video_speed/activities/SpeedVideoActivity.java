package com.media.vid.video_speed.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.util.Util;
import com.media.vid.video_speed.R;
import com.media.vid.video_speed.dialog.ProressDialog;
import com.media.vid.video_speed.dialog.WarmDialog;
import com.media.vid.video_speed.interfaces.ResultComand;
import com.media.vid.video_speed.interfaces.UpdateProcessing;
import com.media.vid.video_speed.objects.FileMp4Convert;
import com.media.vid.video_speed.utils.AnimationTranslate;
import com.media.vid.video_speed.utils.Constant;
import com.media.vid.video_speed.utils.EditVideo;
import com.media.vid.video_speed.utils.ShowAds;
import com.media.vid.video_speed.utils.Utils;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

public class SpeedVideoActivity extends AppCompatActivity implements ResultComand, UpdateProcessing {
    private boolean playWhenReady = true;
    private long playbackPosition;
    private int currentWindow;
    private SimpleExoPlayerView playerView;
    private SimpleExoPlayer player;
    private Uri uri;
    private LinearLayout ln_startconvert, ln_audio;
    private ImageView iv_back, iv_audio;
    private TextView tv_title;
    private DiscreteSeekBar delay;
    private TextView tv_speed;
    private int delaymax, delaymin;
    private FileMp4Convert mp4;
    private EditVideo editvideo;
    private ProressDialog diaglog;
    private int duration;
    private WarmDialog warm;
    private RelativeLayout rlads;

    //true slow
    //false fast
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_video);
        editvideo = new EditVideo(Constant.fFmpeg, this, this);
        String pathvideo = getIntent().getStringExtra(Constant.DATAINTENT);
        boolean check = getIntent().getBooleanExtra(Constant.DATAINTENT1, true);
        mp4 = new FileMp4Convert();
        mp4.setPathin(pathvideo);
        mp4.setSlomotion(check);
        mp4.setSpeed(1f);
        mp4.setAudio(false);
        Log.d("DEBUG", check + "");
        init();
    }

    public void updateIU(boolean check) {
        if (check) {
            if (mp4.isSlomotion()) {
                delaymax = 10;
                delaymin = 5;
                delay.setMax(delaymax);
                delay.setMin(delaymin);
                delay.setProgress(delaymax);
                tv_speed.setText("1x");
            } else {
                delaymax = 20;
                delaymin = 10;
                delay.setMax(delaymax);
                delay.setMin(delaymin);
                delay.setProgress(delaymin);
                tv_speed.setText("1x");
            }
        } else {
            if (mp4.isSlomotion()) {
                delaymax = 10;
                delaymin = 1;
                delay.setMax(delaymax);
                delay.setMin(delaymin);
                delay.setProgress(delaymax);
                tv_speed.setText("1x");
            } else {
                delaymax = 40;
                delaymin = 10;
                delay.setMax(delaymax);
                delay.setMin(delaymin);
                delay.setProgress(delaymin);
                tv_speed.setText("1x");
            }
        }
    }

    public void init() {
            rlads = (RelativeLayout) findViewById(R.id.layout_ads);
            ShowAds.showAdsNative(rlads,SpeedVideoActivity.this);
        warm = new WarmDialog(SpeedVideoActivity.this, new WarmDialog.OnButtonClicked() {
            @Override
            public void okClick() {
            }
        });
        diaglog = new ProressDialog(SpeedVideoActivity.this, new ProressDialog.OnButtonClicked() {
            @Override
            public void onRunClicked() {

            }
        }, true);
        diaglog.setCanceledOnTouchOutside(false);
        delay = (DiscreteSeekBar) findViewById(R.id.delay);
        playerView = (SimpleExoPlayerView) findViewById(R.id.video);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_speed = (TextView) findViewById(R.id.tv_speed);
        ln_startconvert = (LinearLayout) findViewById(R.id.ln_start);
        ln_audio = (LinearLayout) findViewById(R.id.ln_audio);
        iv_audio = (ImageView) findViewById(R.id.iv_audio);
        ln_audio.setOnClickListener(on_click);
        ln_startconvert.setOnClickListener(on_click);
        tv_title.setText(getString(R.string.edit_speed));
        iv_back.setOnClickListener(on_click);
        updateIU(mp4.isAudio());
        showVideo();
        delay.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                float speed = ((float) value / 10);
                tv_speed.setText(speed + "x");
                if (speed == 2.6f) {
                    warm.show();
                }
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
                float speed = ((float) seekBar.getProgress() / 10);
                Log.d("DEBUG", speed + "");
                player.setPlaybackParameters(new PlaybackParameters(speed, speed));
                mp4.setSpeed(speed);
            }
        });
    }

    private View.OnClickListener on_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_back: {
                    finish();
                    AnimationTranslate.previewAnimation(SpeedVideoActivity.this);
                    break;
                }
                case R.id.ln_start: {
                    player.setPlayWhenReady(false);
                    diaglog.show();
                    String type = mp4.getPathin().substring(mp4.getPathin().indexOf("."),
                            mp4.getPathin().length());
                    if (mp4.isAudio()) {
                        if (mp4.isSlomotion()) {
                            String pathout = Utils.creatNameFileVideo(Constant.PATHVIDEO, mp4.isSlomotion(), type);
                            mp4.setPathout(pathout);
                            editvideo.executeSlowMotionVideoCommand(mp4.getPathin(), mp4.getPathout(), mp4.getSpeed());
                        } else {
                            String pathout = Utils.creatNameFileVideo(Constant.PATHVIDEO, mp4.isSlomotion(), type);
                            mp4.setPathout(pathout);
                            editvideo.executeFastMotionVideoCommand(mp4.getPathin(), mp4.getPathout(), mp4.getSpeed());
                        }
                    } else {
                        String pathout = Utils.creatNameFileVideo(Constant.PATHTEMP, mp4.isSlomotion(), type);
                        mp4.setPathout(pathout);
                        editvideo.excuteMotion(mp4.getPathin(), mp4.getPathout(), mp4.getSpeed());
                    }
                    break;
                }
                case R.id.ln_audio: {
                    if (mp4.isAudio()) {
                        iv_audio.setImageResource(R.drawable.ic_uncheck);
                        mp4.setAudio(false);
                        updateIU(mp4.isAudio());
                    } else {
                        iv_audio.setImageResource(R.drawable.ic_check);
                        mp4.setAudio(true);
                        updateIU(mp4.isAudio());
                    }
                }

            }
        }
    };

    public void showVideo() {
        uri = Uri.parse(mp4.getPathin());
        initializePlayer();
    }

    private void initializePlayer() {
        if (player == null) {
            // a factory to create an AdaptiveVideoTrackSelection
            TrackSelection.Factory adaptiveTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);

            player = ExoPlayerFactory.newSimpleInstance(
                    new DefaultRenderersFactory(this),
                    new DefaultTrackSelector(adaptiveTrackSelectionFactory),
                    new DefaultLoadControl());
        }
        playerView.setPlayer(player);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.setRepeatMode(Player.REPEAT_MODE_ALL);
        player.addListener(new ComponentListener());
        player.setPlaybackParameters(new PlaybackParameters(1f, 1f));
        prepareExoPlayerFromFileUri(uri);
        MediaPlayer me = MediaPlayer.create(SpeedVideoActivity.this, uri);
        duration = me.getDuration() / 1000;
    }

    private void prepareExoPlayerFromFileUri(Uri uri) {
        DataSpec dataSpec = new DataSpec(uri);
        final FileDataSource fileDataSource = new FileDataSource();
        try {
            fileDataSource.open(dataSpec);
        } catch (FileDataSource.FileDataSourceException e) {
            e.printStackTrace();
        }

        DataSource.Factory factory = new DataSource.Factory() {
            @Override
            public DataSource createDataSource() {
                return fileDataSource;
            }
        };
        MediaSource videoSource = new ExtractorMediaSource(fileDataSource.getUri(),
                factory, new DefaultExtractorsFactory(), null, null);
        player.prepare(videoSource);
    }

    @Override
    public void resultComand(String resutl) {
        if (resutl.equals("ok")) {
            if (!mp4.isAudio()) {
                String pathin = mp4.getPathout();
                String type = pathin.substring(pathin.indexOf("."),
                        pathin.length());
                String pathout = Utils.creatNameFileVideo(Constant.PATHVIDEO, mp4.isSlomotion(), type);
                mp4.setPathin(pathin);
                mp4.setPathout(pathout);
                editvideo.executeMuteVideoCommand(mp4.getPathin(), mp4.getPathout());
                mp4.setAudio(true);
            } else {
                diaglog.dismiss();
                Utils.removeFolder();
                Intent intent = new Intent(SpeedVideoActivity.this, SlideVideoActivity.class);
                intent.putExtra(Constant.DATAINTENT,mp4.getPathout());
                startActivity(intent);
                finish();
            }

        } else {
            diaglog.dismiss();
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void updateUI(String s) {
        if (!s.equals("")) {
            Log.d("DEBUG", s);
            if (diaglog.isShowing()) {
                int convert=ConvertFloattoString(s);
                diaglog.updatePro(convert);
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

    private class ComponentListener implements ExoPlayer.EventListener {

        @Override
        public void onTimelineChanged(Timeline timeline, Object manifest) {

        }

        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

        }

        @Override
        public void onLoadingChanged(boolean isLoading) {

        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady,
                                         int playbackState) {

        }

        @Override
        public void onRepeatModeChanged(int repeatMode) {
            player.seekTo(0);
        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {

        }

        @Override
        public void onPositionDiscontinuity() {

        }

        @Override
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

        }
    }

    private static final DefaultBandwidthMeter BANDWIDTH_METER =
            new DefaultBandwidthMeter();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        hideSystemUi();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        } else {
            pausePlayer();
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    private void pausePlayer() {
        player.setPlayWhenReady(false);
        player.getPlaybackState();
    }

    private void startPlayer() {
        player.setPlayWhenReady(true);
        player.getPlaybackState();
    }

    @Override
    public void onBackPressed() {
        pausePlayer();
        super.onBackPressed();
    }
}
