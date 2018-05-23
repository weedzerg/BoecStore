package com.lx.ltuddd.boecstore.client.admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.objects.Book;
import com.lx.ltuddd.boecstore.client.objects.Item;

import java.util.ArrayList;

public class AdminBookActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Item> listBook;
    private AdminItemAdapter itemAdapter;
    private FloatingActionButton btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_admin_listview);

        listView = findViewById(R.id.listAdminItem);
        btnAddItem = findViewById(R.id.btnAdminAddItem);
        listBook = getListBook();
        itemAdapter = new AdminItemAdapter(getParent(), R.layout.layout_admin_item, listBook);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AdminBookActivity.this, AdminEditBookActivity.class);
                Book book = (Book) listBook.get(position);
                intent.putExtra("book", book);
                startActivity(intent);
                finish();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Book book = (Book) listBook.get(position);
                new AlertDialog.Builder(AdminBookActivity.this)
                        .setTitle("Notice")
                        .setMessage("Do you want to delete book #" + book.getId() + ": " + book.getName() + "?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Book book = (Book) listBook.get(position);
                                if(deleteBook(book)){
                                    Toast.makeText(AdminBookActivity.this, "Delete success!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(AdminBookActivity.this, "Delete failed!", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(AdminBookActivity.this, AdminAddBookActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private ArrayList<Item> getListBook(){
        // Tu tao du lieu => Se thay bang firebase
        Book book1 = new Book("1", "Sách 1", 100000, 10, "123", null,
                "Truyện", 2016, "NXB Giáo dục");
        Book book2 = new Book("2", "Sách 2", 200000, 11, "123", null,
                "Truyện", 2016, "NXB Giáo dục");
        Book book3 = new Book("3", "Sách 3", 300000, 12, "123", null,
                "Truyện", 2017, "NXB Giáo dục");
        Book book4 = new Book("4", "Sách 4", 400000, 13, "123", null,
                "Truyện", 2018, "NXB Giáo dục");
        Book book5 = new Book("5", "Sách 5", 500000, 13, "123", null,
                "Truyện", 2018, "NXB Giáo dục");
        Book book6 = new Book("6", "Sách 6", 600000, 13, "123", null,
                "Truyện", 2018, "NXB Giáo dục");
        ArrayList<Item> list = new ArrayList<>();
        list.add(book1);
        list.add(book2);
        list.add(book3);
        list.add(book4);
        list.add(book5);
        list.add(book6);
        return list;
    }

    private boolean deleteBook(Book book){
        ArrayList<Item> tmp = new ArrayList<>();
        tmp.addAll(listBook);
        tmp.remove(book);
        itemAdapter.updateList(tmp);
        itemAdapter.notifyDataSetChanged();
        return true;
    }
}
