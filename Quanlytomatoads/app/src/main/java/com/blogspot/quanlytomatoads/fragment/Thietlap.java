package com.blogspot.quanlytomatoads.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.blogspot.quanlytomatoads.R;
import com.blogspot.quanlytomatoads.view.MainActivity_Login;
import com.google.firebase.auth.FirebaseAuth;


public class Thietlap extends Fragment {

    public Thietlap() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thietlap, container, false);
        Button daangxuat = (Button) view.findViewById(R.id.logout);
        daangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), MainActivity_Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }


}
