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
import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.objects.Book;
import com.lx.ltuddd.boecstore.client.objects.Cart;
import com.lx.ltuddd.boecstore.client.objects.Item;
import com.lx.ltuddd.boecstore.client.objects.Order;
import com.lx.ltuddd.boecstore.client.utils.Contants;
import com.lx.ltuddd.boecstore.client.utils.OnSwipeTouchListener;

public class BookDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_name, tv_author, tv_year, tv_price, tv_saleOff, tv_des;
    private TextView tv_namecart, tv_pricecart, tv_salecart, tv_total, tv_amount, tv_add;
    private ImageView iv_add, iv_minus;
    private LinearLayout ln_addCart;
    private FrameLayout ln_cart;
    private ImageView iv_item, iv_cancel;
    private Book book;
    private boolean isShowCart = false;
    private Animation slideUpAnimation, slideDownAnimation;
    private Cart c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_book_layout);
        book = (Book) getIntent().getSerializableExtra(Contants.BOOK);
        init();
    }

    public void init() {
        ln_addCart = (LinearLayout) findViewById(R.id.ln_addCart);
        ln_cart = (FrameLayout) findViewById(R.id.ln_cart);
        ln_addCart.setOnClickListener(this);

        iv_item = (ImageView) findViewById(R.id.iv_item);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_author = (TextView) findViewById(R.id.tv_author);
        tv_year = (TextView) findViewById(R.id.tv_year);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_saleOff = (TextView) findViewById(R.id.tv_sale);
        tv_des = (TextView) findViewById(R.id.tv_des);
        tv_amount = (TextView) findViewById(R.id.tv_amount);
        tv_add = (TextView) findViewById(R.id.tv_add);
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

        setData();
    }

    public void setData() {
        tv_name.setText(book.getName());
        tv_namecart.setText(book.getName());
        tv_author.setText(book.getAuthor());
        tv_year.setText(book.getYear() + "");
        tv_saleOff.setText("-" + book.getSaleOff() + "%");
        tv_des.setText(book.getDescription());

        tv_salecart.setText("-" + book.getSaleOff() + "%");
        tv_price.setText(book.getPrice() + "đ");
        tv_pricecart.setText(book.getPrice() + "đ");
        Glide.with(this)
                .load(book.getUrlImage()[0])
                .override(150, 200)
                .into(iv_item);
        iv_item.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ln_addCart:
                ln_cart.startAnimation(slideUpAnimation);
                ln_cart.setVisibility(View.VISIBLE);
                isShowCart = true;
                if (c == null) {
                    c = new Cart();
                    c.setItem(new Item(book.getId(),
                            book.getName(), book.getPrice(),
                            book.getSaleOff(), book.getDescription(),
                            book.getUrlImage()));
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
}
