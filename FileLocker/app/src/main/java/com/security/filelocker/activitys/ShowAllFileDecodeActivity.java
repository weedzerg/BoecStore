package com.security.filelocker.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.security.filelocker.R;
import com.security.filelocker.adapters.AdapterAudio;
import com.security.filelocker.adapters.AdapterDocument;
import com.security.filelocker.adapters.AdapterImage;
import com.security.filelocker.adapters.AdapterVideo;
import com.security.filelocker.dialog.EncodeDialog;
import com.security.filelocker.interfaces.UploadDecodeFile;
import com.security.filelocker.objects.InfoFile;
import com.security.filelocker.utils.AnimationTranslate;
import com.security.filelocker.utils.AsynTaskDecode;
import com.security.filelocker.utils.AsynTaskDecodeShow;
import com.security.filelocker.utils.Cache;
import com.security.filelocker.utils.Contants;
import com.security.filelocker.utils.ProcessJson;
import com.security.filelocker.utils.ShowAds;
import com.security.filelocker.utils.Utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ShowAllFileDecodeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, UploadDecodeFile {
    private ListView listView;
    private int style;
    private AdapterVideo adapterVideo;
    private AdapterAudio adapterAudio;
    private AdapterImage adapterImage;
    private AdapterDocument adapterDocument;
    private Toolbar toolbar;
    private TextView tv_pro;
    private LinearLayout ln_pro;
    private ArrayList<InfoFile> lsFile, lsCache, lsSecurity, lsDecode;
    private Cache cache;
    private String value;
    private AsynTaskDecode asynTaskDecode;
    private AsynTaskDecodeShow asynTaskDecodeShow;
    private UploadDecodeFile uploadDecodeFile;
    private EncodeDialog decodeDiaglog;
    private RelativeLayout adsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_file);
        style = getIntent().getIntExtra(Contants.VALUE, 0);
        cache = new Cache(getBaseContext());
        uploadDecodeFile = this;
        init();
        asynTaskDecodeShow.execute(lsFile);

    }

    public void getListSecurity() {
        try {
            String s = cache.getLink(value);
            lsSecurity = ProcessJson.JsontoArray(s);
            lsFile = ProcessJson.JsontoArray(s);
            Log.d("JSON " + value, lsSecurity.toString());
        } catch (Exception e) {
            lsSecurity = new ArrayList<>();
            e.printStackTrace();
        }
    }

    public void init() {
        lsSecurity = new ArrayList<>();
        lsFile = new ArrayList<>();
        lsDecode = new ArrayList<>();
        lsCache = new ArrayList<>();
        adsView = (RelativeLayout) findViewById(R.id.layout_ads);
        tv_pro = (TextView) findViewById(R.id.tv_process);
        ln_pro = (LinearLayout) findViewById(R.id.ln_process);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.lsview);
        updateToolbar(style);
        getListSecurity();
        updateListview(style, lsFile);
        ln_pro.setVisibility(View.VISIBLE);
        asynTaskDecodeShow = new AsynTaskDecodeShow(ln_pro, Contants.KEY, uploadDecodeFile);
        listView.setOnItemClickListener(this);
        decodeDiaglog = new EncodeDialog(ShowAllFileDecodeActivity.this, new EncodeDialog.OnButtonClicked() {
            @Override
            public void onOKClicked() {
                ln_pro.setVisibility(View.VISIBLE);
                asynTaskDecode = new AsynTaskDecode(cache, Contants.KEY, value,
                        ShowAllFileDecodeActivity.this, uploadDecodeFile);
                asynTaskDecode.setProgressBar(ln_pro);
                asynTaskDecode.setTv_procsss(tv_pro);
                asynTaskDecode.execute(lsDecode);
            }

            @Override
            public void onCancelClicked() {

            }
        }, false);
        ShowAds.showAdsNative(adsView, ShowAllFileDecodeActivity.this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_decode, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_unlock: {
                if (lsSecurity.size() != 0) {
                    decodeDiaglog.show();
                } else {
                    Toast.makeText(ShowAllFileDecodeActivity.this, getString(R.string.no_choice), Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateToolbar(int style) {
        String s = "";
        switch (style) {
            case 0:
                s = getString(R.string.images);
                value = Contants.IMAGE;
                break;
            case 1:
                s = getString(R.string.musics);
                value = Contants.AUDIO;
                break;
            case 2:
                s = getString(R.string.videos);
                value = Contants.VIDEO;
                break;
            case 3:
                s = getString(R.string.documents);
                value = Contants.DOCUMENT;
                break;
        }
        toolbar.setTitle(s);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationTranslate.previewAnimation(ShowAllFileDecodeActivity.this);
                finish();
            }
        });
    }

    public void updateListview(int style, ArrayList<InfoFile> ls) {
        lsCache = ls;
        switch (style) {
            case 0: {
                adapterImage = new AdapterImage(ls, getLayoutInflater());
                listView.setAdapter(adapterImage);
                adapterImage.notifyDataSetChanged();
                break;
            }
            case 1: {
                adapterAudio = new AdapterAudio(ls, getLayoutInflater());
                listView.setAdapter(adapterAudio);
                adapterAudio.notifyDataSetChanged();
                break;
            }
            case 2: {
                adapterVideo = new AdapterVideo(ls, getLayoutInflater());
                listView.setAdapter(adapterVideo);
                adapterVideo.notifyDataSetChanged();
                break;
            }
            case 3: {
                adapterDocument = new AdapterDocument(ls, getLayoutInflater());
                listView.setAdapter(adapterDocument);
                adapterDocument.notifyDataSetChanged();
                break;
            }

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        InfoFile infoFile = lsFile.get(i);
        int pos = Utils.searchInfoFile(lsSecurity, infoFile.getPathfileCache());
        if (infoFile.isCheck()) {
            if (pos != -1) {
                lsFile.get(i).setCheck(false);
                lsDecode.remove(lsSecurity.get(pos));
            }
        } else {
            lsFile.get(i).setCheck(true);
            lsDecode.add(lsSecurity.get(pos));
        }
        switch (style) {
            case 0:
                adapterImage.notifyDataSetChanged();
                break;
            case 1:
                adapterAudio.notifyDataSetChanged();
                break;
            case 2:
                adapterVideo.notifyDataSetChanged();
                break;
            case 3:
                adapterDocument.notifyDataSetChanged();
                break;
        }
        Log.d("JSON", lsSecurity.toString());
    }

    @Override
    public void uploadFile(ArrayList<InfoFile> lsFile) {

        updateListview(style, lsFile);
    }

    @Override
    public void uploadListview(ArrayList<InfoFile> lsFile) {
        for (InfoFile s : lsFile) {
            int pos = Utils.searchInfoFileEN(lsCache, s.getPathfile());
            if (pos != -1) {
                lsCache.remove(pos);
            }
        }
        updateListview(style, lsCache);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        File dir = new File(Contants.PATH);
        try {
            FileUtils.deleteDirectory(dir);

        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File((Contants.PATH));
        if (!file.exists()) {
            file.mkdirs();
            Log.d("DEBUG", "success EX1");
        }
    }
}