package com.security.filelocker.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.security.filelocker.R;
import com.security.filelocker.adapters.AdapterAlbum;
import com.security.filelocker.interfaces.UploadDecodeFile;
import com.security.filelocker.objects.InfoFile;
import com.security.filelocker.utils.AnimationTranslate;
import com.security.filelocker.utils.AsynTaskLoadData;
import com.security.filelocker.utils.Contants;
import com.security.filelocker.utils.ShowAds;

import java.util.ArrayList;
import java.util.HashMap;

public class AlbumActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, UploadDecodeFile {
    private Toolbar toolbar;
    private ListView lsview;
    private AdapterAlbum adapterAlbum;
    private ArrayList<InfoFile> lsAlbum;
    private HashMap<String, ArrayList<InfoFile>> hashMapAlbum;
    private HashMap<String, String> hashMapAlbum1;
    private int style;
    private RelativeLayout adsView;
    private LinearLayout lnpro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);
        init();
    }

    public void init() {
        adsView = (RelativeLayout) findViewById(R.id.layout_ads);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lnpro = (LinearLayout) findViewById(R.id.ln_process);
        lsview = (ListView) findViewById(R.id.lsview_video);
        hashMapAlbum = new HashMap<>();
        hashMapAlbum1 = new HashMap<>();
        lsAlbum = new ArrayList<>();
        style = getIntent().getIntExtra(Contants.VALUE, 0);
        getData(style);
        adapterAlbum = new AdapterAlbum(lsAlbum, getLayoutInflater(), style);
        lsview.setAdapter(adapterAlbum);
        lsview.setOnItemClickListener(this);
        updateToolbar();
        ShowAds.showAdsNative(adsView,AlbumActivity.this);

    }

    public void getData(int style) {
        AsynTaskLoadData asynTaskLoadData = new AsynTaskLoadData(style, AlbumActivity.this, this);
        asynTaskLoadData.setProgressBar(lnpro);
        lnpro.setVisibility(View.VISIBLE);
        asynTaskLoadData.execute();
    }

    public void updateToolbar() {
        String s = "";
        switch (style) {
            case 0:
                s = getString(R.string.alimages);
                break;
            case 1:
                s = getString(R.string.almusics);
                break;
            case 2:
                s = getString(R.string.alvideos);
                break;
        }
        toolbar.setTitle(s);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationTranslate.previewAnimation(AlbumActivity.this);
                finish();
            }
        });
    }

    public void initFolder(ArrayList<InfoFile> lsFile) {
        for (int i = 0; i < lsFile.size(); i++) {
            InfoFile infoFile = lsFile.get(i);
            if (hashMapAlbum.containsKey(infoFile.getNameAlbum())) {
                hashMapAlbum.get(infoFile.getNameAlbum()).add(infoFile);
            } else {
                ArrayList<InfoFile> lsin = new ArrayList<>();
                lsin.add(infoFile);
                hashMapAlbum.put(infoFile.getNameAlbum(), lsin);
                if (style == 1) {

                    hashMapAlbum1.put(infoFile.getNameAlbum(), infoFile.getIdAlbum());
                } else {
                    hashMapAlbum1.put(infoFile.getNameAlbum(), infoFile.getPathfile());
                }
            }
        }
        initAlbum();
    }

    private void initAlbum() {
        lsAlbum.clear();
        for (String s1 : hashMapAlbum.keySet()) {
            if (style == 1) {
                InfoFile s = new InfoFile(s1, s1, s1, hashMapAlbum.get(s1).size(), "");
                s.setIdAlbum(hashMapAlbum1.get(s1));
                lsAlbum.add(s);
            } else {
                lsAlbum.add(new InfoFile(s1, s1, hashMapAlbum1.get(s1), hashMapAlbum.get(s1).size(), ""));
            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, ShowAllFileActivity.class);
        intent.putExtra(Contants.FILE_INTENT, hashMapAlbum.get(adapterAlbum.getItem(i).getNameAlbum()));
        intent.putExtra(Contants.VALUE, style);
        startActivity(intent);
        AnimationTranslate.nextAnimation(AlbumActivity.this);

    }

    @Override
    public void uploadFile(ArrayList<InfoFile> lsFile) {
        initFolder(lsFile);
        adapterAlbum.notifyDataSetChanged();
    }

    @Override
    public void uploadListview(ArrayList<InfoFile> lsFile) {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        lsAlbum.clear();
        hashMapAlbum.clear();
        hashMapAlbum1.clear();
        AsynTaskLoadData asynTaskLoadData = new AsynTaskLoadData(style, AlbumActivity.this, this);
        asynTaskLoadData.setProgressBar(lnpro);
        lnpro.setVisibility(View.VISIBLE);
        asynTaskLoadData.execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
