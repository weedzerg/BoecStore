package com.lx.ltuddd.boecstore.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.objects.Book;
import com.lx.ltuddd.boecstore.client.utils.Utils;

public class AdminEditBookActivity extends AppCompatActivity {

    private TextView tvTitle;
    private EditText etName, etPrice, etSaleOff, etDescription, etUrlImage, etType, etYear, etAuthor;
    private Button btnOK, btnCancel;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_admin_form_book);

        tvTitle = findViewById(R.id.tvTitle);
        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        etSaleOff = findViewById(R.id.etSaleOff);
        etDescription = findViewById(R.id.etDescription);
        etUrlImage = findViewById(R.id.etUrlImage);
        etType = findViewById(R.id.etType);
        etYear = findViewById(R.id.etYear);
        etAuthor = findViewById(R.id.etAuthor);
        btnOK = findViewById(R.id.btnOK);
        btnCancel = findViewById(R.id.btnCancel);

        book = (Book) getIntent().getSerializableExtra("book");

        tvTitle.setText("Edit Book #" + book.getId());
        etName.setText(book.getName());
        etPrice.setText(book.getPrice() + "");
        etSaleOff.setText(book.getSaleOff() + "");
        etDescription.setText(book.getDescription());
        etUrlImage.setText(book.getUrlImage()[0]);
        etType.setText(book.getPublisher());
        etYear.setText(book.getYear() + "");
        etAuthor.setText(book.getAuthor());

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book b = new Book(book.getId(),
                        etName.getText().toString(),
                        Double.parseDouble(etPrice.getText().toString()),
                        Float.parseFloat(etSaleOff.getText().toString()),
                        etDescription.getText().toString(),
                        book.getUrlImage(),
                        Integer.parseInt(etYear.getText().toString()),
                        etAuthor.getText().toString(), etType.getText().toString());
                updateItem(b);
                updateBook(b);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminEditBookActivity.this, AdminActivity.class);
                intent.putExtra("tab", 0);
                startActivity(intent);
                finish();
            }
        });
    }

    public void updateItem(final Book b) {
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("item");
        ref.orderByChild("id").equalTo(b.getId()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists()) {
                    ref.child(dataSnapshot.getKey()).updateChildren(Utils.creatItem(b));

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminEditBookActivity.this, "Edit failed!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void updateBook(final Book b) {
        final DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().child("books");
        ref1.orderByChild("itemID").equalTo(b.getId()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists()) {
                    ref1.child(dataSnapshot.getKey()).updateChildren(Utils.creatBook(b));
                    Toast.makeText(AdminEditBookActivity.this, "Edit success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminEditBookActivity.this, AdminActivity.class);
                    intent.putExtra("tab", 0);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminEditBookActivity.this, "Edit failed!", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
