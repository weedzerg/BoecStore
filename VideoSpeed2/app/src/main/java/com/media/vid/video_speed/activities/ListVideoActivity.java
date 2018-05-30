package com.media.vid.video_speed.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.media.vid.video_speed.R;
import com.media.vid.video_speed.adapters.AdapterVideo;
import com.media.vid.video_speed.objects.InfoFile;
import com.media.vid.video_speed.utils.Ads;
import com.media.vid.video_speed.utils.AnimationTranslate;
import com.media.vid.video_speed.utils.Constant;
import com.media.vid.video_speed.utils.ShowAds;
import com.media.vid.video_speed.utils.Utils;

import java.io.File;
import java.util.ArrayList;

public class ListVideoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private ArrayList<InfoFile> lsFile;
    private AdapterVideo adapterVideo;
    private ImageView iv_back;
    private TextView tv_title;
    private RelativeLayout rlads;
    private ImageView iv_choice;
    private boolean check;
    //true motion
    //false my video

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_video);
        check=getIntent().getBooleanExtra(Constant.DATAINTENT,true);
        init();
    }

    public void updateIU() {
        if (check) {
            iv_choice.setImageResource(R.drawable.ic_video);
            tv_title.setText(getString(R.string.fs));
        } else {
            iv_choice.setImageResource(R.drawable.ic_speed);
            tv_title.setText(getString(R.string.my_video));
        }
    }

    public void init() {
        rlads = (RelativeLayout) findViewById(R.id.layout_ads);
        ShowAds.showAdsNative(rlads, ListVideoActivity.this);
        lsFile = new ArrayList<>();
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_choice = (ImageView) findViewById(R.id.iv_choice);
        iv_choice.setOnClickListener(on_click);
        iv_back.setOnClickListener(on_click);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(getString(R.string.my_video));
        listView = (ListView) findViewById(R.id.lsview);
        adapterVideo = new AdapterVideo(lsFile, getLayoutInflater());
        listView.setAdapter(adapterVideo);
        listView.setOnItemClickListener(this);
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(Constant.PATHVIDEO))));
        updateIU();
        new AsynLoadVideo().execute();

    }

    private View.OnClickListener on_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_choice: {
                    if (check) {
                        check = false;
                        updateIU();
                    } else {
                        check = true;
                        updateIU();

                    }
                    new AsynLoadVideo().execute();

                    break;
                }
                case R.id.iv_back: {
                    finish();
                    AnimationTranslate.previewAnimation(ListVideoActivity.this);
                    break;
                }
            }
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (check) {
            Intent intent = new Intent(ListVideoActivity.this, SlideVideoActivity.class);
            intent.putExtra(Constant.DATAINTENT, adapterVideo.getItem(i).getPathfile());
            startActivity(intent);
            AnimationTranslate.nextAnimation(ListVideoActivity.this);
        } else {
            Intent intent = new Intent(ListVideoActivity.this, TrimVideoActivity.class);
            intent.putExtra(Constant.DATAINTENT, adapterVideo.getItem(i).getPathfile());
            startActivity(intent);
            AnimationTranslate.nextAnimation(ListVideoActivity.this);
        }


    }

    class AsynLoadVideo extends AsyncTask<Void, Void, ArrayList<InfoFile>> {

        @Override
        protected ArrayList<InfoFile> doInBackground(Void... voids) {
            lsFile.clear();
            if (check) {
                lsFile = Utils.getFSVideo(getBaseContext());
            } else {
                lsFile = Utils.getAllFileVideo(getBaseContext());
            }
            return lsFile;
        }

        @Override
        protected void onPostExecute(ArrayList<InfoFile> infoFiles) {
            super.onPostExecute(infoFiles);
            adapterVideo.setLs(lsFile);
            adapterVideo.notifyDataSetChanged();
            this.cancel(true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AnimationTranslate.previewAnimation(ListVideoActivity.this);
        Ads.f(ListVideoActivity.this);
    }
}
