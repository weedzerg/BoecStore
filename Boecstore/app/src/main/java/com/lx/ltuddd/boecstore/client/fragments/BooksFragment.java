package com.lx.ltuddd.boecstore.client.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.adapters.AdapterBook;
import com.lx.ltuddd.boecstore.client.objects.Book;

import java.util.ArrayList;

public class BooksFragment extends Fragment {
    private ArrayList<Book> ls = new ArrayList<>();
    RecyclerView rc_view;
    private AdapterBook adapterBook;

    public BooksFragment() {
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
        ls.add(new Book(1 + "", "tony buoi sang", 50000, 0.1f,
                "sach", "", "sach triet ly", 2001, "Tony"));
        ls.add(new Book(1 + "", "tony buoi sang1", 80000, 0.1f,
                "sach", "", "sach triet ly", 2001, "Tony"));
        ls.add(new Book(1 + "", "tony buoi sang2", 90000, 0.1f,
                "sach", "", "sach triet ly", 2001, "Tony"));
        rc_view = (RecyclerView) v.findViewById(R.id.rc_items);
        adapterBook = new AdapterBook(getContext(), ls);
        rc_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rc_view.setAdapter(adapterBook);
        adapterBook.notifyDataSetChanged();

    }
}
