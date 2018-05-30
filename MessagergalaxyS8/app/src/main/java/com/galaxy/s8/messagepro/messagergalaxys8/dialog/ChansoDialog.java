package com.galaxy.s8.messagepro.messagergalaxys8.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Switch;
import android.widget.TextView;

import com.galaxy.s8.messagepro.messagergalaxys8.R;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Cache;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Contants;

/**
 * Created by binhnk on 7/25/2017.
 */

public class ChansoDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private OnButtonClicked onButtonClicked;
    private Switch sw;
    private TextView tvRateApp, tvNotNow;
    private String numbeer;

    public ChansoDialog(@NonNull Context context, OnButtonClicked onButtonClicked, String numbeer) {
        super(context);
        this.context = context;
        this.onButtonClicked = onButtonClicked;
        this.numbeer = numbeer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_chan_so);
        sw = (Switch) findViewById(R.id.sw_chanso);
        Cache cache = new Cache(getContext());
        String value = cache.getLink(Contants.BLOCK_SMS);
        if (value != null) {
            String [] s=value.split(" ");

            if (checkblocknum(s, numbeer)) {
                sw.setChecked(true);
            } else {
                sw.setChecked(false);
            }
        }
        tvRateApp = (TextView) findViewById(R.id.tv_ok);
        tvNotNow = (TextView) findViewById(R.id.tv_huy);

        tvRateApp.setOnClickListener(this);
        tvNotNow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_huy:
                dismiss();
                onButtonClicked.onCancelClicked();
                break;

            case R.id.tv_ok:
                dismiss();
                onButtonClicked.onRateClicked();
                break;

            default:
                dismiss();
                break;
        }
    }

    public Switch getSw() {
        return sw;
    }

    public interface OnButtonClicked {
        void onRateClicked();

        void onCancelClicked();
    }

    public boolean checkblocknum(String[] s, String number) {
        for (int i = 0; i < s.length; i++) {
            if (s[i].equals(number)) return true;
        }
        return false;
    }
}
