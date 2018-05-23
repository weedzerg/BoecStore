package com.lx.ltuddd.boecstore.client.admin;

import android.app.ActivityGroup;
import android.app.TabActivity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;

import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.adapters.PaperAdapter;
import com.lx.ltuddd.boecstore.client.fragments.BooksFragment;
import com.lx.ltuddd.boecstore.client.fragments.ClothesFragment;
import com.lx.ltuddd.boecstore.client.fragments.ElectronicsFragment;
import com.lx.ltuddd.boecstore.client.fragments.HomeFragment;

import java.util.ArrayList;

public class AdminActivity extends ActivityGroup {

    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        tabHost = findViewById(R.id.tabhost);
        tabHost.setup(this.getLocalActivityManager());

        TabHost.TabSpec book = tabHost.newTabSpec("Book");
        book.setIndicator("Book");
        book.setContent(new Intent(AdminActivity.this, AdminBookActivity.class));

        TabHost.TabSpec electronic = tabHost.newTabSpec("Electronic");
        electronic.setIndicator("Electronic");
        electronic.setContent(new Intent(AdminActivity.this, AdminElectronicActivity.class));

        TabHost.TabSpec clothes = tabHost.newTabSpec("Clothes");
        clothes.setIndicator("Clothes");
        clothes.setContent(new Intent(AdminActivity.this, AdminClothesActivity.class));

        tabHost.addTab(book);
        tabHost.addTab(electronic);
        tabHost.addTab(clothes);

        int tab = getIntent().getIntExtra("tab", 0);
        tabHost.setCurrentTab(tab);
    }

}
