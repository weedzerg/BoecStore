package com.lx.ltuddd.boecstore.client.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lx.ltuddd.boecstore.R;

/**
 * Created by DaiPhongPC on 5/21/2018.
 */

public class AdapterItems extends RecyclerView.Adapter<AdapterItems.ItemHolder> {
    private Context context;


    public AdapterItems(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public Object getItem(int position) {
        return 0;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_price, tv_sale, tv_buynow,tv_detail;
        ImageView iv_item;

        public ItemHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_nameitem);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_sale = (TextView) itemView.findViewById(R.id.tv_sale);
            tv_buynow = (TextView) itemView.findViewById(R.id.tv_buynow);
            tv_detail = (TextView) itemView.findViewById(R.id.tv_detail);
            iv_item = (ImageView) itemView.findViewById(R.id.iv_item);
        }
    }
}
