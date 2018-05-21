package com.lx.ltuddd.boecstore.client.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.adapters.AdapterElectronics;
import com.lx.ltuddd.boecstore.client.objects.Electronics;

import java.util.ArrayList;

public class ElectronicsFragment extends Fragment {
    private ArrayList<Electronics> ls = new ArrayList<>();
    RecyclerView rc_view;
    private AdapterElectronics adapterElectronics;
    public ElectronicsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_item, container, false);
        init(v);
        return v;
    }

    public void init(View v) {

        rc_view = (RecyclerView) v.findViewById(R.id.rc_items);
        adapterElectronics = new AdapterElectronics(getContext(), ls);
        rc_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rc_view.setAdapter(adapterElectronics);
        adapterElectronics.notifyDataSetChanged();

    }

}
