package com.galaxy.s8.messagepro.messagergalaxys8.adapters;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.galaxy.s8.messagepro.messagergalaxys8.R;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.InfoSender;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Cache;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 9/26/2017.
 */

public class AdapterSMS extends BaseAdapter implements Filterable {
    private ArrayList<InfoSender> ls;
    private ArrayList<InfoSender> lscache;
    private LayoutInflater inflater;
    private Cache cache;

    public AdapterSMS(ArrayList<InfoSender> ls, LayoutInflater inflater, Cache cache) {
        this.ls = ls;
        this.inflater = inflater;
        this.cache = cache;
        this.lscache = ls;
    }

    public ArrayList<InfoSender> getLs() {
        return ls;
    }

    public void setLs(ArrayList<InfoSender> ls) {
        this.ls = ls;
        this.lscache = ls;
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public InfoSender getItem(int i) {
        return ls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    TextView tv_name, tv_body, tv_date, tv_avatar;
    ImageView iv_ava_sms;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.item_sms, null);
            Log.d("LISTVIEW", "created " + i);
        }
        tv_avatar = (TextView) view.findViewById(R.id.txt_id_sender);
        tv_name = (TextView) view.findViewById(R.id.tv_name_contact);
        tv_body = (TextView) view.findViewById(R.id.tv_detail);
        tv_date = (TextView) view.findViewById(R.id.tv_date);
        iv_ava_sms = (ImageView) view.findViewById(R.id.iv_avatar_sms);
        InfoSender sms = getItem(i);
        String uri = cache.getLink(sms.getNumberSender());
        if (uri == null) {
            iv_ava_sms.setVisibility(View.VISIBLE);
            tv_avatar.setVisibility(View.GONE);
            Picasso.with(view.getContext()).load(R.drawable.ic_user).into(iv_ava_sms);
        } else if (uri.equals("true")) {
            iv_ava_sms.setVisibility(View.GONE);
            tv_avatar.setVisibility(View.VISIBLE);
            tv_avatar.setText(sms.getNameSender().substring(0, 1).toUpperCase());
        } else {
            iv_ava_sms.setVisibility(View.VISIBLE);
            tv_avatar.setVisibility(View.GONE);
            Picasso.with(view.getContext()).load(Uri.parse(uri)).into(iv_ava_sms);
        }

        tv_name.setText(sms.getNameSender());
        tv_body.setText(sms.getBody());
        tv_date.setText(Utils.convertDate(sms.getDate()));
        return view;
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
                ArrayList<InfoSender> filterList = new ArrayList();
                for (int i = 0; i < lscache.size(); i++) {
                    InfoSender a = lscache.get(i);
                    if ((a.getNameSender().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(a);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = lscache.size();
                results.values = lscache;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            ls = (ArrayList<InfoSender>) results.values;
            notifyDataSetChanged();
        }

    }
}
