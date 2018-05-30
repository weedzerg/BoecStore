package com.galaxy.s8.messagepro.messagergalaxys8.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.galaxy.s8.messagepro.messagergalaxys8.R;
import com.galaxy.s8.messagepro.messagergalaxys8.activitys.BodySMSActivity;
import com.galaxy.s8.messagepro.messagergalaxys8.adapters.AdapterSMS;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.BodySMS;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.InfoSender;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.SenderSMS;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Cache;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Contants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class SMSFragment extends Fragment implements AdapterView.OnItemClickListener, SearchView.OnQueryTextListener {
    private HashMap<String, SenderSMS> hashSender;
    private ArrayList<InfoSender> lsSender;
    private AdapterSMS adapterSMS;
    private ListView lsview;
    private boolean check_ = false;
    private LayoutInflater inflater;
    private Cache cache;
    private ProgressBar pro;
    private SearchView searchView;

    public SearchView getSearchView() {
        return searchView;
    }

    public void setSearchView(SearchView searchView) {
        this.searchView = searchView;
        this.searchView.setOnQueryTextListener(this);
    }

    public SMSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sm, container, false);
        this.inflater = inflater;
        init(v);
        return v;
    }

    public void init(View v) {
        lsview = (ListView) v.findViewById(R.id.lsview);
        pro = (ProgressBar) v.findViewById(R.id.pro_wait);
        pro.setVisibility(View.VISIBLE);
        hashSender = new HashMap<>();
        lsSender = new ArrayList<>();
        cache = new Cache(getContext());
        adapterSMS = new AdapterSMS(this.lsSender, inflater, cache);
        lsview.setAdapter(adapterSMS);
        lsview.setOnItemClickListener(this);

    }

    public void dataSMS(ArrayList<InfoSender> lsSender, boolean check) {
        try {
            this.lsSender.clear();
            if (adapterSMS != null) {
                this.lsSender = lsSender;
                Collections.sort(this.lsSender, new Comparator<InfoSender>() {
                    @Override
                    public int compare(InfoSender infoSender, InfoSender t1) {
                        return infoSender.getDate().compareTo(t1.getDate());
                    }
                });
                Collections.reverse(this.lsSender);
                adapterSMS.setLs(this.lsSender);
                adapterSMS.notifyDataSetChanged();
                check_ = check;
                Log.d("FRAGMENT", check + "=" + adapterSMS.getCount());
                pro.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDataFromSMSReciever(ArrayList<InfoSender> ls) {
        for (int i = 0; i < lsSender.size(); i++) {
            for (InfoSender infoSender1 : ls) {
                if (lsSender.get(i).getNumberSender().equals(infoSender1.getNumberSender())) {
                    lsSender.get(i).setBody(infoSender1.getBody());
                    lsSender.get(i).setDate(infoSender1.getDate());
                    if (hashSender.containsKey(infoSender1.getNumberSender())) {
                        SenderSMS lsbody = hashSender.get(infoSender1.getNumberSender());
                        lsbody.getLsbody().add(0, new BodySMS(infoSender1.getBody(), infoSender1.getDate(), "1", 1));
                        hashSender.put(infoSender1.getNumberSender(), lsbody);
                    }
                }
            }
        }
        dataSMS(lsSender, true);
    }

    public void dataSMSBody(HashMap<String, SenderSMS> mapBody) {
        hashSender = mapBody;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (check_) {
            Intent intent = new Intent(getActivity(), BodySMSActivity.class);
            intent.putExtra(Contants.BODY_SMS_ACTIVITY, hashSender.get(adapterSMS.getItem(i).getNumberSender()));
            startActivity(intent);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            adapterSMS.getFilter().filter("");
            lsview.clearTextFilter();
        } else {
            adapterSMS.getFilter().filter(newText.toString());
        }
        return true;
    }
}
