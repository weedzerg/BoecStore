package com.funnystudio.whistleaction.activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.funnystudio.whistleaction.R;
import com.funnystudio.whistleaction.SongMusic;
import com.funnystudio.whistleaction.adapter.AdapterListMp3;
import com.funnystudio.whistleaction.model.ObjectMusic;

import java.util.ArrayList;

public class SoundMP3 extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lsmp3;
    private AdapterListMp3 adapter;
    private TextView btok;
    private ObjectMusic obMusic;
    private Toolbar toolbar;
    private ArrayList<ObjectMusic> lsfilemp3;
    private MediaPlayer mediaPlayer;
    private LinearLayout lnpr;
private SongMusic songMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_mp3);
        btok = (TextView) findViewById(R.id.ok);
        lsmp3 = (ListView) findViewById(R.id.lsmp3);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fomattoobar();
        lnpr = (LinearLayout) findViewById(R.id.lnpr);
        lsfilemp3 = new ArrayList<>();
        mediaPlayer = new MediaPlayer();
        adapter = new AdapterListMp3(lsfilemp3, getLayoutInflater());
        lsmp3.setAdapter(adapter);
        lsmp3.setOnItemClickListener(this);
        btok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (obMusic != null) {
                    mediaPlayer.stop();
                    Intent intent = new Intent();
                    obMusic.setCheckringDefault(true);
                    intent.putExtra("filemusic", obMusic);
                    setResult(500, intent);
                    finish();
                }
            }
        });
        songMusic=new SongMusic();
        new loadMusic().execute();
    }

    public void playMusic() {
        mediaPlayer = MediaPlayer.create(this, Uri.parse(obMusic.getFilename()));
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void fomattoobar() {
        toolbar.setTitle(R.string.ringtone);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        finish();
        super.onBackPressed();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        for (int j = 0; j < adapter.getCount(); j++) {
            adapter.getItem(j).setCheck(false);
        }
        adapter.getItem(i).setCheck(true);
        obMusic = adapter.getItem(i);
        adapter.notifyDataSetChanged();
        playMusic();
    }

    public class loadMusic extends AsyncTask<Void, Void, ArrayList<ObjectMusic>> {
        @Override
        protected ArrayList<ObjectMusic> doInBackground(Void... voids) {
            return songMusic.getPlayList();
        }

        @Override
        protected void onPostExecute(ArrayList<ObjectMusic> objectMusics) {
            adapter.setLsmp3(objectMusics);
            adapter.notifyDataSetChanged();
            lnpr.setVisibility(View.INVISIBLE);
            this.cancel(true);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100: {
                Uri uri = data.getData();
            }
        }
    }
}