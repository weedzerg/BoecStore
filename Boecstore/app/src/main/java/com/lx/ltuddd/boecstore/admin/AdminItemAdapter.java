package com.lx.ltuddd.boecstore.admin;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.objects.Item;

import java.util.ArrayList;

/**
 * Created by Macbook Pro on 5/22/2018.
 */

public class AdminItemAdapter extends ArrayAdapter<Item> {

    private Activity activity;
    private int idLayout;
    private ArrayList<Item> listItem;

    public AdminItemAdapter(Activity activity, int idLayout, ArrayList<Item> listItem) {
        super(activity, idLayout, listItem);
        this.activity = activity;
        this.idLayout = idLayout;
        this.listItem = listItem;
    }

    public ArrayList<Item> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<Item> listItem) {
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        if (convertView == null) {
            convertView = layoutInflater.inflate(idLayout, null);
        }
        ImageView imageView = convertView.findViewById(R.id.iv_item);
        TextView name = convertView.findViewById(R.id.tv_nameitem);
        TextView price = convertView.findViewById(R.id.tv_price);
        Glide.with(getContext()).load(listItem.get(position).getUrlImage()[0]).override(200, 200).into(imageView);
        name.setText(listItem.get(position).getName());
        price.setText(listItem.get(position).getPrice() + " VND");
        return convertView;
    }

    public void updateList(ArrayList<Item> listItem) {
        this.listItem.clear();
        this.listItem.addAll(listItem);
    }
}
