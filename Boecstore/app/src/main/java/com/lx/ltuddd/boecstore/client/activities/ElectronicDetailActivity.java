package com.lx.ltuddd.boecstore.client.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.objects.Cart;
import com.lx.ltuddd.boecstore.client.objects.Electronics;
import com.lx.ltuddd.boecstore.client.objects.Item;
import com.lx.ltuddd.boecstore.client.objects.Order;
import com.lx.ltuddd.boecstore.client.utils.Contants;

public class ElectronicDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_name, tv_brand, tv_price, tv_saleOff, tv_des, tv_color;
    private TextView tv_namecart, tv_pricecart, tv_salecart, tv_total, tv_amount, tv_add;
    private ImageView iv_add, iv_minus;
    private LinearLayout ln_addCart;
    private FrameLayout ln_cart;
    private ImageView iv_item, iv_cancel;
    private Electronics electronics;
    private boolean isShowCart = false;
    private Animation slideUpAnimation, slideDownAnimation;
    private Cart c;
    private DatabaseReference mDatabase;
    private LinearLayout ln_progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic_detail);
        electronics = (Electronics) getIntent().getSerializableExtra(Contants.ELECTRONIC);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("electronics");

        init();
    }

    public void init() {
        ln_progress = (LinearLayout) findViewById(R.id.ln_progress);
        ln_addCart = (LinearLayout) findViewById(R.id.ln_addCart);
        ln_cart = (FrameLayout) findViewById(R.id.ln_cart);
        ln_addCart.setOnClickListener(this);
        tv_add = (TextView) findViewById(R.id.tv_add);
        iv_item = (ImageView) findViewById(R.id.iv_item);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_brand = (TextView) findViewById(R.id.tv_brand);
        tv_color = (TextView) findViewById(R.id.tv_color);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_saleOff = (TextView) findViewById(R.id.tv_sale);
        tv_des = (TextView) findViewById(R.id.tv_des);

        tv_total = (TextView) findViewById(R.id.tv_total);
        tv_namecart = (TextView) findViewById(R.id.tv_namecart);
        tv_pricecart = (TextView) findViewById(R.id.tv_pricecart);
        tv_salecart = (TextView) findViewById(R.id.tv_salecart);
        iv_add = (ImageView) findViewById(R.id.iv_add);
        iv_cancel = (ImageView) findViewById(R.id.iv_cancel);
        iv_minus = (ImageView) findViewById(R.id.iv_minus);
        iv_add.setOnClickListener(this);
        iv_minus.setOnClickListener(this);
        tv_add.setOnClickListener(this);
        iv_cancel.setOnClickListener(this);

        slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_up);

        slideDownAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_down);


        loadData();
    }

    public void setData() {
        tv_name.setText(electronics.getName());
        tv_brand.setText(electronics.getBrand());
        tv_color.setText(electronics.getColor());
        tv_saleOff.setText("-" + electronics.getSaleOff() + "%");
        tv_price.setText(electronics.getPrice() + "đ");
        tv_des.setText(electronics.getDescription());
        tv_salecart.setText("-" + electronics.getSaleOff() + "%");
        tv_price.setText(electronics.getPrice() + "đ");
        tv_pricecart.setText(electronics.getPrice() + "đ");
        Glide.with(this)
                .load(electronics.getUrlImage()[0])
                .override(150, 200)
                .into(iv_item);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ln_addCart) {
            switch (view.getId()) {
                case R.id.ln_addCart:
                    ln_cart.startAnimation(slideUpAnimation);
                    ln_cart.setVisibility(View.VISIBLE);
                    isShowCart = true;
                    if (c == null) {
                        c = new Cart();
                        c.setItem(new Item(electronics.getId(),
                                electronics.getName(), electronics.getPrice(),
                                electronics.getSaleOff(), electronics.getDescription(),
                                electronics.getUrlImage()));
                        c.setQuantily(1);
                    }
                    tv_total.setText(c.totalPrice() + "đ");
                    break;
                case R.id.iv_add:
                    c.setQuantily(c.getQuantily() + 1);
                    tv_amount.setText(c.getQuantily() + "");
                    tv_total.setText(c.totalPrice() + "đ");

                    break;
                case R.id.iv_cancel:
                    isShowCart = false;
                    ln_cart.startAnimation(slideDownAnimation);
                    ln_cart.setVisibility(View.GONE);
                    break;
                case R.id.iv_minus:
                    if (c.getQuantily() == 1) {
                        return;
                    } else {
                        c.setQuantily(c.getQuantily() - 1);
                        tv_amount.setText(c.getQuantily() + "");
                        tv_total.setText(c.totalPrice() + "đ");

                    }
                    break;
                case R.id.tv_add:
                    if (Contants.order == null) {
                        Contants.order = new Order();
                    }
                    Contants.order.addCart(c);
                    isShowCart = false;
                    ln_cart.startAnimation(slideDownAnimation);
                    ln_cart.setVisibility(View.GONE);
                    break;
            }

        }
    }

    @Override
    public void onBackPressed() {
        if (isShowCart) {
            isShowCart = false;
            ln_cart.startAnimation(slideDownAnimation);
            ln_cart.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }
    public void loadData() {
        mDatabase.orderByChild("itemID").equalTo(electronics.getId()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                electronics.setBrand(dataSnapshot.child("brand").getValue().toString());
                electronics.setColor(dataSnapshot.child("color").getValue().toString());
                setData();
                ln_progress.setVisibility(View.GONE);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
