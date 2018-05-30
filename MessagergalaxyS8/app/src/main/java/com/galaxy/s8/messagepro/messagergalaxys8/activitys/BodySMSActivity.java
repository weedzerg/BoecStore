package com.galaxy.s8.messagepro.messagergalaxys8.activitys;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.galaxy.s8.messagepro.messagergalaxys8.R;
import com.galaxy.s8.messagepro.messagergalaxys8.adapters.AdapterSMSBody;
import com.galaxy.s8.messagepro.messagergalaxys8.dialog.ChansoDialog;
import com.galaxy.s8.messagepro.messagergalaxys8.dialog.DiaglogPhanhoi;
import com.galaxy.s8.messagepro.messagergalaxys8.inerfaces.ReloadSMS;
import com.galaxy.s8.messagepro.messagergalaxys8.inerfaces.UploadSMSRecieve;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.BodySMS;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.InfoSender;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.Objectsms;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.SenderSMS;
import com.galaxy.s8.messagepro.messagergalaxys8.services.DeliveredMessage;
import com.galaxy.s8.messagepro.messagergalaxys8.services.SMSBroadcastReceiver;
import com.galaxy.s8.messagepro.messagergalaxys8.services.SentMessage;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.ActionSMS;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Cache;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Contants;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.ShowAds;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class BodySMSActivity extends AppCompatActivity implements View.OnClickListener, UploadSMSRecieve, ReloadSMS {


    private static final String[] PERMISSION = {"android.permission.CALL_PHONE"};

    private SenderSMS senderSMS;
    private AdapterSMSBody adapterSMSBody;
    private ListView listview;
    private TextView tv_sender, tv_num_sender;
    private ImageView iv_back, iv_call, iv_menu;
    private ArrayList<BodySMS> ls;

    private EditText ed_input;
    private TextView
            iv_send;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private LinearLayout ln_left, ln_add_img, ln_add_user, ln_remove;
    private TextView tv_phanhoi, tv_hengio,
            tv_theme, tv_slide, tv_chan,
            tv_share_contact, tv_name_sender, tv_number;
    private ImageView iv_sender;

    private DiaglogPhanhoi diaglogPhanhoi;
    private Cache cache;
    private String uri;
    private ChansoDialog chansoDialog;
    private RelativeLayout adslayout;
    private LinearLayout ln_back, ln_input, ln_draw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_sms);
        SentMessage.uploadSMSRecieve = this;
        SMSBroadcastReceiver.reloadSMS = this;
        init();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        uploadIU();
    }

    public void init() {
        initID();

        cache = new Cache(getBaseContext());
        Utils.getCache(cache);
        uploadIU();
        ls = new ArrayList<>();
        iv_back.setOnClickListener(this);
        iv_call.setOnClickListener(this);
        iv_menu.setOnClickListener(this);
        senderSMS = (SenderSMS) getIntent().getSerializableExtra(Contants.BODY_SMS_ACTIVITY);
        tv_name_sender.setText(senderSMS.getNameSender());
        tv_number.setText(senderSMS.getNumberSender());
        updateToolbar();
        ls = senderSMS.getLsbody();
        Collections.reverse(ls);
        uri = cache.getLink(senderSMS.getNumberSender());
        adapterSMSBody = new AdapterSMSBody(ls, getLayoutInflater(), uri, senderSMS.getNameSender());
        listview.setAdapter(adapterSMSBody);
        listview.setSelection(ls.size() - 1);
        adapterSMSBody.notifyDataSetChanged();
        initDrawlayout();
        if (uri == null || uri.equals("true")) {
            iv_sender.setImageResource(R.drawable.ic_user);
        } else {
            Picasso.with(getBaseContext()).load(uri).into(iv_sender);
        }
        chansoDialog = new ChansoDialog(BodySMSActivity.this, new ChansoDialog.OnButtonClicked() {
            @Override
            public void onRateClicked() {
                if (chansoDialog.getSw().isChecked()) {
                    String s = cache.getLink(Contants.BLOCK_SMS);
                    if (s != null) {
                        s += " " + senderSMS.getNumberSender();
                    } else {
                        s = senderSMS.getNumberSender();
                    }
                    cache.putLink(Contants.BLOCK_SMS, s);
                } else {
                    String value = cache.getLink(Contants.BLOCK_SMS);
                    if (value != null) {
                        String[] s = value.split(" ");
                        int pos = checkblocknum(s, senderSMS.getNumberSender());
                        if (pos != -1) {
                            s[pos] = "";
                        }
                        cache.putLink(Contants.BLOCK_SMS, tostring_(s));
                    }
                }
                Log.d("DEBUG", cache.getLink(Contants.BLOCK_SMS));
            }


            @Override
            public void onCancelClicked() {

            }
        }, senderSMS.getNumberSender());
    }

    public void uploadIU() {
        //background;
        if (Contants.BACKGROUND != -1) {
            ln_back.setBackgroundResource(Contants.BACKGROUND);
            ln_draw.setBackgroundResource(Contants.BACKGROUND);
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

    public void updateToolbar() {
        tv_sender.setText(senderSMS.getNameSender());
        tv_num_sender.setText(senderSMS.getNumberSender());

    }

    public String tostring_(String[] s) {
        String v = "";
        try {
            if (s.length != 0) {
                if (!s[0].equals("")) {
                    v += s[0];
                }
            }
            for (int i = 1; i < s.length; i++) {
                if (!s[i].equals("")) {
                    v += " " + s[i];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back: {
                finish();
                break;
            }
            case R.id.iv_call: {
                permissionON_OFF();
                break;
            }
            case R.id.iv_menu: {
                if (mDrawerLayout.isDrawerOpen(ln_left)) {
                    mDrawerLayout.closeDrawer(Gravity.END);
                } else {
                    mDrawerLayout.openDrawer(Gravity.END);
                }
                mDrawerToggle.syncState();

                break;
            }
            case R.id.tv_phanhoinhanh: {
                diaglogPhanhoi.show();
                break;
            }
            case R.id.tv_hengio: {
                break;
            }
            case R.id.tv_theme: {
                Intent intent = new Intent(BodySMSActivity.this, ThemeSMSActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.tv_slide: {
                break;
            }
            case R.id.tv_chan: {
                chansoDialog.show();
                break;
            }
            case R.id.tv_share_contact: {
                break;
            }
            case R.id.tv_send_: {
                if (ed_input.getText().toString().equals("")) {
                    Toast.makeText(BodySMSActivity.this, "Hãy nhập nội dung cho tin nhắn gửi đi!", Toast.LENGTH_SHORT).show();
                } else {
                    sendSMS(senderSMS.getNumberSender(), ed_input.getText().toString());
                    SentMessage.ls.offer(new Objectsms(senderSMS.getNumberSender(), ed_input.getText().toString()));
                    BodySMS b = new BodySMS(ed_input.getText().toString(), Calendar.getInstance().getTimeInMillis(), "2", 2);
                    ls.add(b);
                    adapterSMSBody.setLs(ls);
                    adapterSMSBody.notifyDataSetChanged();
                    listview.smoothScrollToPosition(ls.size() - 1);
                    hideSoftKeyboard(BodySMSActivity.this);
                    ed_input.setText("");

                }
                break;
            }
        }
    }

    public void initID() {
        ln_left = (LinearLayout) findViewById(R.id.left_drawer);
        ln_add_img = (LinearLayout) findViewById(R.id.ln_add_img);
        ln_add_user = (LinearLayout) findViewById(R.id.ln_add_user);
        ln_remove = (LinearLayout) findViewById(R.id.ln_remove);
        listview = (ListView) findViewById(R.id.lsview_body);
        tv_sender = (TextView) findViewById(R.id.tv_sender);
        tv_num_sender = (TextView) findViewById(R.id.tv_num_sender);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_call = (ImageView) findViewById(R.id.iv_call);
        iv_menu = (ImageView) findViewById(R.id.iv_menu);

        tv_phanhoi = (TextView) findViewById(R.id.tv_phanhoinhanh);
        tv_hengio = (TextView) findViewById(R.id.tv_hengio);
        tv_theme = (TextView) findViewById(R.id.tv_theme);
        tv_slide = (TextView) findViewById(R.id.tv_slide);
        tv_name_sender = (TextView) findViewById(R.id.tv_sender_draw);
        tv_number = (TextView) findViewById(R.id.tv_num_sender_draw);
        tv_chan = (TextView) findViewById(R.id.tv_chan);
        tv_share_contact = (TextView) findViewById(R.id.tv_share_contact);
        iv_sender = (ImageView) findViewById(R.id.iv_sender);
        tv_phanhoi.setOnClickListener(this);
        tv_hengio.setOnClickListener(this);
        tv_theme.setOnClickListener(this);
        tv_slide.setOnClickListener(this);
        tv_chan.setOnClickListener(this);
        tv_share_contact.setOnClickListener(this);

        ed_input = (EditText) findViewById(R.id.ed_input_sms);
        iv_send = (TextView) findViewById(R.id.tv_send_);
        iv_send.setOnClickListener(this);

        ln_back = (LinearLayout) findViewById(R.id.ln_back);
        ln_draw = (LinearLayout) findViewById(R.id.ln_back_draw);
        ln_input = (LinearLayout) findViewById(R.id.ln_input);

        adslayout = (RelativeLayout) findViewById(R.id.lnads);
        ShowAds.showAdsNative(adslayout, BodySMSActivity.this);
    }

    public void initDrawlayout() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dr_right_layout);
        mDrawerLayout.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        diaglogPhanhoi = new DiaglogPhanhoi(BodySMSActivity.this);
        diaglogPhanhoi.setOnResult(new DiaglogPhanhoi.OnResult() {
            @Override
            public void onFished(String result) {
                mDrawerLayout.closeDrawer(Gravity.END);
                ed_input.setText(result);
            }
        });
    }

    // Method to send SMS.
    private void sendSMS(String phoneNumber, String message) {
        ArrayList<PendingIntent> sentPendingIntents = new ArrayList<PendingIntent>();
        ArrayList<PendingIntent> deliveredPendingIntents = new ArrayList<PendingIntent>();
        PendingIntent sentPI = PendingIntent.getBroadcast(BodySMSActivity.this, 0,
                new Intent(BodySMSActivity.this, SentMessage.class), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(BodySMSActivity.this, 0,
                new Intent(BodySMSActivity.this, DeliveredMessage.class), 0);
        try {
            SmsManager sms = SmsManager.getDefault();
            ArrayList<String> mSMSMessage = sms.divideMessage(message);
            for (int i = 0; i < mSMSMessage.size(); i++) {
                sentPendingIntents.add(i, sentPI);
                deliveredPendingIntents.add(i, deliveredPI);
            }
            sms.sendMultipartTextMessage(phoneNumber, null, mSMSMessage,
                    sentPendingIntents, deliveredPendingIntents);

        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(getBaseContext(), "SMS sending failed...", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void loadsmsRecieve(int check) {

        switch (check) {
            case Activity.RESULT_OK:
                ls.clear();
                ls = ActionSMS.getSmsFromASender(BodySMSActivity.this, senderSMS.getNumberSender());
                Collections.reverse(ls);
                adapterSMSBody.setLs(ls);
                adapterSMSBody.notifyDataSetChanged();
                listview.smoothScrollToPosition(ls.size() - 1);
                Toast.makeText(this, getString(R.string.sent), Toast.LENGTH_SHORT).show();
                break;
            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                //tin nhan khong gui duoc
                ls.get(ls.size() - 1).setChecksent(2);
                adapterSMSBody.setLs(ls);
                ed_input.setText(ls.get(ls.size() - 1).getBody());
                adapterSMSBody.notifyDataSetChanged();
                Toast.makeText(this, getString(R.string.notsent), Toast.LENGTH_SHORT).show();
                break;
            case SmsManager.RESULT_ERROR_NO_SERVICE:
                //khong co mang
                break;
            case SmsManager.RESULT_ERROR_NULL_PDU:

                break;
            case SmsManager.RESULT_ERROR_RADIO_OFF:

                break;
        }


    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void loadsmsRecieve_(ArrayList<InfoSender> lssender) {


    }

    public void permissionON_OFF() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Utils.checkPermission(PERMISSION, BodySMSActivity.this) == PackageManager.PERMISSION_GRANTED) {
                calling();
            } else {
                BodySMSActivity.this.requestPermissions(PERMISSION, 1);
            }
        } else {
            calling();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0) {
                calling();
            }
        }

    }

    public void calling() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + senderSMS.getNumberSender()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

    public int checkblocknum(String[] s, String number) {
        for (int i = 0; i < s.length; i++) {
            if (s[i].equals(number)) return i;
        }
        return -1;
    }

    @Override
    public void reloadSMS() {
        ls.clear();
        ls = ActionSMS.getSmsFromASender(BodySMSActivity.this, senderSMS.getNumberSender());
        Collections.reverse(ls);
        adapterSMSBody.setLs(ls);
        adapterSMSBody.notifyDataSetChanged();
        listview.smoothScrollToPosition(ls.size() - 1);
    }
}
