package com.blogspot.quanlytomatoads.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.blogspot.quanlytomatoads.R;
import com.blogspot.quanlytomatoads.adapter.PaperAdapter;
import com.blogspot.quanlytomatoads.model.TTappstore;
import com.blogspot.quanlytomatoads.model.TTdev;

import java.util.ArrayList;


public class MainActivity_QL extends AppCompatActivity {
    ViewPager pager;
    TabLayout tabLayout;
    private ArrayList<TTappstore> lsapps;
    private ArrayList<TTdev> lsuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__ql);
        getSupportActionBar().hide();
        pager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        lsapps = new ArrayList<>();
        lsuser = new ArrayList<>();
        FragmentManager manager = getSupportFragmentManager();
        PaperAdapter adapter = new PaperAdapter(manager);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Back một lẫn nữa để kết thúc", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


}
