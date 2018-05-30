package com.security.filelocker.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.security.filelocker.adapters.AdapterImage;
import com.security.filelocker.adapters.AdapterVideo;
import com.security.filelocker.dialog.EncodeDialog;
import com.security.filelocker.interfaces.UploadDecodeFile;
import com.security.filelocker.objects.InfoFile;
import com.security.filelocker.utils.AnimationTranslate;
import com.security.filelocker.utils.AsynTaskEncode;
import com.security.filelocker.utils.Cache;
import com.security.filelocker.utils.Contants;
import com.security.filelocker.utils.ShowAds;
import com.security.filelocker.utils.Utils;

import java.util.ArrayList;

public class ShowAllFileActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, UploadDecodeFile {
    private ListView listView;
    private ArrayList<InfoFile> lsFile;
    private int style;
    private AdapterVideo adapterVideo;
    private AdapterAudio adapterAudio;
    private AdapterImage adapterImage;
    private Toolbar toolbar;
    private LinearLayout ln_pro;
    private TextView tv_pro;
    private ArrayList<InfoFile> lsEncode;
    private Cache cache;
    private String value;
    private UploadDecodeFile uploadDecodeFile;
    private EncodeDialog encodeDialog;
    private RelativeLayout adsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_file);
        lsFile = (ArrayList<InfoFile>) getIntent().getSerializableExtra(Contants.FILE_INTENT);
        style = getIntent().getIntExtra(Contants.VALUE, 0);
        cache = new Cache(getBaseContext());
        uploadDecodeFile = this;
        init();
    }


    public void init() {
        lsEncode = new ArrayList<>();
        adsView= (RelativeLayout) findViewById(R.id.layout_ads);
        ln_pro = (LinearLayout) findViewById(R.id.ln_process);
        tv_pro = (TextView) findViewById(R.id.tv_process);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.lsview);
        updateToolbar(style);
        updateListview(style);
        listView.setOnItemClickListener(this);
        encodeDialog = new EncodeDialog(ShowAllFileActivity.this, new EncodeDialog.OnButtonClicked() {
            @Override
            public void onOKClicked() {
                ln_pro.setVisibility(View.VISIBLE);
                AsynTaskEncode asynTaskSecurity = new AsynTaskEncode(cache,
                        Contants.KEY, value, ShowAllFileActivity.this, uploadDecodeFile);
                asynTaskSecurity.setProgressBar(ln_pro);
                asynTaskSecurity.setTv_process(tv_pro);
                asynTaskSecurity.execute(lsEncode);
            }

            @Override
            public void onCancelClicked() {

            }
        }, true);
        ShowAds.showAdsNative(adsView,ShowAllFileActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_lock: {
                if (lsEncode.size() != 0) {
                    encodeDialog.show();
                } else {
                    Toast.makeText(ShowAllFileActivity.this, getString(R.string.no_choice), Toast.LENGTH_SHORT).show();
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
        }
        toolbar.setTitle(s);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationTranslate.previewAnimation(ShowAllFileActivity.this);

                finish();
            }
        });
    }

    public void updateListview(int style) {
        switch (style) {
            case 0: {
                adapterImage = new AdapterImage(lsFile, getLayoutInflater());
                listView.setAdapter(adapterImage);
                adapterImage.notifyDataSetChanged();
                break;
            }
            case 1: {
                adapterAudio = new AdapterAudio(lsFile, getLayoutInflater());
                listView.setAdapter(adapterAudio);
                adapterAudio.notifyDataSetChanged();
                break;
            }
            case 2: {
                adapterVideo = new AdapterVideo(lsFile, getLayoutInflater());
                listView.setAdapter(adapterVideo);
                adapterVideo.notifyDataSetChanged();
                break;
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        InfoFile infoFile = lsFile.get(i);
        int pos = Utils.searchInfoFile(lsEncode, infoFile.getPathfile());
        if (infoFile.isCheck()) {
            lsFile.get(i).setCheck(false);
            if (pos != -1) {
                lsEncode.remove(pos);
            }
        } else {
            lsFile.get(i).setCheck(true);
            if (!infoFile.isStatus()) {
                Toast.makeText(this, getString(R.string.size_5mb), Toast.LENGTH_SHORT).show();
            }
            if (pos == -1) {
                lsEncode.add(infoFile);
            }
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
        }
    }

    @Override
    public void uploadFile(ArrayList<InfoFile> ls) {
        for (InfoFile s : ls) {
            int pos = Utils.searchInfoFile(lsFile, s.getPathfile());
            if (pos != -1) {
                lsFile.remove(pos);
            }
        }
        updateListview(style);
    }

    @Override
    public void uploadListview(ArrayList<InfoFile> lsFile) {

    }

}
