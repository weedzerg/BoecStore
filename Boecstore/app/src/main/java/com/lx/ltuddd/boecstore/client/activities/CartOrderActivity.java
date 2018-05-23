package com.lx.ltuddd.boecstore.client.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.adapters.AdapterCartOrder;
import com.lx.ltuddd.boecstore.client.objects.Cart;
import com.lx.ltuddd.boecstore.client.utils.Contants;

import java.util.ArrayList;

public class CartOrderActivity extends AppCompatActivity {
    private TextView tv_payment;
    private ListView lsview;
    private ArrayList<Cart> ls;
    private AdapterCartOrder adapterCartOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cart_order);
        init();
    }

    public void init() {
        lsview = (ListView) findViewById(R.id.ls_view);
        ls = Contants.order.getLsCart();
        adapterCartOrder = new AdapterCartOrder(ls, getBaseContext());
        lsview.setAdapter(adapterCartOrder);
        adapterCartOrder.notifyDataSetChanged();

        tv_payment = (TextView) findViewById(R.id.tv_payment);
        tv_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
