package com.lx.ltuddd.boecstore.client.admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.objects.Book;
import com.lx.ltuddd.boecstore.client.objects.Clothes;
import com.lx.ltuddd.boecstore.client.objects.Item;

import java.util.ArrayList;

public class AdminClothesActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Item> listClothes;
    private AdminItemAdapter itemAdapter;
    private FloatingActionButton btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_admin_listview);

        listView = findViewById(R.id.listAdminItem);
        btnAddItem = findViewById(R.id.btnAdminAddItem);
        listClothes = getListClothes();
        itemAdapter = new AdminItemAdapter(getParent(), R.layout.layout_admin_item, listClothes);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AdminClothesActivity.this, AdminEditClothesActivity.class);
                Clothes clothes = (Clothes) listClothes.get(position);
                intent.putExtra("Clothes", clothes);
                startActivity(intent);
                finish();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Clothes clothes = (Clothes) listClothes.get(position);
                new AlertDialog.Builder(AdminClothesActivity.this)
                        .setTitle("Notice")
                        .setMessage("Do you want to delete clothes #" + clothes.getId() + ": " + clothes.getName() + "?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(deleteClothes(clothes)){
                                    Toast.makeText(AdminClothesActivity.this, "Delete success!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(AdminClothesActivity.this, "Delete failed!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
                return true;
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminClothesActivity.this, AdminAddClothesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private ArrayList<Item> getListClothes(){
        // Tu tao du lieu => Se thay bang firebase
        Clothes clothes1 = new Clothes("1", "Áo 1", 50000, 5, "a", null, "Gucci",
                "Shirt", "Cotton", "Red", "");
        Clothes clothes2 = new Clothes("2", "Quần 1", 20000, 15, "a", null, "Gucci",
                "Shirt", "Cotton", "Red", "");
        Clothes clothes3 = new Clothes("3", "Áo 2", 500000, 25, "a", null, "Channel",
                "Shirt", "Cotton", "Red", "");
        Clothes clothes4 = new Clothes("4", "Quần 2", 350000, 35, "a", null, "Nike",
                "Shirt", "Cotton", "Red", "");
        Clothes clothes5 = new Clothes("5", "Áo 3", 520000, 15, "a", null, "Channel",
                "Shirt", "Cotton", "Red", "");
        Clothes clothes6 = new Clothes("6", "Quần 3", 1500000, 5, "a", null, "Nike",
                "Shirt", "Cotton", "Red", "");
        ArrayList<Item> list = new ArrayList<>();
        list.add(clothes1);
        list.add(clothes2);
        list.add(clothes3);
        list.add(clothes4);
        list.add(clothes5);
        list.add(clothes6);
        return list;
    }
    private boolean deleteClothes(Clothes clothes){
        ArrayList<Item> tmp = new ArrayList<>();
        tmp.addAll(listClothes);
        tmp.remove(clothes);
        itemAdapter.updateList(tmp);
        itemAdapter.notifyDataSetChanged();
        return true;
    }
}
