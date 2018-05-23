package com.lx.ltuddd.boecstore.client.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.objects.Book;
import com.lx.ltuddd.boecstore.client.objects.Clothes;
import com.lx.ltuddd.boecstore.client.objects.Electronics;
import com.lx.ltuddd.boecstore.client.objects.Item;

import java.util.ArrayList;

public class AdminElectronicActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Item> listElectronic;
    private AdminItemAdapter itemAdapter;
    private FloatingActionButton btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_admin_listview);

        listView = findViewById(R.id.listAdminItem);
        btnAddItem = findViewById(R.id.btnAdminAddItem);
        listElectronic = getListElectronic();
        itemAdapter = new AdminItemAdapter(getParent(), R.layout.layout_admin_item, listElectronic);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AdminElectronicActivity.this, AdminEditElectronicActivity.class);
                Electronics electronics = (Electronics) listElectronic.get(position);
                intent.putExtra("electronics", electronics);
                startActivity(intent);
                finish();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final Electronics electronics = (Electronics) listElectronic.get(position);
                new AlertDialog.Builder(AdminElectronicActivity.this)
                        .setTitle("Notice")
                        .setMessage("Do you want to delete electronics #" + electronics.getId() + electronics.getName() + "?" )
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Electronics electronics1 = (Electronics) listElectronic.get(position);
                                if(deleteElectronics(electronics)){
                                    Toast.makeText(AdminElectronicActivity.this, "Delete Succsess", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(AdminElectronicActivity.this, "Delete Failed", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(AdminElectronicActivity.this, AdminAddElectronicActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private ArrayList<Item> getListElectronic(){
        // Tu tao du lieu => Se thay bang firebase
        Electronics electronics1 = new Electronics("1", "Tivi", 9000000, 5, "a", null,
                "LG", "Electroníc", "Black");
        Electronics electronics2 = new Electronics("2", "Tủ lạnh", 1500000, 5, "a", null,
                "LG", "Electroníc", "Black");
        Electronics electronics3 = new Electronics("3", "Điều hòa", 3500000, 5, "a", null,
                "LG", "Electroníc", "Black");
        Electronics electronics4 = new Electronics("4", "Ổn áp", 4200000, 5, "a", null,
                "LG", "Electroníc", "Black");
        Electronics electronics5 = new Electronics("5", "Quạt trần", 500000, 5, "a", null,
                "LG", "Electroníc", "Black");
        Electronics electronics6 = new Electronics("6", "Máy xay sinh tố", 200000, 5, "a", null,
                "LG", "Electroníc", "Black");
        ArrayList<Item> list = new ArrayList<>();
        list.add(electronics1);
        list.add(electronics2);
        list.add(electronics3);
        list.add(electronics4);
        list.add(electronics5);
        list.add(electronics6);
        return list;
    }
    private boolean deleteElectronics(Electronics electronics){
        ArrayList<Item> tmp = new ArrayList<>();
        tmp.addAll(listElectronic);
        tmp.remove(electronics);
        itemAdapter.updateList(tmp);
        itemAdapter.notifyDataSetChanged();
        return true;
    }
}
