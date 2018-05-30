package com.media.vid.video_speed.activities;

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
import android.widget.Toast;

import com.media.vid.video_speed.R;
import com.media.vid.video_speed.dialog.RateAppDialog;
import com.media.vid.video_speed.utils.Ads;
import com.media.vid.video_speed.utils.Constant;
import com.media.vid.video_speed.utils.Utils;
import com.zer.android.newsdk.ZAndroidSDK;

public class MainActivity extends AppCompatActivity {
    private LinearLayout iv_capture, iv_myvideo, iv_rate, iv_speedvideo;
    private static final String[] PERMISSION =
            {Manifest.permission.READ_EXTERNAL_STORAGE
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private RateAppDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermiss();
    }

    public void init() {
        Utils.creatFile();
        dialog = new RateAppDialog(MainActivity.this, new RateAppDialog.OnButtonClicked() {
            @Override
            public void onRateClicked() {
                Utils.rateApp(MainActivity.this);
            }

            @Override
            public void onCancelClicked() {
                finish();

            }
        });
        iv_capture = (LinearLayout) findViewById(R.id.iv_capture);
        iv_myvideo = (LinearLayout) findViewById(R.id.iv_video);
        iv_rate = (LinearLayout) findViewById(R.id.rateapp);
        iv_speedvideo = (LinearLayout) findViewById(R.id.iv_videospeed);
        iv_speedvideo.setOnClickListener(on_click);
        iv_capture.setOnClickListener(on_click);
        iv_myvideo.setOnClickListener(on_click);
        iv_rate.setOnClickListener(on_click);
        Ads.f(MainActivity.this);
    }

    private View.OnClickListener on_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_capture:
                    openCamera();
                    break;
                case R.id.iv_video:
                    Intent intent = new Intent(MainActivity.this, ListVideoActivity.class);
                    intent.putExtra(Constant.DATAINTENT, false);
                    startActivity(intent);
                    break;
                case R.id.rateapp:
                    Utils.rateApp_(MainActivity.this);
                    break;
                case R.id.iv_videospeed:
                    Intent intent1 = new Intent(MainActivity.this, ListVideoActivity.class);
                    intent1.putExtra(Constant.DATAINTENT, true);
                    startActivity(intent1);
                    break;
            }
        }
    };

    public void checkPermiss() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Utils.checkPermission(PERMISSION, MainActivity.this) == PackageManager.PERMISSION_GRANTED) {
                ZAndroidSDK.init(MainActivity.this);
                init();
            } else {
                MainActivity.this.requestPermissions(PERMISSION, 1);
            }
        } else {
            ZAndroidSDK.init(MainActivity.this);
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ZAndroidSDK.init(MainActivity.this);
                    init();
                } else {
                    finish();
                }
                break;
            }
            case 2: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakeVideoIntent();
                } else {
                    Toast.makeText(this, "permission dinend", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (requestCode == 1) {

        }

    }

    public void openCamera() {
        String[] s = new String[]{Manifest.permission.CAMERA};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Utils.checkPermission(s, MainActivity.this) == PackageManager.PERMISSION_GRANTED) {
                dispatchTakeVideoIntent();
            } else {
                MainActivity.this.requestPermissions(s, 2);
            }
        } else {
            dispatchTakeVideoIntent();
        }
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
            Intent intent = new Intent(MainActivity.this, TrimVideoActivity.class);
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
        dialog.show();
    }
}
