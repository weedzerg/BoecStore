package com.funnystudio.whistleaction.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.funnystudio.whistleaction.R;
import com.funnystudio.whistleaction.model.ObjectMusic;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 8/12/2017.
 */

public class AdapterListMp3 extends BaseAdapter {
    private ArrayList<ObjectMusic> lsmp3;
    private LayoutInflater inflater;

    public AdapterListMp3(ArrayList<ObjectMusic> lsmp3, LayoutInflater inflater) {
        this.lsmp3 = lsmp3;
        this.inflater = inflater;
    }


    @Override
    public int getCount() {
        return lsmp3.size();
    }

    @Override
    public ObjectMusic getItem(int i) {
        return lsmp3.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public ArrayList<ObjectMusic> getLsmp3() {
        return lsmp3;
    }

    public void setLsmp3(ArrayList<ObjectMusic> lsmp3) {
        this.lsmp3 = lsmp3;
    }

    private ImageView imgcheck;
    private TextView txtname;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.itemmusic, null);
        }
        imgcheck = (ImageView) view.findViewById(R.id.checkoptopm);
        txtname = (TextView) view.findViewById(R.id.txtnamemp3);
        ObjectMusic ObjectMusic = getItem(i);
        if (ObjectMusic.isCheck()) {
            imgcheck.setImageResource(R.drawable.check);
        } else {
            imgcheck.setImageResource(R.drawable.uncheck);
        }
        txtname.setText(ObjectMusic.getNamemusic());

        return view;
    }
}
