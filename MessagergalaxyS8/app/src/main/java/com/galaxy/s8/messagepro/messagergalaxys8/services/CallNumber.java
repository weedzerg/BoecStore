package com.galaxy.s8.messagepro.messagergalaxys8.services;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

public class CallNumber extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action_name = intent.getAction();
        if (action_name.equals("call_method")) {
            // call your method here and do what ever you want.
            String num = intent.getStringExtra("number");
            Intent y = new Intent(Intent.ACTION_CALL);
            y.setData(Uri.parse("tel:" + num));
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            y.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(y);
        }
    }
}
