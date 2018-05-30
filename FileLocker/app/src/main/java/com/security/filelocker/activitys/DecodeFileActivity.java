package com.security.filelocker.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.security.filelocker.R;
import com.security.filelocker.utils.AnimationTranslate;
import com.security.filelocker.utils.Contants;
import com.security.filelocker.utils.ShowAds;

public class DecodeFileActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ln_image, ln_audio, ln_video, ln_document;
    private Toolbar toolbar;
    private RelativeLayout adsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode_file);
        init();
    }

    public void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        adsView= (RelativeLayout) findViewById(R.id.layout_ads);
        updateToolbar();
        ln_image = (LinearLayout) findViewById(R.id.ln_images);
        ln_audio = (LinearLayout) findViewById(R.id.ln_audios);
        ln_video = (LinearLayout) findViewById(R.id.ln_videos);
        ln_document = (LinearLayout) findViewById(R.id.ln_documents);
        ln_image.setOnClickListener(this);
        ln_audio.setOnClickListener(this);
        ln_video.setOnClickListener(this);
        ln_document.setOnClickListener(this);
        ShowAds.showAdsNative(adsView,DecodeFileActivity.this);

    }

    public void updateToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle(getString(R.string.security));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationTranslate.previewAnimation(DecodeFileActivity.this);
                finish();
            }
        });
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ShowAllFileDecodeActivity.class);
        switch (view.getId()){
            case R.id.ln_images: {
                intent.putExtra(Contants.VALUE, 0);
                break;
            }
            case R.id.ln_audios: {
                intent.putExtra(Contants.VALUE, 1);
                break;
            }
            case R.id.ln_videos: {
                intent.putExtra(Contants.VALUE, 2);
                break;
            }
            case R.id.ln_documents: {
                intent.putExtra(Contants.VALUE, 3);
                break;
            }
        }
        startActivity(intent);
        AnimationTranslate.nextAnimation(DecodeFileActivity.this);

    }
}
