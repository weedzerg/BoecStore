package com.lx.ltuddd.boecstore.client.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.objects.Book;

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
        etPrice.setText(book.getPrice()+ "");
        etSaleOff.setText(book.getSaleOff()+"");
        etDescription.setText(book.getDescription());
        etUrlImage.setText(book.getUrlImage());
        etType.setText(book.getType());
        etYear.setText(book.getYear()+"");
        etAuthor.setText(book.getAuthor());

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book("",
                        etName.getText().toString(),
                        Double.parseDouble(etPrice.getText().toString()),
                        Float.parseFloat(etSaleOff.getText().toString()),
                        etDescription.getText().toString(),
                        etUrlImage.getText().toString(),
                        etType.getText().toString(),
                        Integer.parseInt(etYear.getText().toString()),
                        etAuthor.getText().toString());
                if(editBook(book)){
                    Toast.makeText(AdminEditBookActivity.this, "Edit success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminEditBookActivity.this, AdminActivity.class);
                    intent.putExtra("tab", 0);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AdminEditBookActivity.this, "Edit failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminEditBookActivity.this , AdminActivity.class);
                intent.putExtra("tab", 0);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean editBook(Book book){
        return true;
    }
}
