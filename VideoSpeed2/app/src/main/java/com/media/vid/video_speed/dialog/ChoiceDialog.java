package com.media.vid.video_speed.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.media.vid.video_speed.R;


/**
 * Created by binhnk on 7/25/2017.
 */

public class ChoiceDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private OnButtonClicked onButtonClicked;

    private TextView tv_ok, tv_cancel;
    private LinearLayout ln_fast, ln_slow;
    private ImageView iv_slow, iv_fast;
    private boolean check = false;

    //true slow
    //true fast
    public ChoiceDialog(@NonNull Context context, OnButtonClicked onButtonClicked) {
        super(context);
        this.context = context;
        this.onButtonClicked = onButtonClicked;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.diaglog_choice);
        ln_fast = (LinearLayout) findViewById(R.id.ln_fast);
        ln_slow = (LinearLayout) findViewById(R.id.ln_slow);
        iv_fast = (ImageView) findViewById(R.id.iv_fast);
        iv_slow = (ImageView) findViewById(R.id.iv_slow);
        tv_ok = (TextView) findViewById(R.id.tv_ok);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_ok.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        ln_slow.setOnClickListener(this);
        ln_fast.setOnClickListener(this);
        iv_slow.setOnClickListener(this);
        iv_fast.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ln_fast:
            case R.id.iv_fast:
                check = false;
                updatiUI();
                break;
            case R.id.ln_slow:
            case R.id.iv_slow:
                check = true;
                updatiUI();
                break;
            case R.id.tv_cancel:
                dismiss();
                onButtonClicked.onCancelClicked();
                break;

            case R.id.tv_ok:
                dismiss();
                onButtonClicked.onOkClicked(check);
                break;

            default:
                dismiss();
                break;
        }
    }

    public interface OnButtonClicked {
        void onOkClicked(boolean check);

        void onCancelClicked();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    public void updatiUI() {
        if (check) {
            iv_slow.setImageResource(R.drawable.ic_check);
            iv_fast.setImageResource(R.drawable.ic_uncheck);
        } else {
            iv_slow.setImageResource(R.drawable.ic_uncheck);
            iv_fast.setImageResource(R.drawable.ic_check);
        }

    }
}
