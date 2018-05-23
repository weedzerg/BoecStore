package com.lx.ltuddd.boecstore.client.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.lx.ltuddd.boecstore.client.activities.BookDetailActivity;
import com.lx.ltuddd.boecstore.client.objects.Clothes;
import com.lx.ltuddd.boecstore.client.utils.Contants;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 5/21/2018.
 */

public class AdapterClothes extends AdapterItems {
    private ArrayList<Clothes> ls;

    public AdapterClothes(Context context, ArrayList<Clothes> ls) {
        super(context);
        this.ls = ls;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        final Clothes b = getItem(position);
        holder.tv_name.setText(b.getName());
        holder.tv_price.setText(b.getPrice() + "");
        holder.tv_sale.setText(b.getSaleOff() + "");
        holder.tv_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BookDetailActivity.class);
                intent.putExtra(Contants.CLOTHES, b);
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    @Override
    public Clothes getItem(int position) {
        return ls.get(position);
    }
}
