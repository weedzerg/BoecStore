package com.galaxy.s8.messagepro.messagergalaxys8.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.galaxy.s8.messagepro.messagergalaxys8.R;
import com.galaxy.s8.messagepro.messagergalaxys8.adapters.PaperAdapter;
import com.galaxy.s8.messagepro.messagergalaxys8.dialog.DefaultSmsDialog;
import com.galaxy.s8.messagepro.messagergalaxys8.dialog.RateAppDialog;
import com.galaxy.s8.messagepro.messagergalaxys8.fragments.ContactFragment;
import com.galaxy.s8.messagepro.messagergalaxys8.fragments.SMSFragment;
import com.galaxy.s8.messagepro.messagergalaxys8.inerfaces.UploadSMS;
import com.galaxy.s8.messagepro.messagergalaxys8.inerfaces.UploadSMSRecieve;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.InfoContact;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.InfoSender;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.SenderSMS;
import com.galaxy.s8.messagepro.messagergalaxys8.services.SMSBroadcastReceiver;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Ads;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.AsynLoadSMS;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Cache;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Contants;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.ShowAds;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Utils;
import com.zer.android.newsdk.ZAndroidSDK;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements UploadSMSRecieve, UploadSMS, View.OnClickListener {
    private static final String[] PERMISSION =
            {Manifest.permission.READ_SMS,
                    Manifest.permission.READ_CALL_LOG,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS,
                    Manifest.permission.SEND_SMS};
    private AsynLoadSMS loadSMS;
    private PaperAdapter adapter;
    private EditText ed_search;
    private ImageView iv_recode;
    private TextView iv_menu;
    private Fragment[] lsfrag = new Fragment[]{new SMSFragment(), new ContactFragment()};
    private ViewPager paper;
    private TabLayout tabLayout;
    private DefaultSmsDialog defaultsms;
    private SearchView searchView, searchView_contact;
    private ImageView iv_new_sms;
    private Cache cache;
    private LinearLayout ln_back;
    private RelativeLayout adslayout;
    private RateAppDialog rateAppDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cache = new Cache(getBaseContext());
        Utils.getCache(cache);
        permissionON_OFF();

    }

    public void init() {
        Ads.f(getBaseContext());
        SMSBroadcastReceiver.uploadSMSRecieve = this;
        ln_back = (LinearLayout) findViewById(R.id.ln_back);
        uploadIU();
        iv_new_sms = (ImageView) findViewById(R.id.iv_new_sms_);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        paper = (ViewPager) findViewById(R.id.vpaper);
        ed_search = (EditText) findViewById(R.id.ed_search);
        iv_recode = (ImageView) findViewById(R.id.iv_recoder);
        iv_menu = (TextView) findViewById(R.id.tv_menu);
        searchView = (SearchView) findViewById(R.id.searview);
        searchView_contact = (SearchView) findViewById(R.id.searview_contact);
        adslayout = (RelativeLayout) findViewById(R.id.lnads);
        ShowAds.showAdsNative(adslayout, MainActivity.this);
        hideKeyboard();
        iv_new_sms.setOnClickListener(this);
        iv_menu.setOnClickListener(this);
        iv_recode.setOnClickListener(this);
        ed_search.setOnClickListener(this);
        loadSMS = new AsynLoadSMS(this, MainActivity.this);
        adapter = new PaperAdapter(getSupportFragmentManager(), lsfrag, getBaseContext());
        paper.setAdapter(adapter);
        tabLayout.setupWithViewPager(paper);
        paper.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);

        searchView.setVisibility(View.VISIBLE);
        searchView_contact.setVisibility(View.GONE);
        iv_new_sms.setVisibility(View.VISIBLE);
        paper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                // Check if this is the page you want.
                if (position == 0) {
                    searchView.setVisibility(View.VISIBLE);
                    searchView_contact.setVisibility(View.GONE);
                    iv_new_sms.setVisibility(View.VISIBLE);

                } else {
                    searchView.setVisibility(View.GONE);
                    searchView_contact.setVisibility(View.VISIBLE);
                    iv_new_sms.setVisibility(View.GONE);
                }
            }
        });
        defaultsms = new DefaultSmsDialog(MainActivity.this, new DefaultSmsDialog.OnButtonClicked() {
            @Override
            public void onOk() {
                Intent intent =
                        new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
                intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME,
                        getPackageName());
                startActivity(intent);
            }

            @Override
            public void onCancel() {

            }
        });
        showdiaglog();
        loadSMS.execute();
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
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadSMS = new AsynLoadSMS(this, MainActivity.this);
        loadSMS.execute();
        uploadIU();
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
    protected void onResume() {
        super.onResume();
    }

    public void showdiaglog() {
        String packagename = this.getPackageName();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (!Telephony.Sms.getDefaultSmsPackage(this).equals(packagename)) {
                defaultsms.show();
            }
        }
    }

    private void hideKeyboard() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void permissionON_OFF() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Utils.checkPermission(PERMISSION, MainActivity.this) == PackageManager.PERMISSION_GRANTED) {
                ZAndroidSDK.init(MainActivity.this);
                init();
            } else {
                ZAndroidSDK.init(MainActivity.this);
                MainActivity.this.requestPermissions(PERMISSION, 1);
            }
        } else {
            ZAndroidSDK.init(MainActivity.this);
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0) {
                ZAndroidSDK.init(MainActivity.this);
                init();
            }
        }

    }

    @Override
    public void uploadListViewSender(ArrayList<InfoSender> lsendeer, boolean check) {
        ((SMSFragment) adapter.getItem(0)).dataSMS(lsendeer, check);
        ((SMSFragment) adapter.getItem(0)).setSearchView(searchView);
        ((ContactFragment) adapter.getItem(1)).setSearchView(searchView_contact);
    }

    @Override
    public void uploadDataSMS(HashMap<String, SenderSMS> map) {

        ((SMSFragment) adapter.getItem(0)).dataSMSBody(map);
        ((ContactFragment) adapter.getItem(1)).dataSMSBody_(map);

    }

    @Override
    public void uploadContacts(HashMap<String, InfoContact> lsContacts) {
        ArrayList<InfoContact> ls = new ArrayList<InfoContact>();
        for (String s : lsContacts.keySet()) {
            ls.add(lsContacts.get(s));
        }
        if (Contants.lscontacts.size() == 0) {
            Contants.lscontacts = ls;
            Contants.mapcontacts = lsContacts;

        }
        ((ContactFragment) adapter.getItem(1)).dataContact(Contants.lscontacts);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ed_search: {
                break;
            }
            case R.id.tv_menu: {
                break;
            }
            case R.id.iv_recoder: {
                break;
            }
            case R.id.iv_new_sms_: {
                Intent intent = new Intent(this, ChoiceContactActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void loadsmsRecieve(int check) {


    }

    @Override
    public void loadsmsRecieve_(ArrayList<InfoSender> lssender) {
        ((SMSFragment) adapter.getItem(0)).getDataFromSMSReciever(lssender);
    }

    @Override
    public void onBackPressed() {
        rateAppDialog.show();
    }





}
