package com.lx.ltuddd.boecstore.admin;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.lx.ltuddd.boecstore.R;

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
