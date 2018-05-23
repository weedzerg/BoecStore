package com.lx.ltuddd.boecstore.client.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.adapters.PaperAdapter;
import com.lx.ltuddd.boecstore.client.fragments.BooksFragment;
import com.lx.ltuddd.boecstore.client.fragments.ClothesFragment;
import com.lx.ltuddd.boecstore.client.fragments.ElectronicsFragment;
import com.lx.ltuddd.boecstore.client.fragments.HomeFragment;
import com.lx.ltuddd.boecstore.client.objects.Address;
import com.lx.ltuddd.boecstore.client.objects.Customer;
import com.lx.ltuddd.boecstore.client.objects.FullName;
import com.lx.ltuddd.boecstore.client.utils.Contants;
import com.lx.ltuddd.boecstore.client.utils.Utils;

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
    private DatabaseReference refUser;
    private Customer customer;

    private ImageView iv_avatar;
    private TextView tv_name, tv_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_boec);
        refUser = FirebaseDatabase.getInstance().getReference().child("person").child(Contants.user.getUid());
        customer = new Customer();
        init();
        checkInformationCustomer();
    }

    public void init() {

        ed_search = (EditText) findViewById(R.id.ed_search);
        iv_slideNav = (ImageView) findViewById(R.id.iv_nav_menu);
        iv_slideNav.setOnClickListener(this);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View v = navigationView.getHeaderView(0);
        iv_avatar = (ImageView) v.findViewById(R.id.iv_avatar);
        tv_name = (TextView) v.findViewById(R.id.tv_name);
        tv_email = (TextView) v.findViewById(R.id.tv_email);
        initPaper();
        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String key = ed_search.getText().toString();
                int curent = viewPager.getCurrentItem();
                if (curent == 1) {
                    ((BooksFragment) paperAdapter.getItem(curent)).onLoadSearchView(key);
                } else if (curent == 2) {
                    ((ElectronicsFragment) paperAdapter.getItem(curent)).onLoadSearchView(key);
                }
                if (curent == 3) {
                    ((ClothesFragment) paperAdapter.getItem(curent)).onLoadSearchView(key);
                }
            }
        });
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
            case R.id.nav_cart:
                Intent cart = new Intent(ItemBoecActivity.this, CartOrderActivity.class);
                startActivity(cart);
                break;
            case R.id.nav_acc:
                Intent acc = new Intent(ItemBoecActivity.this, CartOrderActivity.class);
                acc.putExtra(Contants.DATAINTENT, customer);
                startActivity(acc);
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                finish();
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

    public void checkInformationCustomer() {
        refUser.keepSynced(true);
        refUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    customer.setId(dataSnapshot.child("id").getValue().toString());
                    customer.setEmail(dataSnapshot.child("email").getValue().toString());
                    try {
                        customer.setNumber(dataSnapshot.child("phoneNumber").getValue().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        customer.setSex(dataSnapshot.child("sex").getValue(String.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    customer.setUrlImage(dataSnapshot.child("urlImage").getValue(String.class));
                    try {
                        customer.setAddress(new Address(dataSnapshot.child("address/numberHouse").getValue(String.class),
                                dataSnapshot.child("address/street").getValue().toString(),
                                dataSnapshot.child("address/city").getValue().toString(),
                                dataSnapshot.child("address/district").getValue().toString()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    customer.setFullName(new FullName(dataSnapshot.child("fullname/firstName").getValue(String.class),
                            dataSnapshot.child("fullname/lastName").getValue().toString(),
                            dataSnapshot.child("fullname/midName").getValue().toString()));
                    setUpInf();

                } else {
                    customer.setId(Contants.user.getUid());
                    customer.setUrlImage(Contants.user.getPhotoUrl().toString());
                    customer.setEmail(Contants.user.getEmail());
                    customer.setFullName(new FullName(Contants.user.getDisplayName(), "", ""));
                    customer.setType("nomarl");
                    Utils.upInfCustomer(refUser, customer);
                    FirebaseDatabase.getInstance().getReference().child("customer/" +
                            Contants.user.getUid() + "/type").setValue(customer.getType());
                    setUpInf();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void setUpInf() {
        Glide.with(getBaseContext()).load(customer.getUrlImage()).override(200, 200).into(iv_avatar);
        tv_name.setText(customer.getFullName().getFirstName());
        tv_email.setText(customer.getEmail());
    }
}
