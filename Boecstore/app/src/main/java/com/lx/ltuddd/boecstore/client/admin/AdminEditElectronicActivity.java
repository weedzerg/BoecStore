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
import com.lx.ltuddd.boecstore.client.objects.Electronics;

public class AdminEditElectronicActivity extends AppCompatActivity {

    private TextView tvTitle;
    private EditText etName, etPrice, etSaleOff, etDescription, etUrlImage, etBrand, etType, etColor;
    private Button btnOk, btnCancel;
    private Electronics electronics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_admin_form_electronic);

        tvTitle = findViewById(R.id.tvTitle);

        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        etSaleOff = findViewById(R.id.etSaleOff);
        etDescription = findViewById(R.id.etDescription);
        etUrlImage = findViewById(R.id.etUrlImage);
        etBrand = findViewById(R.id.etBrand);
        etType = findViewById(R.id.etType);
        etColor = findViewById(R.id.etColor);

        btnOk = findViewById(R.id.btnOK);
        btnCancel = findViewById(R.id.btnCancel);

        electronics = (Electronics) getIntent().getSerializableExtra("electronics");
        tvTitle.setText("Edit Electronics #" + electronics.getId() );
        etName.setText(electronics.getName());
        etPrice.setText(electronics.getPrice()+"");
        etSaleOff.setText(electronics.getSaleOff()+"");
        etDescription.setText(electronics.getDescription());
        etUrlImage.setText(electronics.getUrlImage());
        etBrand.setText(electronics.getBrand());
        etType.setText(electronics.getType());
        etColor.setText(electronics.getColor());

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Electronics electronics = new Electronics("",
                        etName.getText().toString(),
                        Double.parseDouble(etPrice.getText().toString()),
                        Float.parseFloat(etSaleOff.getText().toString()),
                        etDescription.getText().toString(),
                        etUrlImage.getText().toString(),
                        etBrand.getText().toString(),
                        etType.getText().toString(),
                        etColor.getText().toString());
                if(editElectronics(electronics)){
                    Toast.makeText(AdminEditElectronicActivity.this, "Edit success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminEditElectronicActivity.this, AdminActivity.class);
                    intent.putExtra("tab", 1);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AdminEditElectronicActivity.this, "Edit failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminEditElectronicActivity.this , AdminActivity.class);
                intent.putExtra("tab", 1);
                startActivity(intent);
                finish();
            }
        });
    }
    private boolean editElectronics(Electronics electronics){
        return true;
    }
}
