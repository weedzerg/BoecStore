package com.media.convert.videotomp3aacc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.media.convert.videotomp3aacc.R;
import com.media.convert.videotomp3aacc.adapters.AdapterVideo;
import com.media.convert.videotomp3aacc.dialog.ProressDialog;
import com.media.convert.videotomp3aacc.objects.InfoFile;
import com.media.convert.videotomp3aacc.utils.Ads;
import com.media.convert.videotomp3aacc.utils.AnimationTranslate;
import com.media.convert.videotomp3aacc.utils.Constant;
import com.media.convert.videotomp3aacc.utils.FileExternalStorage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private AdapterVideo adapterVideo;
    private ArrayList<InfoFile> lsvideo;
    private RelativeLayout adsView;
    private ImageView iv_back;
    private TextView tv_title;
    private ProressDialog pro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        pro = new ProressDialog(MainActivity.this, new ProressDialog.OnButtonClicked() {
            @Override
            public void onRunClicked() {

            }
        });
        pro.show();
        lsvideo = new ArrayList<>();
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                AnimationTranslate.previewAnimation(MainActivity.this);
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(getString(R.string.my_video));
        adsView = (RelativeLayout) findViewById(R.id.layout_ads);
        listView = (ListView) findViewById(R.id.lsview_video);
        listView.setOnItemClickListener(this);
        adapterVideo = new AdapterVideo(lsvideo, getLayoutInflater());
        listView.setAdapter(adapterVideo);
        lsvideo = FileExternalStorage.getAllFileVideo(getBaseContext());
        pro.dismiss();
        adapterVideo.setLsFile(lsvideo);
        adapterVideo.notifyDataSetChanged();
        Ads.b(this, adsView, new Ads.OnAdsListener() {
            @Override
            public void onError() {
                adsView.setVisibility(View.GONE);
                Log.d("DEBUG","error");
            }

            @Override
            public void onAdLoaded() {
                adsView.setVisibility(View.VISIBLE);
                Log.d("DEBUG","loaded");

            }

            @Override
            public void onAdOpened() {
                adsView.setVisibility(View.VISIBLE);
                Log.d("DEBUG","opened");

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(MainActivity.this, ConvertActivity.class);
        intent.putExtra(Constant.INTENTDATA, adapterVideo.getItem(i).getPathfile());
        intent.putExtra(Constant.INTENTDATANAME, adapterVideo.getItem(i).getNamefile());
        startActivity(intent);
        AnimationTranslate.nextAnimation(MainActivity.this);
    }


}
