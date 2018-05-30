package com.galaxy.s8.messagepro.messagergalaxys8.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.galaxy.s8.messagepro.messagergalaxys8.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 10/6/2017.
 */

public class AdapterThem extends BaseAdapter {
    private ArrayList<Integer> ls;
    private LayoutInflater inflater;
    private int choice;

    public AdapterThem(ArrayList<Integer> ls, LayoutInflater inflater, int choice) {
        this.ls = ls;
        this.inflater = inflater;
        this.choice = choice;
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public Integer getItem(int i) {
        return ls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    ImageView iv_view;
    LinearLayout ln_choice;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.item_them, null);
        }
        iv_view = (ImageView) view.findViewById(R.id.iv_them);
        ln_choice = (LinearLayout) view.findViewById(R.id.ln_check);
        if (getItem(i) == choice) {
            ln_choice.setVisibility(View.VISIBLE);
        } else {
            ln_choice.setVisibility(View.GONE);
        }
        Picasso.with(view.getContext()).load(getItem(i)).resize(200, 355).into(iv_view);
        return view;
    }
}
