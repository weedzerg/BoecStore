package com.media.convert.videotomp3aacc.activities;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.media.convert.videotomp3aacc.R;
import com.media.convert.videotomp3aacc.adapters.AdapterAudio;
import com.media.convert.videotomp3aacc.dialog.PlayAudioDialog;
import com.media.convert.videotomp3aacc.dialog.ProressDialog;
import com.media.convert.videotomp3aacc.dialog.RemoveDialog;
import com.media.convert.videotomp3aacc.objects.InfoFile;
import com.media.convert.videotomp3aacc.utils.Ads;
import com.media.convert.videotomp3aacc.utils.AnimationTranslate;
import com.media.convert.videotomp3aacc.utils.Constant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ListAudioActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private ArrayList<InfoFile> ls;
    private AdapterAudio adapter;
    private ListView lsview;
    private ProressDialog pro;
    private PlayAudioDialog playAudioDialog;
    private MediaPlayer mediaPlayer;
    private static final String[] PERMISSION =
            {Manifest.permission.WRITE_SETTINGS};
    private String path;
    private ImageView iv_back;
    private TextView tv_titel;
    private RelativeLayout rlads;
    private RemoveDialog removediaglog;
    private String itemremove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_audio);
        ls = new ArrayList<>();
        init();
    }

    public void init() {
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
        removediaglog = new RemoveDialog(ListAudioActivity.this, new RemoveDialog.OnButtonClicked() {
            @Override
            public void onRateClicked() {
                if (itemremove != null) {
                    File file = new File(itemremove);
                    boolean deleted = file.delete();
                    if (deleted) {
                        Toast.makeText(ListAudioActivity.this, getString(R.string.removeed), Toast.LENGTH_SHORT).show();
                        loadmusic();
                    }
                }

            }

            @Override
            public void onCancelClicked() {

            }
        });
        playAudioDialog = new PlayAudioDialog(ListAudioActivity.this, new PlayAudioDialog.OnButtonClicked() {
            @Override
            public void onOkClicked() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Settings.System.canWrite(ListAudioActivity.this)) {
                        // Do stuff here
                        setDefaultRingTone(path);
                    } else {
                        Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }

                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                    }
                }

            }

            @Override
            public void onCancelClicked() {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                    }
                }

            }
        });
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                AnimationTranslate.previewAnimation(ListAudioActivity.this);
            }
        });
        tv_titel = (TextView) findViewById(R.id.tv_title);
        tv_titel.setText(getString(R.string.my_audio));
        playAudioDialog.setCanceledOnTouchOutside(false);
        pro = new ProressDialog(ListAudioActivity.this, new ProressDialog.OnButtonClicked() {
            @Override
            public void onRunClicked() {

            }
        });

        lsview = (ListView) findViewById(R.id.lsaudio);
        adapter = new AdapterAudio(ls, getLayoutInflater());
        lsview.setAdapter(adapter);
        lsview.setOnItemClickListener(this);
        lsview.setOnItemLongClickListener(this);
        loadmusic();

    }

    public void loadmusic() {
        pro.show();
        ls.clear();
        File folder = new File(Constant.PATHAUD);
        for (File f : folder.listFiles()) {
            ls.add(new InfoFile(f.getName(), f.getPath()));
        }
        Collections.reverse(ls);
        pro.dismiss();
        adapter.setLsFile(ls);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();

            }
        }
        try {
            path = adapter.getItem(i).getPathfile();
            mediaPlayer = MediaPlayer.create(getBaseContext(), Uri.parse(path));
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
            playAudioDialog.show();
        } catch (NullPointerException e) {
            Toast.makeText(this, getResources().getString(R.string.audio_erro), Toast.LENGTH_SHORT).show();
        }
    }


    public void setDefaultRingTone(String path) {
        String name = "myringtone";
        if (path.endsWith(".mp3")) {
            name += ".mp3";
        } else {
            name += ".aac";
        }

        File ringtone = new File(Constant.PATHRING, name);
        if (!ringtone.getParentFile().exists()) {
            ringtone.getParentFile().mkdirs();
        }
        try {
            byte[] bArr = new byte[1024];
            FileInputStream createInputStream = new FileInputStream(path);
            FileOutputStream fileOutputStream = new FileOutputStream(ringtone);
            for (int read = createInputStream.read(bArr); read != -1; read = createInputStream.read(bArr)) {
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.close();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("_data", ringtone.getAbsolutePath());
        contentValues.put("title", "nkDroid ringtone");
        contentValues.put("mime_type", "audio/mp3");
        contentValues.put("_size", Long.valueOf(ringtone.length()));
        contentValues.put("artist", Integer.valueOf(R.string.app_name));
        contentValues.put("is_ringtone", Boolean.valueOf(true));
        contentValues.put("is_notification", Boolean.valueOf(false));
        contentValues.put("is_alarm", Boolean.valueOf(false));
        contentValues.put("is_music", Boolean.valueOf(false));
        Uri uri = MediaStore.Audio.Media.getContentUriForPath(ringtone
                .getAbsolutePath());
        getContentResolver().delete(
                uri,
                MediaStore.MediaColumns.DATA + "=\""
                        + ringtone.getAbsolutePath() + "\"", null);
        Uri newUri = getContentResolver().insert(uri, contentValues);

        try {
            RingtoneManager.setActualDefaultRingtoneUri(
                    ListAudioActivity.this, RingtoneManager.TYPE_RINGTONE,
                    newUri);
            Toast.makeText(this, new StringBuilder().append(getString(R.string.ringtonesucces)), Toast.LENGTH_LONG).show();

        } catch (Throwable t) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, "" + requestCode, Toast.LENGTH_SHORT).show();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Ads.f(ListAudioActivity.this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        itemremove = adapter.getItem(i).getPathfile();
        removediaglog.show();
        return true;
    }
}
