package com.media.convert.videotomp3aacc.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.media.convert.videotomp3aacc.R;
import com.media.convert.videotomp3aacc.dialog.RateAppDialog;
import com.media.convert.videotomp3aacc.utils.Ads;
import com.media.convert.videotomp3aacc.utils.AnimationTranslate;
import com.media.convert.videotomp3aacc.utils.Utils;
import com.zer.android.newsdk.ZAndroidSDK;

public class ChoiceActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout fr_video, fr_audio, fr_rate;
    private static final String[] PERMISSION =
            {Manifest.permission.READ_EXTERNAL_STORAGE
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private RateAppDialog rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        checkPermiss();
//        try {
            ZAndroidSDK.init(this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        final RelativeLayout layout_ads = (RelativeLayout) findViewById(R.id.layout_ads);
        Ads.b(this, layout_ads, new Ads.OnAdsListener() {
            @Override
            public void onError() {
                layout_ads.setVisibility(View.GONE);
                Log.d("DEBUG", "error");
            }

            @Override
            public void onAdLoaded() {
                layout_ads.setVisibility(View.VISIBLE);
                Log.d("DEBUG", "loaded");

            }

            @Override
            public void onAdOpened() {
                layout_ads.setVisibility(View.VISIBLE);
                Log.d("DEBUG", "opened");

            }
        });
    }

    public void init() {
        Ads.f(ChoiceActivity.this);
        rate = new RateAppDialog(ChoiceActivity.this, new RateAppDialog.OnButtonClicked() {
            @Override
            public void onRateClicked() {
                Utils.rateApp(ChoiceActivity.this);
            }

            @Override
            public void onCancelClicked() {
                finish();
            }
        });
        Utils.creatFile();
        fr_audio = (FrameLayout) findViewById(R.id.fr_myaudio);
        fr_video = (FrameLayout) findViewById(R.id.fr_videotoaudio);
        fr_rate = (FrameLayout) findViewById(R.id.fr_rateapp);
        fr_rate.setOnClickListener(this);
        fr_audio.setOnClickListener(this);
        fr_video.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fr_rateapp: {
                Utils.rateApp_(ChoiceActivity.this);
                break;
            }
            case R.id.fr_myaudio: {
                Intent intent = new Intent(ChoiceActivity.this, ListAudioActivity.class);
                startActivity(intent);
                AnimationTranslate.nextAnimation(ChoiceActivity.this);
                break;
            }
            case R.id.fr_videotoaudio: {
                Intent intent = new Intent(ChoiceActivity.this, MainActivity.class);
                startActivity(intent);
                AnimationTranslate.nextAnimation(ChoiceActivity.this);

                break;
            }
        }
    }

    public void checkPermiss() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Utils.checkPermission(PERMISSION, ChoiceActivity.this) == PackageManager.PERMISSION_GRANTED) {
                init();
            } else {
                ChoiceActivity.this.requestPermissions(PERMISSION, 1);
            }
        } else {
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                ZAndroidSDK.init(ChoiceActivity.this);
                init();
            } else {
                finish();
            }
        }

    }

    @Override
    public void onBackPressed() {
        rate.show();
    }
}
