package com.media.vid.video_speed.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.media.vid.video_speed.R;


/**
 * Created by binhnk on 7/25/2017.
 */

public class ProressDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private TextView tv_content, tv_runbackground;
    private OnButtonClicked onButtonClicked;
    private boolean check = false;
    private ProgressBar pb1, pb2;

    public ProressDialog(@NonNull Context context, OnButtonClicked onButtonClicked, boolean check) {
        super(context);
        this.context = context;
        this.onButtonClicked = onButtonClicked;
        this.check = check;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.diaglog_proressbar);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_runbackground = (TextView) findViewById(R.id.tv_runbackground);
        pb1 = (ProgressBar) findViewById(R.id.pb1);
        pb2 = (ProgressBar) findViewById(R.id.pb2);
        if (!check) {
            pb1.setVisibility(View.VISIBLE);
            tv_runbackground.setVisibility(View.GONE);
            pb2.setVisibility(View.GONE);
        } else {
            pb1.setVisibility(View.GONE);
            tv_runbackground.setVisibility(View.GONE);
            pb2.setVisibility(View.VISIBLE);
        }
        tv_runbackground.setOnClickListener(this);
        tv_content.setText(context.getString(R.string.wait_for_processing));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_runbackground:
                dismiss();
                onButtonClicked.onRunClicked();
                break;

            default:
                dismiss();
                break;
        }
    }

    public void updatePro(int pt) {
        if (pt > 100) {
            pt = 100;
        }
        tv_content.setText(getContext().getString(R.string.convert_to) + "  " + pt + "%");
        pb2.setProgress(pt);
    }

    public interface OnButtonClicked {
        void onRunClicked();

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
