package com.galaxy.s8.messagepro.messagergalaxys8.services;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DeliveredMessage extends BroadcastReceiver {
    private final String TAG = "DeliveredMessage ";

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (getResultCode()) {
            case Activity.RESULT_OK:
                break;
            case Activity.RESULT_CANCELED:
                break;
        }

    }
}
