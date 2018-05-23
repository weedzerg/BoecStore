package com.lx.ltuddd.boecstore.client.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.objects.Cart;
import com.lx.ltuddd.boecstore.client.utils.Contants;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 5/24/2018.
 */

public class AdapterCartOrder extends BaseAdapter {
    private ArrayList<Cart> ls;
    private Context context;

    public AdapterCartOrder(ArrayList<Cart> ls, Context context) {
        this.ls = ls;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public Cart getItem(int i) {
        return ls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    TextView tv_name, tv_price, tv_sale, tv_amount;
    ImageView iv_remove, iv_add, iv_minus, iv_item;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_cart_order, null);
        }
        Cart c = getItem(i);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_price = (TextView) view.findViewById(R.id.tv_price);
        tv_sale = (TextView) view.findViewById(R.id.tv_sale);
        tv_amount = (TextView) view.findViewById(R.id.tv_amount);
        iv_item = (ImageView) view.findViewById(R.id.iv_cart);
        iv_add = (ImageView) view.findViewById(R.id.iv_add);
        iv_minus = (ImageView) view.findViewById(R.id.iv_minus);
        iv_remove = (ImageView) view.findViewById(R.id.iv_remove);
        Glide.with(context).load(c.getItem().getUrlImage()[0]).override(150, 150).into(iv_item);
        tv_amount.setText(c.getQuantily() + "");
        tv_name.setText(c.getItem().getName());
        tv_price.setText(c.getItem().getPrice() + "đ");
        tv_sale.setText("-" + c.getItem().getSaleOff() + "%");
        iv_add.setOnClickListener(on_click);
        iv_minus.setOnClickListener(on_click);
        iv_remove.setOnClickListener(on_click);
        iv_remove.setTag(i);
        iv_minus.setTag(i);
        iv_add.setTag(i);

        return view;
    }

    private View.OnClickListener on_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int index = (int) view.getTag();
            switch (view.getId()) {
                case R.id.iv_remove:
                    ls.remove(index);
                    Contants.order.setLsCart(ls);
                    notifyDataSetChanged();
                    break;
                case R.id.iv_add:

                    ls.get(index).setQuantily((ls.get(index).getQuantily() + 1));
                    tv_amount.setText(getItem(index).getQuantily() + "");

                    break;
                case R.id.iv_minus:
                    if (ls.get(index).getQuantily() == 1) {
                        return;
                    } else {
                        ls.get(index).setQuantily((ls.get(index).getQuantily() - 1));
                        tv_amount.setText(getItem(index).getQuantily() + "");

                    }
                    break;
            }
        }
    };
}
