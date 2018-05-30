package com.media.vid.video_speed.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.media.vid.video_speed.R;


/**
 * Created by binhnk on 7/25/2017.
 */

public class RemoveDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private OnButtonClicked onButtonClicked;
    private int key;
    private TextView tvRateApp, tvNotNow;

    public RemoveDialog(@NonNull Context context, OnButtonClicked onButtonClicked) {
        super(context);
        this.context = context;
        this.onButtonClicked = onButtonClicked;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.remove_app);

        tvRateApp = (TextView) findViewById(R.id.tv_rate_app);
        tvNotNow = (TextView) findViewById(R.id.tv_not_now);

        tvRateApp.setOnClickListener(this);
        tvNotNow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_not_now:
                dismiss();
                onButtonClicked.onCancelClicked();
                break;

            case R.id.tv_rate_app:
                dismiss();
                onButtonClicked.onRateClicked();
                break;

            default:
                dismiss();
                break;
        }
    }

    public interface OnButtonClicked {
        void onRateClicked();

        void onCancelClicked();
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
