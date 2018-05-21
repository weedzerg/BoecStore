package com.lx.ltuddd.boecstore.client.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.adapters.AdapterClothes;
import com.lx.ltuddd.boecstore.client.objects.Clothes;

import java.util.ArrayList;

public class ClothesFragment extends Fragment {
    private ArrayList<Clothes> ls = new ArrayList<>();
    RecyclerView rc_view;
    private AdapterClothes adapterClothes;
    public ClothesFragment() {
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
        adapterClothes = new AdapterClothes(getContext(), ls);
        rc_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rc_view.setAdapter(adapterClothes);
        adapterClothes.notifyDataSetChanged();

    }
}
