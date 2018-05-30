package com.galaxy.s8.messagepro.messagergalaxys8.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.util.Log;

import com.galaxy.s8.messagepro.messagergalaxys8.R;
import com.galaxy.s8.messagepro.messagergalaxys8.activitys.MainActivity;
import com.galaxy.s8.messagepro.messagergalaxys8.inerfaces.ReloadSMS;
import com.galaxy.s8.messagepro.messagergalaxys8.inerfaces.UploadSMSRecieve;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.InfoSender;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Cache;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Contants;

import java.util.ArrayList;

import static android.content.Context.NOTIFICATION_SERVICE;

public class SMSBroadcastReceiver extends BroadcastReceiver {
    public static UploadSMSRecieve uploadSMSRecieve = null;
    public static ReloadSMS reloadSMS = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        // Parse the SMS.
        Cache cache = new Cache(context);

        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "";
        if (bundle != null) {

            // Retrieve the SMS.
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            ArrayList<InfoSender> ls = new ArrayList<>();
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                String number = msgs[i].getOriginatingAddress();
                String body = msgs[i].getMessageBody();
                long date = msgs[i].getTimestampMillis();
                ls.add(new InfoSender(number, date, body));
                ContentValues values = new ContentValues();
                values.put("address", number);
                values.put("body", body);
                if (checkdefault(context)) {
                    String n = cache.getLink(Contants.BLOCK_SMS);
                    if (n != null) {
                        String[] s = cache.getLink(Contants.BLOCK_SMS).split(" ");
                        if (!checkblocknum(s, number)) {
                            context.getContentResolver().insert(Uri.parse(Contants.SMS_URI_INBOX), values);
                            Log.d("SERVICE", str);
                            createNotification(context, number, body);
                            if (uploadSMSRecieve != null) {
                                try {
                                    uploadSMSRecieve.loadsmsRecieve_(ls);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if (reloadSMS != null) {
                                try {
                                    reloadSMS.reloadSMS();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } else {
                        context.getContentResolver().insert(Uri.parse(Contants.SMS_URI_INBOX), values);
                        Log.d("SERVICE", str);
                        createNotification(context, number, body);

                        if (uploadSMSRecieve != null) {
                            try {
                                uploadSMSRecieve.loadsmsRecieve_(ls);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if (reloadSMS != null) {
                            try {
                                reloadSMS.reloadSMS();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    if (uploadSMSRecieve != null) {
                        try {
                            uploadSMSRecieve.loadsmsRecieve_(ls);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (reloadSMS != null) {
                        try {
                            reloadSMS.reloadSMS();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public boolean checkdefault(Context context) {
        String packagename = context.getPackageName();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (!Telephony.Sms.getDefaultSmsPackage(context).equals(packagename)) {
                return false;
            }
        }
        return true;
    }

    private void createNotification(Context context, String n, String b) {
        Cache cache = new Cache(context);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Intent i = new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(context, 0, i,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setContentTitle(n);
        builder.setContentText(b);
        builder.setContentIntent(intent);
        builder.setTicker(n);
        builder.setSmallIcon(R.drawable.ic_laucher);
        builder.setAutoCancel(true);
        builder.setSound(defaultSoundUri);
        Notification notification = builder.build();
//        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.notification_layout);
//        contentView.setTextViewText(R.id.tv_title, n);
//        contentView.setTextViewText(R.id.tv_body, b);
//        notification.contentView = contentView;
//
//
//        RemoteViews expandedView =
//                new RemoteViews(context.getPackageName(), R.layout.notification_layout);
//
//        expandedView.setTextViewText(R.id.tv_title, n);
//        expandedView.setTextViewText(R.id.tv_body, b);
//        String uri = cache.getLink(n);
//        if (uri == null || uri.equals("true")) {
//            expandedView.setImageViewResource(R.id.iv_noti, R.drawable.ic_user);
//        } else {
//            expandedView.setImageViewUri(R.id.iv_noti, Uri.parse(uri));
//        }
//        Intent stopIntent = new Intent("call_method");
//        stopIntent.putExtra("number", n);
//        PendingIntent call = PendingIntent.getBroadcast(context, 4, stopIntent, PendingIntent.FLAG_CANCEL_CURRENT);
//        expandedView.setOnClickPendingIntent(R.id.tv_call, call);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            notification.bigContentView = expandedView;
//        }


        NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0, notification);
    }

    public boolean checkblocknum(String[] s, String number) {
        for (int i = 0; i < s.length; i++) {
            if (s[i].equals(number)) return true;
        }
        return false;
    }
}
