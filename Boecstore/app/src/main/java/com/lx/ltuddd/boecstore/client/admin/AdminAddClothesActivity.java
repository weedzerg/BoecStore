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
import com.lx.ltuddd.boecstore.client.objects.Clothes;
import com.lx.ltuddd.boecstore.client.objects.Electronics;

public class AdminAddClothesActivity extends AppCompatActivity {

    private TextView tvTitle;
    private EditText etName, etPrice, etSaleOff, etDescription, etUrlImage, etBrand, etType, etMaterial, etColor, etPattern;
    private Button btnOK, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_admin_form_clothes);
        tvTitle = findViewById(R.id.tvTitle);
        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        etSaleOff = findViewById(R.id.etSaleOff);
        etDescription = findViewById(R.id.etDescription);
        etUrlImage = findViewById(R.id.etUrlImage);
        etBrand = findViewById(R.id.etBrand);
        etType = findViewById(R.id.etType);
        etMaterial = findViewById(R.id.etMaterial);
        etColor = findViewById(R.id.etColor);
        etPattern = findViewById(R.id.etPattern);
        btnOK = findViewById(R.id.btnOK);
        btnCancel = findViewById(R.id.btnCancel);

        tvTitle.setText("Add Clothes");

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clothes clothes = new Clothes("",
                        etName.getText().toString(),
                        Double.parseDouble("0"+etPrice.getText().toString()),
                        Float.parseFloat("0"+etSaleOff.getText().toString()),
                        etDescription.getText().toString(),
                        etUrlImage.getText().toString(),
                        etBrand.getText().toString(),
                        etType.getText().toString(),
                        etMaterial.getText().toString(),
                        etColor.getText().toString(),
                        etPattern.getText().toString());
                if (addClothes(clothes)) {
                    Toast.makeText(AdminAddClothesActivity.this, "Add success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminAddClothesActivity.this, AdminActivity.class);
                    intent.putExtra("tab", 2);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AdminAddClothesActivity.this, "Add failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddClothesActivity.this, AdminActivity.class);
                intent.putExtra("tab", 2);
                startActivity(intent);
                finish();
            }
        });
    }
        private boolean addClothes(Clothes clothes) {
            return true;
        }
}
