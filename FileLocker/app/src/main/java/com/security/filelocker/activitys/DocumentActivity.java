package com.security.filelocker.activitys;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.security.filelocker.R;
import com.security.filelocker.adapters.AdapterDocumentExp;
import com.security.filelocker.interfaces.UploadDecodeFile;
import com.security.filelocker.objects.InfoFile;
import com.security.filelocker.utils.AsynTaskEncode;
import com.security.filelocker.utils.Cache;
import com.security.filelocker.utils.Contants;
import com.security.filelocker.utils.FileExternalStorage;
import com.security.filelocker.utils.ProcessJson;
import com.security.filelocker.utils.ShowAds;
import com.security.filelocker.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;

public class DocumentActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener, UploadDecodeFile {
    private ExpandableListView expandableListView;
    private AdapterDocumentExp adapterDocument;
    private HashMap<String, ArrayList<InfoFile>> hashMap = new HashMap<>();
    private ArrayList<String> lshead = new ArrayList<>();
    private ArrayList<InfoFile> lsSecurity;
    private Cache cache;
    private Dialog mdialog;
    private TextView tv_pro;
    private LinearLayout ln_pro;
    private Toolbar toolbar;
    private UploadDecodeFile uploadDecodeFile;
    private RelativeLayout adsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        uploadDecodeFile = this;
        init();
    }

    public void updateToolbar() {
        toolbar.setTitle(getString(R.string.documents));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getListSecurity() {
        try {
            String s = cache.getLink(Contants.DOCUMENT);
            lsSecurity = ProcessJson.JsontoArray(s);
        } catch (Exception e) {
            lsSecurity = new ArrayList<>();
            e.printStackTrace();
        }
    }

    public void init() {
        cache = new Cache(getBaseContext());
        getListSecurity();
        expandableListView = (ExpandableListView) findViewById(R.id.ex_listview);
        adsView = (RelativeLayout) findViewById(R.id.layout_ads);
        ln_pro = (LinearLayout) findViewById(R.id.ln_process);
        tv_pro = (TextView) findViewById(R.id.tv_process);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        updateToolbar();
        adapterDocument = new AdapterDocumentExp(lshead, hashMap, getLayoutInflater());
        expandableListView.setAdapter(adapterDocument);
        expandableListView.setOnChildClickListener(this);
        ln_pro.setVisibility(View.VISIBLE);
        new loadData().execute();
        ShowAds.showAdsNative(adsView, DocumentActivity.this);

    }

    public void getListHead() {
        lshead = new ArrayList<>();
        for (String s : hashMap.keySet()) {
            lshead.add(s);
        }
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
                createDiaglog();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void createDiaglog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(DocumentActivity.this);
        View v = getLayoutInflater().inflate(R.layout.dialog_encodefile, null);
        TextView btsave, btcancel;
        btsave = (TextView) v.findViewById(R.id.tv_ok);
        btcancel = (TextView) v.findViewById(R.id.tv_cancel);
        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lsSecurity.size() != 0) {
                    ln_pro.setVisibility(View.INVISIBLE);
                    AsynTaskEncode asynTaskSecurity = new AsynTaskEncode(cache,
                            Contants.KEY, Contants.DOCUMENT, DocumentActivity.this, uploadDecodeFile);
                    asynTaskSecurity.setTv_process(tv_pro);
                    asynTaskSecurity.setProgressBar(ln_pro);
                    asynTaskSecurity.execute(lsSecurity);
                    mdialog.dismiss();
                } else {
                    Toast.makeText(DocumentActivity.this, getString(R.string.no_choice), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdialog.dismiss();
            }
        });
        dialog.setView(v);
        mdialog = dialog.create();
        mdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mdialog.show();
    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view,
                                int groupPosition, int childPosition, long l) {
        InfoFile infoFile = adapterDocument.getChild(groupPosition, childPosition);
        int pos = Utils.searchInfoFile(lsSecurity, infoFile.getPathfile());
        if (infoFile.isCheck()) {
            hashMap.get(lshead.get(groupPosition)).get(childPosition).setCheck(false);
            if (pos != -1) {
                lsSecurity.remove(pos);
            }
        } else {
            hashMap.get(lshead.get(groupPosition)).get(childPosition).setCheck(true);
            if (pos == -1) {
                lsSecurity.add(infoFile);
            }
        }
        adapterDocument.notifyDataSetChanged();
        return false;
    }

    @Override
    public void uploadFile(ArrayList<InfoFile> ls) {
        for (InfoFile s : ls) {
            int pos = Utils.searchInfoFile(ls, s.getPathfile());
            switch (s.getMineType()) {
                case "application/vnd.openxmlformats-officedocument.wordprocessingml.document": {
                    pos = Utils.searchInfoFile(hashMap.get("Word"), s.getPathfile());
                    if (pos != -1) {
                        hashMap.get("Word").remove(pos);
                    }
                    break;
                }
                case "application/pdf": {
                    pos = Utils.searchInfoFile(hashMap.get("PDF"), s.getPathfile());
                    if (pos != -1) {
                        hashMap.get("PDF").remove(pos);
                    }
                    break;
                }
                case "application/vnd.ms-excel": {
                    pos = Utils.searchInfoFile(hashMap.get("Excel"), s.getPathfile());
                    if (pos != -1) {
                        hashMap.get("Excel").remove(pos);
                    }
                    break;
                }
            }
        }
        adapterDocument.notifyDataSetChanged();

    }

    @Override
    public void uploadListview(ArrayList<InfoFile> lsFile) {

    }

    public class loadData extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            hashMap = FileExternalStorage.getAllFileDocument(getBaseContext());
            getListHead();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            ln_pro.setVisibility(View.GONE);
            adapterDocument = new AdapterDocumentExp(lshead, hashMap, getLayoutInflater());
            adapterDocument.notifyDataSetChanged();
            expandableListView.setAdapter(adapterDocument);
            this.cancel(true);

        }

    }
    @Override
    protected void onRestart() {
        super.onRestart();
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
