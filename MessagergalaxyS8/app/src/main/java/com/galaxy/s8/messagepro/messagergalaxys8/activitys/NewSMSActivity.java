package com.galaxy.s8.messagepro.messagergalaxys8.activitys;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.galaxy.s8.messagepro.messagergalaxys8.R;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.BodySMS;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.InfoContact;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.Objectsms;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.SenderSMS;
import com.galaxy.s8.messagepro.messagergalaxys8.services.DeliveredMessage;
import com.galaxy.s8.messagepro.messagergalaxys8.services.SentMessage;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Cache;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Contants;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.ShowAds;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import static com.galaxy.s8.messagepro.messagergalaxys8.utils.Contants.BODY_SMS_ACTIVITY;
import static com.galaxy.s8.messagepro.messagergalaxys8.utils.Contants.lscontacts;

public class NewSMSActivity extends AppCompatActivity {
    private ArrayList<InfoContact> lscontact;
    private LinearLayout lnContacts;
    private String s = "";
    private String number = "";
    private EditText ed_input;
    private TextView iv_send;
    private EditText ed_input_addcontact;
    private ImageView iv_add;
    private ImageView iv_back;
    private FrameLayout ln_back;
    private Cache cache;
    private HashMap<View, InfoContact> lsview_;
    private RelativeLayout adslayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sms);
        cache = new Cache(getBaseContext());
        Utils.getCache(cache);
        lscontact = (ArrayList<InfoContact>) getIntent().getSerializableExtra(Contants.INTENT_GET_CONTACT);
        init();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        uploadIU();
    }

    public void init() {
        adslayout = (RelativeLayout) findViewById(R.id.lnads);
        ShowAds.showAdsNative(adslayout, NewSMSActivity.this);
        lsview_ = new HashMap<>();
        ln_back = (FrameLayout) findViewById(R.id.ln_back);
        uploadIU();
        iv_back = (ImageView) findViewById(R.id.iv_back);
        lnContacts = (LinearLayout) findViewById(R.id.ln_re);
        ed_input = (EditText) findViewById(R.id.ed_input_sms);
        iv_send = (TextView) findViewById(R.id.iv_send);
        ed_input = (EditText) findViewById(R.id.ed_input_sms);
        iv_add = (ImageView) findViewById(R.id.iv_add_contact);
        ed_input_addcontact = (EditText) findViewById(R.id.ed_input_contact);
        for (InfoContact infoContact : lscontact) {
            addView(infoContact);
            s += infoContact.getNameContact() + ",";
            number += infoContact.getNumContact() + " ";
        }
        iv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_input.getText().toString().equals("")) {
                    Toast.makeText(NewSMSActivity.this, "Hãy nhập nội dung cho tin nhắn gửi đi!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    for (InfoContact infoContact : lscontact) {
                        SentMessage.ls.offer(new Objectsms(infoContact.getNumContact(), ed_input.getText().toString()));
                    }
                    sendSMS(lscontact, ed_input.getText().toString());
                    ArrayList<BodySMS> ls = new ArrayList<BodySMS>();
                    ls.add(new BodySMS(ed_input.getText().toString(), Calendar.getInstance().getTimeInMillis(),
                            "2", 2));
                    SenderSMS sms = new SenderSMS(s, number, ls);
                    Intent intent = new Intent(NewSMSActivity.this, BodySMSActivity.class);
                    intent.putExtra(BODY_SMS_ACTIVITY, sms);
                    startActivity(intent);
                    finish();
                }
            }
        });
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = ed_input_addcontact.getText().toString();
                if (number.equals("")) {
                    Intent intent = new Intent(NewSMSActivity.this, ChoiceContactActivity.class);
                    intent.putExtra(Contants.INTENT_SMS, "true");
                    startActivityForResult(intent, 100);
                } else {
                    if (number.matches("^[09][0-9]{9}$")) {
                        InfoContact infoContact = new InfoContact("", number, number);
                        lscontact.add(infoContact);
                        addView(infoContact);
                        ed_input_addcontact.setText("");
                    } else if (number.matches("^[01][0-9]{10}$")) {
                        InfoContact infoContact = new InfoContact("", number, number);
                        lscontact.add(infoContact);
                        addView(infoContact);
                        ed_input_addcontact.setText("");
                    }
                }
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void sendSMS(ArrayList<InfoContact> ls, String message) {
        ArrayList<PendingIntent> sentPendingIntents = new ArrayList<PendingIntent>();
        ArrayList<PendingIntent> deliveredPendingIntents = new ArrayList<PendingIntent>();
        PendingIntent sentPI = PendingIntent.getBroadcast(NewSMSActivity.this, 0,
                new Intent(NewSMSActivity.this, SentMessage.class), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(NewSMSActivity.this, 0,
                new Intent(NewSMSActivity.this, DeliveredMessage.class), 0);
        try {
            SmsManager sms = SmsManager.getDefault();
            ArrayList<String> mSMSMessage = sms.divideMessage(message);
            for (int i = 0; i < mSMSMessage.size(); i++) {
                sentPendingIntents.add(i, sentPI);
                deliveredPendingIntents.add(i, deliveredPI);
            }
            for (int i = 0; i < ls.size(); i++) {
                sms.sendMultipartTextMessage(ls.get(i).getNumContact(), null, mSMSMessage,
                        sentPendingIntents, deliveredPendingIntents);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "SMS sending failed...", Toast.LENGTH_SHORT).show();
        }
    }

    public void addView(InfoContact name) {
        View view = getLayoutInflater().inflate(R.layout.item_layout_contact_add, lnContacts, false);
        ((TextView) view.findViewById(R.id.tv_name_contact_new_sms)).setText(name.getNameContact());
        lsview_.put(view, name);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lnContacts.removeView(view);
                if (lsview_.containsKey(view)) {
                    InfoContact infoContact = lsview_.get(view);
                    lscontacts.remove(infoContact);
                }

            }
        });
        lnContacts.addView(view);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            ArrayList<InfoContact> lsrresult = null;
            try {
                lsrresult = (ArrayList<InfoContact>) data.getSerializableExtra(Contants.INTENT_SMS_DATA);
                for (InfoContact a : lsrresult) {
                    boolean check = removeContact(a);
                    if (check == false) {
                        lscontact.add(a);
                        addView(a);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public boolean removeContact(InfoContact a) {
        for (InfoContact infoContact : lscontact) {
            if (infoContact.getNumContact().equals(a.getNumContact())) {
                return true;
            }
        }
        return false;
    }
}
