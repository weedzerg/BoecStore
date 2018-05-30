package com.media.studio.reversevideo.activitys;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.media.studio.reversevideo.R;
import com.media.studio.reversevideo.adapters.AdapterVideo;
import com.media.studio.reversevideo.objects.InfoFile;
import com.media.studio.reversevideo.utils.AnimationTranslate;
import com.media.studio.reversevideo.utils.Constant;
import com.media.studio.reversevideo.utils.ShowAds;
import com.media.studio.reversevideo.utils.Utils;

import java.util.ArrayList;

public class ListVideoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private ArrayList<InfoFile> lsFile;
    private AdapterVideo adapterVideo;
    private LinearLayout ln_pro;
    private ImageView iv_back;
    private TextView tv_title;
    private RelativeLayout rlads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_video);
        init();
    }

    public void init() {
        rlads = (RelativeLayout) findViewById(R.id.layout_ads);
        ShowAds.showAdsNative(rlads, ListVideoActivity.this);
        lsFile = new ArrayList<>();
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                AnimationTranslate.previewAnimation(ListVideoActivity.this);
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(getString(R.string.my_video));
        ln_pro = (LinearLayout) findViewById(R.id.ln_pro);
        listView = (ListView) findViewById(R.id.lsview);
        adapterVideo = new AdapterVideo(lsFile, getLayoutInflater());
        listView.setAdapter(adapterVideo);
        listView.setOnItemClickListener(this);
        ln_pro.setVisibility(View.VISIBLE);
        new AsynLoadVideo().execute();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(ListVideoActivity.this, EditVideoActivity.class);
        intent.putExtra(Constant.DATAINTENT, adapterVideo.getItem(i).getPathfile());
        startActivity(intent);
        AnimationTranslate.nextAnimation(ListVideoActivity.this);

    }

    class AsynLoadVideo extends AsyncTask<Void, Void, ArrayList<InfoFile>> {

        @Override
        protected ArrayList<InfoFile> doInBackground(Void... voids) {
            lsFile = Utils.getAllFileVideo(getBaseContext());
            return lsFile;
        }

        @Override
        protected void onPostExecute(ArrayList<InfoFile> infoFiles) {
            super.onPostExecute(infoFiles);
            ln_pro.setVisibility(View.GONE);
            adapterVideo.setLs(lsFile);
            adapterVideo.notifyDataSetChanged();
        }
    }
}
