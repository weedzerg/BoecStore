package com.blogspot.quanlytomatoads.fragment;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.Toast;

import com.blogspot.quanlytomatoads.R;
import com.blogspot.quanlytomatoads.adapter.AppKiemTienAdapter;
import com.blogspot.quanlytomatoads.common.Common;
import com.blogspot.quanlytomatoads.controller.ConvertPackage;
import com.blogspot.quanlytomatoads.model.TTappstore;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;


public class QLApp extends Fragment implements View.OnClickListener {

    private SearchView ser;

    private AppKiemTienAdapter adapter;
    private ListView lsview;
    private ArrayList<TTappstore> lspackage_store, lsdapheduyet, lschuapheduyet;
    private final String LINK_PART = "https://play.google.com/store/apps/details?id=";
    private FirebaseDatabase database;
    private ImageView imgsort;
    private LinearLayout lnapp;
    private ConvertPackage crt = new ConvertPackage();
    private FloatingActionButton actionA, actionB, actionC;
    private FloatingActionsMenu a_;
    HashMap<String, Object> id1 = new HashMap<String, Object>();

    public QLApp() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_qlapp, container, false);
        ser = (SearchView) view.findViewById(R.id.serview);
        lsview = (ListView) view.findViewById(R.id.lsviewapp);
        lnapp = (LinearLayout) view.findViewById(R.id.lnapp);
        actionA = (FloatingActionButton) view.findViewById(R.id.action_a);
        actionB = (FloatingActionButton) view.findViewById(R.id.action_b);
        actionC = (FloatingActionButton) view.findViewById(R.id.action_c);
        a_ = (FloatingActionsMenu) view.findViewById(R.id.multiple_actions);
        lspackage_store = new ArrayList<>();
        lsdapheduyet = new ArrayList<>();
        lschuapheduyet = new ArrayList<>();

        imgsort = (ImageView) view.findViewById(R.id.sortapp);
        adapter = new AppKiemTienAdapter(lspackage_store, getActivity());
        lsview.setAdapter(adapter);
        DatabaseReference refLoaddata = Common.data.child("listxetduyet");
        loaddata(refLoaddata);
        ser.setOnQueryTextListener(onTextQuery);
        lsview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TTappstore a = adapter.getItem(i);
                Uri uri = Uri.parse(LINK_PART + a.getNamepackage());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        imgsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sorttheothoigian();
            }
        });
        actionA.setOnClickListener(this);
        actionB.setOnClickListener(this);
        actionC.setOnClickListener(this);
        return view;
    }

    boolean check = true;

    public void sorttheothoigian() {
        Collections.sort(lspackage_store, new Comparator<TTappstore>() {
            @Override
            public int compare(TTappstore tTappstore, TTappstore t1) {
                return (int) (tTappstore.getTimestart() - t1.getTimestart());
            }
        });
        if (check) {
            imgsort.setImageResource(R.drawable.selector_sort_ascending);
            Collections.reverse(adapter.getLspackage());
            Toast.makeText(getActivity(), "Sắp xếp ứng dụng giảm dần theo thời gian", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Sắp xếp ứng dụng tăng dần theo thời gian", Toast.LENGTH_SHORT).show();
            imgsort.setImageResource(R.drawable.selector_sort_descending);
        }
        check = !check;
        adapter.notifyDataSetChanged();
    }

    private SearchView.OnQueryTextListener onTextQuery = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            if (TextUtils.isEmpty(newText)) {
                adapter.getFilter().filter("");
                lsview.clearTextFilter();
            } else {
                adapter.getFilter().filter(newText.toString());
            }
            return true;
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_a: {
                adapter.setLspackage(lsdapheduyet);
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Lọc các app đã được phê duyêt", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.action_b: {
                adapter.setLspackage(lschuapheduyet);
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Lọc các app đang chờ phê duyêt", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.action_c: {
                adapter.setLspackage(lspackage_store);
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Hiển thị tất cả app", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        a_.collapse();
    }

    public void loaddata(DatabaseReference reference) {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lspackage_store.clear();
                lschuapheduyet.clear();
                lsdapheduyet.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    TTappstore tTappstore = new TTappstore(ConvertPackage.packagetoValues(d.getKey()),
                            d.child("namedev").getValue(String.class), d.child("iddev").getValue(String.class),
                            d.child("ttpheduyet").getValue(Integer.class),
                            d.child("timexetduyet").getValue(Long.class));
                    lspackage_store.add(tTappstore);
                    if (tTappstore.getTrangthai() == 1) {
                        lschuapheduyet.add(tTappstore);
                    }
                    if (tTappstore.getTrangthai() == 2) {
                        lsdapheduyet.add(tTappstore);
                    }
                }

                adapter.notifyDataSetChanged();
                lnapp.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
