package com.galaxy.s8.messagepro.messagergalaxys8.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.galaxy.s8.messagepro.messagergalaxys8.R;
import com.galaxy.s8.messagepro.messagergalaxys8.activitys.NewSMSActivity;
import com.galaxy.s8.messagepro.messagergalaxys8.adapters.AdapterContact;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.InfoContact;
import com.galaxy.s8.messagepro.messagergalaxys8.objects.SenderSMS;
import com.galaxy.s8.messagepro.messagergalaxys8.utils.Cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import static com.galaxy.s8.messagepro.messagergalaxys8.utils.Contants.INTENT_GET_CONTACT;

public class ContactFragment extends Fragment implements AdapterView.OnItemClickListener, SearchView.OnQueryTextListener {
    private ListView lsview_contact;
    private AdapterContact adapterContact;
    private ArrayList<InfoContact> lscontact;
    private LayoutInflater inflater;
    private Cache cache;
    private HashMap<String, SenderSMS> lsHashMap = new HashMap<>();
    private SearchView searchView;
    private ImageView iv_creat;

    public SearchView getSearchView() {
        return searchView;
    }

    private ArrayList<InfoContact> lscontactadd;

    public void setSearchView(SearchView searchView) {
        this.searchView = searchView;
        this.searchView.setOnQueryTextListener(this);
    }

    //private
    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contact, container, false);
        this.inflater = inflater;
        init(v);
        return v;
    }

    public void init(View v) {
        lscontact = new ArrayList<>();
        lscontactadd = new ArrayList<>();
        cache = new Cache(getContext());
        lsview_contact = v.findViewById(R.id.lsview_contact);
        iv_creat = (ImageView) v.findViewById(R.id.iv_new_sms_contact);
        adapterContact = new AdapterContact(lscontact, inflater, cache);
        lsview_contact.setAdapter(adapterContact);
        lsview_contact.setOnItemClickListener(this);
        iv_creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewSMSActivity.class);
                intent.putExtra(INTENT_GET_CONTACT, lscontactadd);
                startActivity(intent);
            }
        });
    }

    public void dataContact(ArrayList<InfoContact> lsContact) {
        if (adapterContact != null) {
            this.lscontact = lsContact;
            Collections.sort(this.lscontact, new Comparator<InfoContact>() {
                @Override
                public int compare(InfoContact infoContact, InfoContact t1) {
                    return infoContact.getNameContact().compareToIgnoreCase(t1.getNameContact());
                }
            });
            adapterContact.setLsContact(this.lscontact);
            adapterContact.notifyDataSetChanged();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Intent intent = new Intent(getActivity(), BodySMSActivity.class);
//        String address = Utils.checkNumberPhone(adapterContact.getItem(i).getNumContact());
//        if (lsHashMap.containsKey(address)) {
//            intent.putExtra(Contants.BODY_SMS_ACTIVITY, lsHashMap.get(adapterContact.getItem(i).getNumContact()));
//        } else {
//            intent.putExtra(Contants.BODY_SMS_ACTIVITY, new SenderSMS(adapterContact.getItem(i).getNameContact(),
//                    adapterContact.getItem(i).getNumContact(), new ArrayList<BodySMS>()));
//
//        }
//        startActivity(intent);
        boolean c = adapterContact.getItem(i).isOnclick();
        if (!c) {
            lscontactadd.add(adapterContact.getItem(i));
        } else {
            lscontactadd.remove(adapterContact.getItem(i));
        }
        adapterContact.getItem(i).setOnclick(!c);
        adapterContact.notifyDataSetChanged();
    }

    public void dataSMSBody_(HashMap<String, SenderSMS> mapBody) {
        lsHashMap = mapBody;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            adapterContact.getFilter().filter("");
            lsview_contact.clearTextFilter();
        } else {
            adapterContact.getFilter().filter(newText.toString());
        }
        return true;
    }
}
