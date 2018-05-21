package com.lx.ltuddd.boecstore.client.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.adapters.PaperAdapter;
import com.lx.ltuddd.boecstore.client.fragments.BooksFragment;
import com.lx.ltuddd.boecstore.client.fragments.ClothesFragment;
import com.lx.ltuddd.boecstore.client.fragments.ElectronicsFragment;
import com.lx.ltuddd.boecstore.client.fragments.HomeFragment;

import java.util.ArrayList;

public class ItemBoecActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener {
    private TabLayout tablayout;
    private ViewPager viewPager;
    private ImageView iv_slideNav;
    private EditText ed_search;
    private ArrayList<Fragment> lsFragment;
    private PaperAdapter paperAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_boec);
        init();

    }

    public void init() {
        ed_search=(EditText) findViewById(R.id.ed_search);
        iv_slideNav = (ImageView) findViewById(R.id.iv_nav_menu);
        iv_slideNav.setOnClickListener(this);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initPaper();
    }

    public void initPaper() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.paper);
        lsFragment = new ArrayList<>();
        lsFragment.add(new HomeFragment());
        lsFragment.add(new BooksFragment());
        lsFragment.add(new ElectronicsFragment());
        lsFragment.add(new ClothesFragment());
        paperAdapter = new PaperAdapter(getSupportFragmentManager(), lsFragment);
        viewPager.setAdapter(paperAdapter);
        tablayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.setTabsFromPagerAdapter(paperAdapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_fav:
                break;
            case R.id.nav_tran:
                break;
            case R.id.nav_noti:
                break;
            case R.id.nav_acc:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_nav_menu:
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
                break;
        }
    }
}
