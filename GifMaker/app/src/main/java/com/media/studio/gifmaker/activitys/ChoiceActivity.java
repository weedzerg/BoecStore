package com.media.studio.gifmaker.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.media.studio.gifmaker.R;
import com.media.studio.gifmaker.utils.Constant;
import com.media.studio.gifmaker.utils.Utils;

import java.io.File;

public class ChoiceActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ln_videotogif, ln_imagetogif, ln_gif, ln_rate;
    private static final String[] PERMISSION =
            {Manifest.permission.READ_EXTERNAL_STORAGE
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        turnPermiss();
    }

    public void turnPermiss() {
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
            if (grantResults.length > 0) {
                init();
            }
        }

    }

    public void init() {
        creatFile();
        ln_videotogif = (LinearLayout) findViewById(R.id.ln_videotogif);
        ln_imagetogif = (LinearLayout) findViewById(R.id.ln_imagetogif);
        ln_gif = (LinearLayout) findViewById(R.id.ln_gif);
        ln_rate = (LinearLayout) findViewById(R.id.ln_rate);
        ln_videotogif.setOnClickListener(this);
        ln_imagetogif.setOnClickListener(this);
        ln_gif.setOnClickListener(this);
        ln_rate.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ln_videotogif: {
                Intent intent = new Intent(ChoiceActivity.this, ListVideoActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.ln_imagetogif: {
                break;
            }
            case R.id.ln_gif: {
                break;
            }
            case R.id.ln_rate: {
                break;
            }
        }
    }

    public void creatFile() {
        File f = new File(Constant.PATHFOLDER);
        if (!f.exists()) {
            f.mkdirs();
        }
        File f1 = new File(Constant.PATHMYGIF);
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


}
