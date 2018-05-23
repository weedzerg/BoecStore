package com.lx.ltuddd.boecstore.admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.objects.Clothes;
import com.lx.ltuddd.boecstore.client.objects.Item;

import java.util.ArrayList;

public class AdminClothesActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Item> listClothes;
    private AdminItemAdapter itemAdapter;
    private FloatingActionButton btnAddItem;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_admin_listview);
        mDatabase = FirebaseDatabase.getInstance().getReference("item");
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
                                if (deleteClothes(clothes)) {
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
        loadData();
    }

    private ArrayList<Item> getListClothes() {

        ArrayList<Item> list = new ArrayList<>();

        return list;
    }

    private boolean deleteClothes(Clothes clothes) {
        ArrayList<Item> tmp = new ArrayList<>();
        tmp.addAll(listClothes);
        tmp.remove(clothes);
        itemAdapter.updateList(tmp);
        itemAdapter.notifyDataSetChanged();
        return true;
    }

    public void loadData() {
        mDatabase.orderByChild("type").equalTo(3).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //lang nghe su kien khi 1 cay con add vao
                String id, name, des, url;
                Double price;
                float saleoff;
                id = dataSnapshot.child("id").getValue().toString();
                name = dataSnapshot.child("name").getValue().toString();
                des = dataSnapshot.child("description").getValue().toString();
                url = dataSnapshot.child("urlImage").getValue().toString();
                price = Double.valueOf(dataSnapshot.child("price").getValue().toString());
                saleoff = Float.valueOf(dataSnapshot.child("saleOff").getValue().toString());
                listClothes.add(new Clothes(id, name, price, saleoff, des, url.split(";")));
                itemAdapter.setListItem(listClothes);
                itemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //lang nghe su kien khi child trong nhanh co su thay doi
                //ta ve snapshot thay doi
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // 1 cay con bi xoa
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//                          1 cay con bi di chuyen
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
