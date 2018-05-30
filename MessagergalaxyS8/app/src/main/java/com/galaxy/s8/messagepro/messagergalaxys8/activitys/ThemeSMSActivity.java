package com.galaxy.s8.messagepro.messagergalaxys8.activitys;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.galaxy.s8.messagepro.messagergalaxys8.R;
import com.galaxy.s8.messagepro.messagergalaxys8.adapters.AdapterThem;
import com.galaxy.s8.messagepro.messagergalaxys8.dialog.ThemeDialog;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Cache;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Contants;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.ShowAds;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Utils;

import java.util.ArrayList;

public class ThemeSMSActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ArrayList<Integer> ls;
    private AdapterThem adapterThem;
    private GridView gridView;
    private ThemeDialog themeDialog;
    private ImageView iv_back;
    private LinearLayout ln_back;
    private int id_ = -1;
private RelativeLayout adslayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_them_all);
        init();
        themeDialog = new ThemeDialog(ThemeSMSActivity.this, new ThemeDialog.OnButtonClicked() {
            @Override
            public void onRateClicked() {
                Cache cache = new Cache(getBaseContext());
                if (id_ != -1) {
                    cache.putLink(Contants.CACHE_BACKGROUND, adapterThem.getItem(id_) + "");
                    cache.putLink(Contants.CACHE_STATUS_BAR, "#f5f5f5");
                    Contants.BACKGROUND = adapterThem.getItem(id_);
                    Contants.COLOR_STATUS_BAR = "#f5f5f5";
                    String textcolor = null;
                    switch (Contants.BACKGROUND) {
                        case R.drawable.tm1:
                            textcolor = "#252525";
                            Contants.COLOR_STATUS_BAR = "#239faf";
                            break;
                        case R.drawable.tm2:
                            textcolor = "#252525";
                            Contants.COLOR_STATUS_BAR = "#558ac3";
                            break;
                        case R.drawable.tm5:
                            textcolor = "#252525";
                            Contants.COLOR_STATUS_BAR = "#68e2d0";
                            break;
                        case R.drawable.tm7:
                            textcolor = "#252525";
                            Contants.COLOR_STATUS_BAR = "#f26968";
                            break;
                        case R.drawable.tm9:
                            textcolor = "#252525";
                            Contants.COLOR_STATUS_BAR = "#e5adff";
                            break;
                        case R.drawable.tm3:
                            textcolor = "#ababab";
                            Contants.COLOR_STATUS_BAR = "#222222";
                            break;
                        case R.drawable.tm4:
                            textcolor = "#ababab";
                            Contants.COLOR_STATUS_BAR = "#323464";
                            break;
                        case R.drawable.tm6:
                            textcolor = "#252525";
                            Contants.COLOR_STATUS_BAR = "#66006a";
                            break;
                        case R.drawable.tm8:
                            textcolor = "#ababab";
                            Contants.COLOR_STATUS_BAR = "#f29400";
                            break;
                        case R.drawable.tm10:
                            textcolor = "#ababab";
                            Contants.COLOR_STATUS_BAR = "#240761";
                            break;
                        case R.drawable.tm11:
                            textcolor = "#ababab";
                            Contants.COLOR_STATUS_BAR = "#0f0912";
                            break;
                        case R.drawable.tm12:
                            textcolor = "#ababab";
                            Contants.COLOR_STATUS_BAR = "#0f0912";
                            break;
                    }
                    cache.putLink(Contants.CACHE_TEXTVIEW, textcolor);
                    cache.putLink(Contants.CACHE_STATUS_BAR, Contants.COLOR_STATUS_BAR);
                    finish();
                }
            }

            @Override
            public void onCancelClicked() {

            }
        });
    }

    private Cache cache;

    public void init() {
        adslayout = (RelativeLayout) findViewById(R.id.lnads);
        ShowAds.showAdsNative(adslayout, ThemeSMSActivity.this);
        cache = new Cache(getBaseContext());
        Utils.getCache(cache);
        ls = new ArrayList<>();
        ls.add(R.drawable.tm1);
        ls.add(R.drawable.tm2);
        ls.add(R.drawable.tm3);
        ls.add(R.drawable.tm4);
        ls.add(R.drawable.tm5);
        ls.add(R.drawable.tm6);
        ls.add(R.drawable.tm7);
        ls.add(R.drawable.tm8);
        ls.add(R.drawable.tm9);
        ls.add(R.drawable.tm10);
        ls.add(R.drawable.tm11);
        ls.add(R.drawable.tm12);
        ln_back = (LinearLayout) findViewById(R.id.ln_back);
        uploadIU();
        iv_back = (ImageView) findViewById(R.id.iv_back);
        gridView = (GridView) findViewById(R.id.gd_view);
        adapterThem = new AdapterThem(ls, getLayoutInflater(), Contants.BACKGROUND);
        gridView.setAdapter(adapterThem);
        gridView.setOnItemClickListener(this);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void uploadIU() {
        //background;
        if (Contants.BACKGROUND != -1) {
            ln_back.setBackgroundResource(Contants.BACKGROUND);
        }
        //setstatus bar

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Contants.COLOR_STATUS_BAR != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(Color.parseColor(Contants.COLOR_STATUS_BAR));
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        id_ = i;
        themeDialog.show();
    }
}
