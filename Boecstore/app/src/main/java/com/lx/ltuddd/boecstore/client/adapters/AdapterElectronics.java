package com.lx.ltuddd.boecstore.client.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;

import com.bumptech.glide.Glide;
import com.lx.ltuddd.boecstore.client.activities.ElectronicDetailActivity;
import com.lx.ltuddd.boecstore.client.objects.Electronics;
import com.lx.ltuddd.boecstore.client.utils.Contants;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 5/21/2018.
 */

public class AdapterElectronics extends AdapterItems implements Filterable {
    private ArrayList<Electronics> ls;
    private ArrayList<Electronics> lsFilted;

    public AdapterElectronics(Context context, ArrayList<Electronics> ls) {
        super(context);
        this.ls = ls;
        this.lsFilted = ls;
    }

    public ArrayList<Electronics> getLs() {
        return lsFilted;
    }

    public void setLs(ArrayList<Electronics> ls) {
        this.lsFilted = ls;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        final Electronics b = getItem(position);
        holder.tv_name.setText(b.getName());
        holder.tv_price.setText(b.getPrice() + "");
        holder.tv_sale.setText(b.getSaleOff() + "");
        holder.iv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ElectronicDetailActivity.class);
                intent.putExtra(Contants.ELECTRONIC, b);
                getContext().startActivity(intent);
            }
        });
        Glide.with(getContext()).load(b.getUrlImage()[0]).override(150, 200).into(holder.iv_item);

    }

    @Override
    public int getItemCount() {
        return lsFilted.size();
    }

    @Override
    public Electronics getItem(int position) {
        return lsFilted.get(position);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    lsFilted = ls;
                } else {
                    ArrayList<Electronics> filteredList = new ArrayList<>();
                    for (Electronics b : ls) {
                        if (b.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(b);
                        }
                    }
                    lsFilted = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = lsFilted;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                lsFilted = (ArrayList<Electronics>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
