package com.media.convert.videotomp3aacc.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.media.convert.videotomp3aacc.R;


/**
 * Created by binhnk on 7/25/2017.
 */

public class PlayAudioDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private OnButtonClicked onButtonClicked;

    private TextView tv_ok, tv_cancel;
    private ImageView iv_music;

    public PlayAudioDialog(@NonNull Context context, OnButtonClicked onButtonClicked) {
        super(context);
        this.context = context;
        this.onButtonClicked = onButtonClicked;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.layout_play_audio);
        iv_music = (ImageView) findViewById(R.id.gif_);
        tv_ok = (TextView) findViewById(R.id.tv_ok);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        Glide.with(context).asGif().load(R.drawable.giphy).into(iv_music);
        tv_ok.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                onButtonClicked.onCancelClicked();
                break;

            case R.id.tv_ok:
                dismiss();
                onButtonClicked.onOkClicked();
                break;

            default:
                dismiss();
                break;
        }
    }

    public interface OnButtonClicked {
        void onOkClicked();

        void onCancelClicked();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
