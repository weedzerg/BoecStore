package com.galaxy.s8.messagepro.messagergalaxys8.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.galaxy.s8.messagepro.messagergalaxys8.R;
import com.galaxy.s8.messagepro.messagergalaxys8.adapters.AdapterContact;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.InfoContact;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Cache;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Contants;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.ShowAds;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Utils;

import java.util.ArrayList;

import static com.galaxy.s8.messagepro.messagergalaxys8.utils.Contants.INTENT_GET_CONTACT;

public class ChoiceContactActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ArrayList<InfoContact> lscontact;
    private Cache cache;
    private AdapterContact adapterContact;
    private ListView lsView;
    private TextView tv_hoantat;
    private ArrayList<InfoContact> lscontactadd;
    private String result;
    private FrameLayout ln_back;
    private ImageView iv_back;
    private RelativeLayout adslayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_contact);
        result = getIntent().getStringExtra(Contants.INTENT_SMS);
        init();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
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

    public void init() {
        adslayout = (RelativeLayout) findViewById(R.id.lnads);
        ShowAds.showAdsNative(adslayout, ChoiceContactActivity.this);
        lscontact = new ArrayList<>();
        lscontactadd = new ArrayList<>();
        cache = new Cache(getBaseContext());
        Utils.getCache(cache);
        lsView = (ListView) findViewById(R.id.ls_view_contact);
        ln_back = (FrameLayout) findViewById(R.id.ln_back);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (result != null) {
                    finish();
                } else {
                    finish();
                }

            }
        });
        uploadIU();
        tv_hoantat = (TextView) findViewById(R.id.tv_hoantat);
        for (int i = 0; i < Contants.lscontacts.size(); i++) {
            Contants.lscontacts.get(i).setOnclick(false);
        }
        adapterContact = new AdapterContact(Contants.lscontacts, getLayoutInflater(), cache);
        lsView.setAdapter(adapterContact);
        lsView.setOnItemClickListener(this);
        tv_hoantat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (result != null) {
                    Intent intent = new Intent();
                    intent.putExtra(Contants.INTENT_SMS_DATA, lscontactadd);
                    setResult(100, intent);
                    finish();
                } else {
                    Intent intent = new Intent(ChoiceContactActivity.this, NewSMSActivity.class);
                    intent.putExtra(INTENT_GET_CONTACT, lscontactadd);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        boolean c = adapterContact.getItem(i).isOnclick();
        if (!c) {
            lscontactadd.add(adapterContact.getItem(i));
        } else {
            lscontactadd.remove(adapterContact.getItem(i));
        }
        adapterContact.getItem(i).setOnclick(!c);
        adapterContact.notifyDataSetChanged();
    }

}
