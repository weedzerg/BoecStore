package com.lx.ltuddd.boecstore.client.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;

import com.bumptech.glide.Glide;
import com.lx.ltuddd.boecstore.client.activities.ClothesDetailActivity;
import com.lx.ltuddd.boecstore.client.objects.Clothes;
import com.lx.ltuddd.boecstore.client.utils.Contants;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 5/21/2018.
 */

public class AdapterClothes extends AdapterItems implements Filterable{
    private ArrayList<Clothes> ls;
    private ArrayList<Clothes> lsfilted;

    public AdapterClothes(Context context, ArrayList<Clothes> ls) {
        super(context);
        this.ls = ls;
        this.lsfilted = ls;
    }

    public ArrayList<Clothes> getLs() {
        return lsfilted;
    }

    public void setLs(ArrayList<Clothes> ls) {
        this.lsfilted = ls;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        final Clothes b = getItem(position);
        holder.tv_name.setText(b.getName());
        holder.tv_price.setText(b.getPrice() + "");
        holder.tv_sale.setText(b.getSaleOff() + "");
        holder.iv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ClothesDetailActivity.class);
                intent.putExtra(Contants.CLOTHES, b);
                getContext().startActivity(intent);
            }
        });
        Glide.with(getContext()).load(b.getUrlImage()[0]).override(150,200).into(holder.iv_item);

    }

    @Override
    public int getItemCount() {
        return lsfilted.size();
    }

    @Override
    public Clothes getItem(int position) {
        return lsfilted.get(position);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    lsfilted = ls;
                } else {
                    ArrayList<Clothes> filteredList = new ArrayList<>();
                    for (Clothes b : ls) {
                        if (b.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(b);
                        }
                    }
                    lsfilted = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = lsfilted;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                lsfilted = (ArrayList<Clothes>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
