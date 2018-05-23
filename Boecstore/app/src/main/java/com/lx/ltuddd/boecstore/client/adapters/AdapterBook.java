package com.lx.ltuddd.boecstore.client.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;

import com.bumptech.glide.Glide;
import com.lx.ltuddd.boecstore.client.activities.BookDetailActivity;
import com.lx.ltuddd.boecstore.client.objects.Book;
import com.lx.ltuddd.boecstore.client.utils.Contants;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 5/21/2018.
 */

public class AdapterBook extends AdapterItems implements Filterable {
    private ArrayList<Book> ls;
    private ArrayList<Book> lsFilted;

    public AdapterBook(Context context, ArrayList<Book> ls) {
        super(context);
        this.ls = ls;
        this.lsFilted = ls;
    }

    public ArrayList<Book> getLs() {
        return lsFilted;
    }

    public void setLs(ArrayList<Book> ls) {
        this.lsFilted = ls;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, int position) {
        final Book b = getItem(position);
        holder.tv_name.setText(b.getName());
        holder.tv_price.setText(b.getPrice() + "");
        holder.tv_sale.setText(b.getSaleOff() + "");
        holder.iv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BookDetailActivity.class);
                intent.putExtra(Contants.BOOK, b);
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
    public Book getItem(int position) {
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
                    ArrayList<Book> filteredList = new ArrayList<>();
                    for (Book b : ls) {
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
                lsFilted = (ArrayList<Book>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
