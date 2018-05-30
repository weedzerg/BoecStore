package com.media.studio.reversevideo.activitys;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.media.studio.reversevideo.R;
import com.media.studio.reversevideo.dialog.RateAppDialog;
import com.media.studio.reversevideo.utils.Ads;
import com.media.studio.reversevideo.utils.AnimationTranslate;
import com.media.studio.reversevideo.utils.Constant;
import com.media.studio.reversevideo.utils.ShowAds;
import com.media.studio.reversevideo.utils.Utils;
import com.zer.android.newsdk.ZAndroidSDK;

import java.io.File;

public class ChoiceActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ln_capture, ln_edit, ln_video, ln_rate;
    private static final String[] PERMISSION =
            {Manifest.permission.READ_EXTERNAL_STORAGE
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA};
    private RateAppDialog rateAppDialog;
    private RelativeLayout rlads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        turnPermiss();
    }

    public void turnPermiss() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Utils.checkPermission(PERMISSION, ChoiceActivity.this) == PackageManager.PERMISSION_GRANTED) {
                ZAndroidSDK.init(ChoiceActivity.this);
                init();
            } else {
                ChoiceActivity.this.requestPermissions(PERMISSION, 1);
            }
        } else {
            ZAndroidSDK.init(ChoiceActivity.this);
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0) {
                ZAndroidSDK.init(ChoiceActivity.this);
                init();
            }
        }

    }

    public void init() {
        creatFile();
        Ads.f(getBaseContext());
        rlads = (RelativeLayout) findViewById(R.id.layout_ads);
        ShowAds.showAdsNative(rlads, ChoiceActivity.this);
        ln_capture = (LinearLayout) findViewById(R.id.ln_capture);
        ln_edit = (LinearLayout) findViewById(R.id.ln_edit);
        ln_video = (LinearLayout) findViewById(R.id.ln_video);
        ln_rate = (LinearLayout) findViewById(R.id.ln_rate);
        ln_video.setOnClickListener(this);
        ln_edit.setOnClickListener(this);
        ln_capture.setOnClickListener(this);
        ln_rate.setOnClickListener(this);
        rateAppDialog = new RateAppDialog(this, new RateAppDialog.OnButtonClicked() {
            @Override
            public void onRateClicked() {
                Utils.rateApp(ChoiceActivity.this);
            }

            @Override
            public void onCancelClicked() {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ln_capture: {
                //use cammera of phone
                openCamera();
                break;
            }
            case R.id.ln_edit: {
                //edit video: strim video
                Intent intent = new Intent(ChoiceActivity.this, ListVideoActivity.class);
                startActivity(intent);
                AnimationTranslate.nextAnimation(ChoiceActivity.this);
                break;
            }
            case R.id.ln_video: {
                //load list video of reverse video
                Intent intent = new Intent(ChoiceActivity.this, MyVideoActivity.class);
                startActivity(intent);
                AnimationTranslate.nextAnimation(ChoiceActivity.this);

                break;
            }
            case R.id.ln_rate: {
                Utils.rateApp_(getBaseContext());

                //rate for app
                break;
            }
        }
    }

    public void creatFile() {
        File f = new File(Constant.PATHFOLDER);
        if (!f.exists()) {
            f.mkdirs();
        }
        File f1 = new File(Constant.PATHMYVIDEO);
        if (!f1.exists()) {
            f1.mkdirs();
        }
        File f2 = new File(Constant.PATHTEMP);
        if (!f2.exists()) {
            f2.mkdirs();
        }
        File f3 = new File(Constant.PATHTEMPIMAGE);
        if (!f3.exists()) {
            f3.mkdirs();
        }
        File f4 = new File(Constant.PATHTEMPVIDEO);
        if (!f4.exists()) {
            f4.mkdirs();
        }
    }

    public void openCamera() {
        dispatchTakeVideoIntent();
    }

    static final int REQUEST_VIDEO_CAPTURE = 100;

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            String path = getRealPathFromURI(getBaseContext(), videoUri);
            Intent intent = new Intent(ChoiceActivity.this, EditVideoActivity.class);
            intent.putExtra(Constant.DATAINTENT, path);
            startActivity(intent);

        }

    }


    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void onBackPressed() {
        rateAppDialog.show();
    }

}
