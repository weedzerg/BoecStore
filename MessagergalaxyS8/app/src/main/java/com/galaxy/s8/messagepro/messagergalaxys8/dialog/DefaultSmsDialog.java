package com.galaxy.s8.messagepro.messagergalaxys8.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.galaxy.s8.messagepro.messagergalaxys8.R;

/**
 * Created by binhnk on 7/25/2017.
 */

public class DefaultSmsDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private OnButtonClicked onButtonClicked;

    private TextView tv_ok, tv_cancel;

    public DefaultSmsDialog(@NonNull Context context, OnButtonClicked onButtonClicked) {
        super(context);
        this.context = context;
        this.onButtonClicked = onButtonClicked;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.diaglog_sms_default);

        tv_ok = (TextView) findViewById(R.id.tv_ok);
        tv_cancel = (TextView) findViewById(R.id.tv_huy);

        tv_ok.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_huy:
                tv_cancel.setTextColor(Color.parseColor("#00a697"));
                dismiss();
                onButtonClicked.onCancel();
                break;

            case R.id.tv_ok:
                tv_ok.setTextColor(Color.parseColor("#00a697"));
                dismiss();
                onButtonClicked.onOk();
                break;

            default:
                dismiss();
                break;
        }
    }

    public interface OnButtonClicked {
        void onOk();

        void onCancel();
    }
}
