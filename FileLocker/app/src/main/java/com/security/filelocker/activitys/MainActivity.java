package com.security.filelocker.activitys;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.security.filelocker.R;
import com.security.filelocker.adapters.AdapterViewpaper;
import com.security.filelocker.dialog.RateAppDialog;
import com.security.filelocker.utils.Ads;
import com.security.filelocker.utils.Cache;
import com.security.filelocker.utils.Contants;
import com.security.filelocker.utils.ShowAds;
import com.security.filelocker.utils.Utils;

import java.io.File;

import me.relex.circleindicator.CircleIndicator;

import static com.security.filelocker.R.id.indicator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ln_image, ln_audio, ln_video, ln_document, ln_permiss;
    private LinearLayout ln_security;
    private RelativeLayout layoutAds;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private AdapterViewpaper adapterViewpaper;
    private boolean check;
    private static final String[] PERMISSION =
            {Manifest.permission.READ_EXTERNAL_STORAGE
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private Cache cache;
    private RateAppDialog rateAppDialog;
    private Toolbar toolbar;
    private TextView tv_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cache = new Cache(getBaseContext());
        turnPermiss();

    }

    public void getUser() {
        String s = cache.getLink(Contants.CHECK_SD);
        try {
            check = Boolean.parseBoolean(s);
        } catch (Exception e) {
            e.printStackTrace();
            check = false;
        }
        if (check) {
            ln_permiss.setVisibility(View.GONE);
            Contants.uriSD = Uri.parse(cache.getLink(Contants.URISD_CACHE));
            Contants.ID_DOCUMENT = cache.getLink(Contants.ID_DOCUMENT_CACHE);
            Contants.PATH_DOCUMENT = cache.getLink(Contants.PATH_DOCUMENT_CACHE);
        } else {
            ln_permiss.setVisibility(View.VISIBLE);
            showGuidePermission();

        }
    }

    public void startActionON_OFF() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        startActivityForResult(intent, 100);

    }

    public void init() {
        creatFolder();
        tv_next = (TextView) findViewById(R.id.tv_next);
        viewPager = (ViewPager) findViewById(R.id.viewPaper);
        circleIndicator = (CircleIndicator) findViewById(indicator);
        layoutAds = (RelativeLayout) findViewById(R.id.layout_ads);
        ln_permiss = (LinearLayout) findViewById(R.id.ln_permiss);
        ln_image = (LinearLayout) findViewById(R.id.ln_image);
        ln_audio = (LinearLayout) findViewById(R.id.ln_audio);
        ln_video = (LinearLayout) findViewById(R.id.ln_video);
        ln_document = (LinearLayout) findViewById(R.id.ln_document);
        ln_security = (LinearLayout) findViewById(R.id.ln_security);
        ln_image.setOnClickListener(this);
        ln_audio.setOnClickListener(this);
        ln_video.setOnClickListener(this);
        ln_document.setOnClickListener(this);
        ln_security.setOnClickListener(this);
        rateAppDialog = new RateAppDialog(this, new RateAppDialog.OnButtonClicked() {
            @Override
            public void onRateClicked() {
                Utils.rateApp(MainActivity.this);
            }

            @Override
            public void onCancelClicked() {
                finish();
            }
        });
//        ZAndroidSDK.init(this);
        Ads.f(this);
        ShowAds.showAdsNative(layoutAds, MainActivity.this);
        showGuidePermission();
    }

    public void showGuidePermission() {
        adapterViewpaper = new AdapterViewpaper(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewpaper);
        circleIndicator.setViewPager(viewPager);
        adapterViewpaper.registerDataSetObserver(circleIndicator.getDataSetObserver());
        tv_next.setOnClickListener(this);
    }

    public void updateToolbar() {
        init();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ln_image: {
                Intent intent = new Intent(this, AlbumActivity.class);
                intent.putExtra(Contants.VALUE, 0);
                startActivity(intent);
                break;
            }
            case R.id.ln_audio: {
                Intent intent = new Intent(this, AlbumActivity.class);
                intent.putExtra(Contants.VALUE, 1);
                startActivity(intent);
                break;
            }
            case R.id.ln_video: {
                Intent intent = new Intent(this, AlbumActivity.class);
                intent.putExtra(Contants.VALUE, 2);
                startActivity(intent);
                break;
            }
            case R.id.ln_document: {
                Intent intent = new Intent(this, DocumentActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.ln_security: {
                Intent intent = new Intent(this, DecodeFileActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.tv_next: {
                startActionON_OFF();
                break;
            }
        }
    }


    public void creatFolder() {
        File file = new File(Contants.PATH);
        if (!file.exists()) {
            file.mkdirs();
            Log.d("DEBUG", "file create");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                ln_permiss.setVisibility(View.GONE);
                mCurrentDirectoryUri = data.getData();
                updateDirectoryEntries(data.getData());
            } else {
                Toast.makeText(this, getString(R.string.per), Toast.LENGTH_SHORT).show();
            }
        }
    }

    Uri mCurrentDirectoryUri;

    public void updateDirectoryEntries(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        Uri docUri = null;
        Contants.uriSD = uri;
        cache.putLink(Contants.URISD_CACHE, uri.toString());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            docUri = DocumentsContract.buildDocumentUriUsingTree(uri,
                    DocumentsContract.getTreeDocumentId(uri));
            Log.d("URI", docUri.toString());
            Uri childrenUri = DocumentsContract.buildChildDocumentsUriUsingTree(uri,
                    DocumentsContract.getTreeDocumentId(uri));
            Log.d("URI", childrenUri.toString());
            Cursor docCursor = contentResolver.query(docUri, new String[]{
                    DocumentsContract.Document.COLUMN_DISPLAY_NAME,
                    DocumentsContract.Document.COLUMN_MIME_TYPE,
                    DocumentsContract.Document.COLUMN_DOCUMENT_ID}, null, null, null);
            try {
                while (docCursor.moveToNext()) {
                    Log.d("DEBUG", "found doc =" + docCursor.getString(0) + ", mime=" + docCursor
                            .getString(1) + ", id=" + docCursor.getString(2));
                    mCurrentDirectoryUri = uri;
                    Contants.PATH_DOCUMENT = "/storage/" + docCursor.getString(2).split(":")[0] + "/";
                    Contants.ID_DOCUMENT = docCursor.getString(2);
                    cache.putLink(Contants.PATH_DOCUMENT_CACHE, Contants.PATH_DOCUMENT);
                    cache.putLink(Contants.ID_DOCUMENT_CACHE, Contants.ID_DOCUMENT);
                    cache.putLink(Contants.CHECK_SD, true + "");

                }
            } finally {
                closeQuietly(docCursor);
            }

            Cursor childCursor = contentResolver.query(childrenUri, new String[]{
                    DocumentsContract.Document.COLUMN_DISPLAY_NAME,
                    DocumentsContract.Document.COLUMN_MIME_TYPE,
                    DocumentsContract.Document.COLUMN_DOCUMENT_ID}, null, null, null);
            while (childCursor.moveToNext()) {
                Log.d("DEBUG", "found doc =" + childCursor.getString(0) + ", mime=" + childCursor
                        .getString(1)
                        + ", id=" + childCursor
                        .getString(2));
            }
            try {
            } finally {
                closeQuietly(childCursor);
            }
        }
    }

    public void closeQuietly(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    closeable.close();
                }
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception ignored) {
            }
        }
    }


    public void turnPermiss() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Utils.checkPermission(PERMISSION, MainActivity.this) == PackageManager.PERMISSION_GRANTED) {
                updateToolbar();
                getUser();
            } else {
                MainActivity.this.requestPermissions(PERMISSION, 1);
            }
        } else {
            updateToolbar();
            getUser();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0) {
                updateToolbar();
                getUser();
            }
        }

    }


    @Override
    public void onBackPressed() {
        rateAppDialog.show();
    }

}
