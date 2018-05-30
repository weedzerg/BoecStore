package com.galaxy.s8.messagepro.messagergalaxys8.services;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;

import com.galaxy.s8.messagepro.messagergalaxys8.inerfaces.UploadSMSRecieve;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.Objectsms;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Contants;

import java.util.LinkedList;
import java.util.Queue;

public class SentMessage extends BroadcastReceiver {
    public static UploadSMSRecieve uploadSMSRecieve = null;
    public static Queue<Objectsms> ls = new LinkedList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        Objectsms objectsms = ls.poll();

        if (checkdefault(context)) {
            ContentValues values = new ContentValues();
            values.put("address", objectsms.getNumber());
            values.put("body", objectsms.getBody());
            context.getContentResolver().insert(Uri.parse(Contants.SMS_URI_SENT), values);
        }
        uploadSMSRecieve.loadsmsRecieve(getResultCode());
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

}
