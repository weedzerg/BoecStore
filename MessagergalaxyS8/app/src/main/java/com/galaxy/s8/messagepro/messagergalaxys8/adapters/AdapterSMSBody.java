package com.galaxy.s8.messagepro.messagergalaxys8.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.galaxy.s8.messagepro.messagergalaxys8.R;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.BodySMS;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 9/27/2017.
 */

public class AdapterSMSBody extends BaseAdapter {
    private ArrayList<BodySMS> ls;
    private LayoutInflater inflater;
    private String uri;
    private String nameSend;

    public AdapterSMSBody(ArrayList<BodySMS> ls, LayoutInflater inflater, String uri, String nameSend) {
        this.ls = ls;
        this.inflater = inflater;
        this.uri = uri;
        this.nameSend = nameSend;
    }

    public ArrayList<BodySMS> getLs() {
        return ls;
    }

    public void setLs(ArrayList<BodySMS> ls) {
        this.ls = ls;
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public BodySMS getItem(int i) {
        return ls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private TextView tv_body_sender, tv_date_sender, tv_ava_send, tv_ava_me;
    private TextView tv_body_me, tv_date_me, tv_sms_fail;
    private LinearLayout ln_send, ln_me;
    private ImageView iv_ava_send, iv_ava_me;
    private ProgressBar pro_sms;


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.item_sms_body, null);
        }
        ln_send = (LinearLayout) view.findViewById(R.id.ln_send);
        ln_me = (LinearLayout) view.findViewById(R.id.ln_me);
        tv_body_sender = (TextView) view.findViewById(R.id.tv_body_send);
        tv_date_sender = (TextView) view.findViewById(R.id.tv_date_send);
        tv_body_me = (TextView) view.findViewById(R.id.tv_body_recie);
        tv_date_me = (TextView) view.findViewById(R.id.tv_date_recie);
        tv_sms_fail = (TextView) view.findViewById(R.id.tv_sms_fail);
        tv_ava_send = (TextView) view.findViewById(R.id.tv_ava_send);
        tv_ava_me = (TextView) view.findViewById(R.id.tv_ava_me);
        iv_ava_me = (ImageView) view.findViewById(R.id.iv_recei);
        iv_ava_send = (ImageView) view.findViewById(R.id.iv_send);
        pro_sms = (ProgressBar) view.findViewById(R.id.pro_sms_me);
        BodySMS bodySMS = getItem(i);
        switch (bodySMS.getType()) {
            case "1": {
                //nguoi gui

                ln_me.setVisibility(View.GONE);
                ln_send.setVisibility(View.VISIBLE);
                if (uri == null) {
                    iv_ava_send.setVisibility(View.VISIBLE);
                    tv_ava_send.setVisibility(View.GONE);
                    Picasso.with(view.getContext()).load(R.drawable.ic_user).into(iv_ava_send);
                } else if (uri.equals("true")) {
                    iv_ava_send.setVisibility(View.GONE);
                    tv_ava_send.setVisibility(View.VISIBLE);
                    tv_ava_send.setText(nameSend.substring(0, 1).toUpperCase());
                }  else {
                    iv_ava_send.setVisibility(View.VISIBLE);
                    tv_ava_send.setVisibility(View.GONE);
                    Picasso.with(view.getContext()).load(uri).into(iv_ava_send);
                }
                tv_body_sender.setText(bodySMS.getBody());
                tv_date_sender.setText(Utils.convertDate(bodySMS.getDate()));

                break;
            }
            case "2": {
//                nguoi nhan
                ln_send.setVisibility(View.GONE);
                ln_me.setVisibility(View.VISIBLE);
                iv_ava_me.setVisibility(View.GONE);
                tv_ava_me.setText("M");
                tv_body_me.setText(bodySMS.getBody());
                tv_date_me.setText(Utils.convertDate(bodySMS.getDate()));
                switch (bodySMS.getChecksent()) {
                    case 1:
                        tv_sms_fail.setVisibility(View.GONE);
                        pro_sms.setVisibility(View.GONE);
                        break;
                    case 2:
                        tv_sms_fail.setVisibility(View.GONE);
                        pro_sms.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        tv_sms_fail.setVisibility(View.VISIBLE);
                        pro_sms.setVisibility(View.GONE);
                        break;
                }
                break;
            }
        }
        return view;
    }
}
