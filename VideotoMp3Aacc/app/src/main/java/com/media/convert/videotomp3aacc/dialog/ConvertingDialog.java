package com.media.convert.videotomp3aacc.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.media.convert.videotomp3aacc.R;


/**
 * Created by binhnk on 7/25/2017.
 */

public class ConvertingDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private TextView tv_content, tv_runbackground;
    private OnButtonClicked onButtonClicked;
    private ArcProgress acrProgress;

    public ConvertingDialog(@NonNull Context context, OnButtonClicked onButtonClicked) {
        super(context);
        this.context = context;
        this.onButtonClicked = onButtonClicked;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.diaglog_proressbar_);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_runbackground = (TextView) findViewById(R.id.tv_runbackground);
        tv_runbackground.setOnClickListener(this);
        tv_content.setText(context.getResources().getString(R.string.conveting));
        acrProgress = (ArcProgress) findViewById(R.id.arc_progress);

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

    public void updatePro(String stype, int pt) {
        if (pt <= 100) {
            acrProgress.setBottomText(stype);
            acrProgress.setProgress(pt);
        }
    }

    public interface OnButtonClicked {
        void onRunClicked();

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
