package com.lx.ltuddd.boecstore.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.objects.Book;
import com.lx.ltuddd.boecstore.client.utils.Utils;

public class AdminAddBookActivity extends AppCompatActivity {

    private TextView tvTitle;
    private EditText etName, etPrice, etSaleOff, etDescription, etUrlImage, etType, etYear, etAuthor;
    private Button btnOK, btnCancel;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_admin_form_book);
        ref = FirebaseDatabase.getInstance().getReference();
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

        tvTitle.setText("Add Book");

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ref.child("item").push().getKey();
                Book b = new Book(id,
                        etName.getText().toString(),
                        Double.parseDouble(etPrice.getText().toString()),
                        Float.parseFloat(etSaleOff.getText().toString()),
                        etDescription.getText().toString(),
                        new String[]{etUrlImage.getText().toString()},
                        Integer.parseInt(etYear.getText().toString()),
                        etAuthor.getText().toString(), etType.getText().toString());
                ref.child("item").child(id).updateChildren(Utils.creatItem(b));
                ref.child("books").child(id).updateChildren(Utils.creatBook(b));

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddBookActivity.this, AdminActivity.class);
                intent.putExtra("tab", 0);
                startActivity(intent);
                finish();
            }
        });
    }


}
