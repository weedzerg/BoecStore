package com.blogspot.quanlytomatoads.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.quanlytomatoads.R;
import com.blogspot.quanlytomatoads.model.TTdev;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by DaiPhongPC on 5/30/2017.
 */

public class AdapterDev extends BaseAdapter implements Filterable {
    private ArrayList<TTdev> ls;
    private ArrayList<TTdev> ls1;
    private Activity activity;

    public AdapterDev(ArrayList<TTdev> ls, Activity activity) {
        this.ls = ls;
        this.activity = activity;
        this.ls1 = ls;
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public TTdev getItem(int i) {
        return ls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    ImageView img;
    TextView txt, diem, txtloai;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.itemdev, null);

        }
        diem = (TextView) view.findViewById(R.id.diem);
        img = (ImageView) view.findViewById(R.id.imgava);
        txt = (TextView) view.findViewById(R.id.namedev);
        txtloai = (TextView) view.findViewById(R.id.loaiacount);
        TTdev a = ls.get(i);
        loadimgavatar(a, img, txt, diem);
        return view;
    }

    public ArrayList<TTdev> getLs() {
        return ls;
    }

    public void setLs(ArrayList<TTdev> ls) {
        this.ls = ls;
    }


    public void loadimgavatar(final TTdev a, final ImageView img, final TextView txt, final TextView diem) {
        Picasso.with(activity).load(a.getLinkavatar()).into(img);
        txt.setText(a.getName());
        diem.setText(a.getDiem() + "");
        if (a.getLoaicount().equals("[google.com]")) {
            txtloai.setTextColor(Color.RED);
            txtloai.setText("Google");
        } else {
            txtloai.setTextColor(Color.parseColor("#125688"));
            txtloai.setText("Facebook");
        }

    }

    ValueFilter valueFilter;

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<TTdev> filterList = new ArrayList();

                for (int i = 0; i < ls1.size(); i++) {
                    TTdev a = ls1.get(i);
                    if ((a.getName().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(a);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = ls1.size();
                results.values = ls1;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            ls = (ArrayList<TTdev>) results.values;
            notifyDataSetChanged();
        }

    }
}
