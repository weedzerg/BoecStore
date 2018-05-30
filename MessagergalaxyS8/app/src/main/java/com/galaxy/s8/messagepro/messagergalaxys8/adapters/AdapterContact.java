package com.galaxy.s8.messagepro.messagergalaxys8.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.galaxy.s8.messagepro.messagergalaxys8.R;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.InfoContact;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.ActionSMS;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Cache;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 9/30/2017.
 */

public class AdapterContact extends BaseAdapter implements Filterable {
    private ArrayList<InfoContact> lsContact;
    private ArrayList<InfoContact> lsContact_;
    private LayoutInflater inflater;
    private Cache cache;

    public AdapterContact(ArrayList<InfoContact> lsContact, LayoutInflater inflater, Cache cache) {
        this.lsContact = lsContact;
        this.inflater = inflater;
        this.cache = cache;
        this.lsContact_=lsContact;
    }

    public ArrayList<InfoContact> getLsContact() {
        return lsContact;
    }

    public void setLsContact(ArrayList<InfoContact> lsContact) {
        this.lsContact = lsContact;
        this.lsContact_ = lsContact;
    }

    @Override
    public int getCount() {
        return lsContact.size();
    }

    @Override
    public InfoContact getItem(int i) {
        return lsContact.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    TextView tv_name_contact;
    ImageView iv_contact;
    TextView tv_contact;
    ImageView iv_check;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.item_contact, null);
        }
        tv_name_contact = (TextView) view.findViewById(R.id.tv_name_contact);
        tv_contact = (TextView) view.findViewById(R.id.tv_ava_contact);
        iv_contact = (ImageView) view.findViewById(R.id.iv_avatar_sms);
        iv_check = (ImageView) view.findViewById(R.id.iv_check);
        tv_contact.setVisibility(View.GONE);
        iv_contact.setVisibility(View.VISIBLE);
        tv_name_contact.setText(getItem(i).getNameContact());
        String uri = cache.getLink(getItem(i).getNumContact());
        if (uri == null) {
            new LoadPhotoUriContact(iv_contact, tv_contact, view.getContext(), getItem(i)).execute();
        } else {
            if (uri.equals("true")) {
                tv_contact.setVisibility(View.VISIBLE);
                iv_contact.setVisibility(View.GONE);
                tv_contact.setText(getItem(i).getNameContact().substring(0, 1));
            } else {
                tv_contact.setVisibility(View.GONE);
                iv_contact.setVisibility(View.VISIBLE);
                Picasso.with(view.getContext()).load(Uri.parse(uri)).into(iv_contact);
            }
        }
        iv_check.setVisibility(View.VISIBLE);
        if (getItem(i).isOnclick()) {
            iv_check.setImageResource(R.drawable.ic_tick);
        } else {
            iv_check.setImageResource(R.drawable.ic_no_tick);
        }
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
                ArrayList<InfoContact> filterList = new ArrayList();
                for (int i = 0; i < lsContact_.size(); i++) {
                    InfoContact a = lsContact_.get(i);
                    if ((a.getNameContact().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(a);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = lsContact_.size();
                results.values = lsContact_;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            lsContact = (ArrayList<InfoContact>) results.values;
            notifyDataSetChanged();
        }

    }
    public class LoadPhotoUriContact extends AsyncTask<Void, Void, Uri> {
        private ImageView iv_avatar_contact;
        private TextView tv_contact;
        private Context context;
        private InfoContact contact;

        public LoadPhotoUriContact(ImageView iv_avatar_contact, TextView tv_contact,
                                   Context context, InfoContact contact) {
            this.iv_avatar_contact = iv_avatar_contact;
            this.tv_contact = tv_contact;
            this.context = context;
            this.contact = contact;
        }

        @Override
        protected Uri doInBackground(Void... voids) {

            return ActionSMS.getPhotoUri(context, contact.getIdContact());
        }

        @Override
        protected void onPostExecute(Uri uri) {
//            iv_avatar_contact.setImageURI(uri);
            if (uri == null) {
                this.tv_contact.setVisibility(View.VISIBLE);
                this.iv_avatar_contact.setVisibility(View.GONE);
                tv_contact.setText(contact.getNameContact().substring(0, 1));
                cache.putLink(contact.getNumContact(), "true");
            } else {
                cache.putLink(contact.getNumContact(), uri.toString());
                this.tv_contact.setVisibility(View.GONE);
                this.iv_avatar_contact.setVisibility(View.VISIBLE);
                Picasso.with(context).load(uri).into(iv_avatar_contact);
            }
            this.cancel(true);
        }
    }
}
