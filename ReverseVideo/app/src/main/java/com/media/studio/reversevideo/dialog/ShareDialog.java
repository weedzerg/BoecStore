package com.media.studio.reversevideo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.media.studio.reversevideo.R;


/**
 * Created by binhnk on 7/25/2017.
 */

public class ShareDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private OnButtonClicked onButtonClicked;

    private LinearLayout lnfb, lnis;

    public ShareDialog(@NonNull Context context, OnButtonClicked onButtonClicked) {
        super(context);
        this.context = context;
        this.onButtonClicked = onButtonClicked;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_share);
        lnfb = (LinearLayout) findViewById(R.id.ln_fb);
        lnis = (LinearLayout) findViewById(R.id.ln_is);
        lnfb.setOnClickListener(this);
        lnis.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ln_fb:
                dismiss();
                onButtonClicked.onShareFBClicked();
                break;

            case R.id.ln_is:
                dismiss();
                onButtonClicked.onShareISClicked();
                break;

            default:
                dismiss();
                break;
        }
    }

    public interface OnButtonClicked {
        void onShareFBClicked();

        void onShareISClicked();
    }
}
