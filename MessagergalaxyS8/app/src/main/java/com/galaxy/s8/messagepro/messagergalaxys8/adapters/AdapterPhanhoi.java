package com.galaxy.s8.messagepro.messagergalaxys8.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.galaxy.s8.messagepro.messagergalaxys8.R;

/**
 * Created by DaiPhongPC on 9/28/2017.
 */

public class AdapterPhanhoi extends BaseAdapter {
    private String[] lsphanhoi;
    private LayoutInflater inflater;

    public AdapterPhanhoi(String[] lsphanhoi, LayoutInflater inflater) {
        this.lsphanhoi = lsphanhoi;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return lsphanhoi.length;
    }

    @Override
    public String getItem(int i) {
        return lsphanhoi[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    TextView txtview;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.item_phanhoi, null);
        }
        txtview = (TextView) view.findViewById(R.id.txt_nd);
        txtview.setText(getItem(i));
        return view;
    }
}
