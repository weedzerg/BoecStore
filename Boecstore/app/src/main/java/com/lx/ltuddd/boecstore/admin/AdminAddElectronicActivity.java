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
import com.lx.ltuddd.boecstore.client.objects.Electronics;
import com.lx.ltuddd.boecstore.client.utils.Utils;

public class AdminAddElectronicActivity extends AppCompatActivity {

    private TextView tvTitle;
    private EditText etName, etPrice, etSaleOff, etDescription, etUrlImage, etBrand, etType, etColor;
    private Button btnOk, btnCancel;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_admin_form_electronic);
        ref = FirebaseDatabase.getInstance().getReference();
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

        tvTitle.setText("Add Electronic");

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ref.child("item").push().getKey();
                Electronics e = new Electronics("",
                        etName.getText().toString(),
                        Double.parseDouble("0" + etPrice.getText().toString()),
                        Float.parseFloat("0" + etSaleOff.getText().toString()),
                        etDescription.getText().toString(),
                        new String[]{etUrlImage.getText().toString()},
                        etBrand.getText().toString(),
                        etType.getText().toString(),
                        etColor.getText().toString());
                ref.child("item").child(id).updateChildren(Utils.creatItem(e));
                ref.child("clothes").child(id).updateChildren(Utils.creatElectronic(e));
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddElectronicActivity.this, AdminActivity.class);
                intent.putExtra("tab", 1);
                startActivity(intent);
                finish();
            }
        });
    }

}
