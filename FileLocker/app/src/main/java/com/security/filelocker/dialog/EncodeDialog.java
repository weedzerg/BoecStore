package com.security.filelocker.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.security.filelocker.R;


/**
 * Created by binhnk on 7/25/2017.
 */

public class EncodeDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private OnButtonClicked onButtonClicked;
    private boolean check;
    private TextView tv_ok, tv_cancel, tv_title;

    public EncodeDialog(@NonNull Context context, OnButtonClicked onButtonClicked, boolean check) {
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
        setContentView(R.layout.dialog_encodefile);
        tv_ok = (TextView) findViewById(R.id.tv_ok);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_title = (TextView) findViewById(R.id.tv_title_diaglog);
        if (check) {
            tv_title.setText(context.getString(R.string.b_n_ng_b_o_m_t));
        } else {
            tv_title.setText(context.getString(R.string.decode_file));
        }
        tv_ok.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                tv_cancel.setTextColor(Color.parseColor("#00a697"));
                dismiss();
                onButtonClicked.onCancelClicked();
                break;

            case R.id.tv_ok:
                tv_ok.setTextColor(Color.parseColor("#00a697"));
                dismiss();
                onButtonClicked.onOKClicked();
                break;

            default:
                dismiss();
                break;
        }
    }

    public interface OnButtonClicked {
        void onOKClicked();

        void onCancelClicked();
    }
}
