package com.blogspot.quanlytomatoads.fragment;

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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.quanlytomatoads.DetailActivity;
import com.blogspot.quanlytomatoads.R;
import com.blogspot.quanlytomatoads.adapter.AdapterDev;
import com.blogspot.quanlytomatoads.common.Common;
import com.blogspot.quanlytomatoads.controller.ConvertPackage;
import com.blogspot.quanlytomatoads.model.TTMypackage;
import com.blogspot.quanlytomatoads.model.TTdev;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class QLUser extends Fragment {
    private ListView lsview;
    private ArrayList<TTdev> lsuser;
    private AdapterDev adapterDev;
    private SearchView ser;
    private ImageView sortuser;
    private LinearLayout lnuser;
    private TextView txttong;

    public QLUser() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qluser, container, false);

        ser = (SearchView) view.findViewById(R.id.serviewuser);
        lsview = (ListView) view.findViewById(R.id.lsviewuser);
        sortuser = (ImageView) view.findViewById(R.id.sortuser);
        lnuser = (LinearLayout) view.findViewById(R.id.lnuser);
        txttong = (TextView) view.findViewById(R.id.tonguser);
        lsuser = new ArrayList<>();
        adapterDev = new AdapterDev(lsuser, getActivity());
        lsview.setAdapter(adapterDev);
        loaddatauser(Common.data.child("user"));
        lsview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("data", adapterDev.getItem(i));
                getActivity().startActivity(intent);

            }
        });
        ser.setOnQueryTextListener(onTextQuery);
        sortuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sorttheodiem();
            }
        });
        return view;
    }

    boolean check = true;

    public void sorttheodiem() {
        Collections.sort(lsuser, new Comparator<TTdev>() {
            @Override
            public int compare(TTdev tTdev, TTdev t1) {
                return tTdev.getDiem() - t1.getDiem();
            }
        });
        if (check) {
            sortuser.setImageResource(R.drawable.selector_sort_ascending);
            Collections.reverse(adapterDev.getLs());
            Toast.makeText(getActivity(), "Sắp xếp user giảm dần theo điểm", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Sắp xếp user tăng dần theo điểm", Toast.LENGTH_SHORT).show();
            sortuser.setImageResource(R.drawable.selector_sort_descending);
        }
        check = !check;
        adapterDev.notifyDataSetChanged();
    }

    private SearchView.OnQueryTextListener onTextQuery = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            if (TextUtils.isEmpty(newText)) {
                adapterDev.getFilter().filter("");
                lsview.clearTextFilter();
            } else {
                adapterDev.getFilter().filter(newText.toString());
            }
            return true;
        }
    };

    public void loaddatauser(DatabaseReference reference) {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lsuser.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    TTdev tTdev = new TTdev(snapshot.child("/infoUser/name").getValue(String.class),
                            snapshot.getKey(),
                            snapshot.child("/infoUser/linkavatar").getValue(String.class),
                            snapshot.child("/infoUser/loaiacount").getValue(String.class),
                            snapshot.child("/infoUser/diem").getValue(Integer.class));
                    tTdev.setMypackage(getmypackage(snapshot.child("mypackage")));
                    lsuser.add(tTdev);
                }
                adapterDev.notifyDataSetChanged();
                txttong.setText("Tổng user: " + adapterDev.getCount());
                lnuser.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public ArrayList<TTMypackage> getmypackage(DataSnapshot snapshot) {
        ArrayList<TTMypackage> ls = new ArrayList<>();
        for (DataSnapshot d : snapshot.getChildren()) {
            TTMypackage ttMypackage = new TTMypackage(ConvertPackage.packagetoValues(d.getKey()),
                    d.child("countdown").getValue(Integer.class),
                    d.child("ttpheduyet").getValue(Integer.class));
            ls.add(ttMypackage);
        }
        return ls;
    }
}
