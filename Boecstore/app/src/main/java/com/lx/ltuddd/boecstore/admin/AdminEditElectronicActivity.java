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
import com.lx.ltuddd.boecstore.client.objects.Electronics;
import com.lx.ltuddd.boecstore.client.utils.Utils;

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
        tvTitle.setText("Edit Electronics #" + electronics.getId());
        etName.setText(electronics.getName());
        etPrice.setText(electronics.getPrice() + "");
        etSaleOff.setText(electronics.getSaleOff() + "");
        etDescription.setText(electronics.getDescription());
        etUrlImage.setText(electronics.getUrlImage()[0]);
        etBrand.setText(electronics.getBrand());
        etType.setText(electronics.getType());
        etColor.setText(electronics.getColor());

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Electronics e = new Electronics("",
                        etName.getText().toString(),
                        Double.parseDouble(etPrice.getText().toString()),
                        Float.parseFloat(etSaleOff.getText().toString()),
                        etDescription.getText().toString(),
                        new String[]{etUrlImage.getText().toString()},
                        etBrand.getText().toString(),
                        etType.getText().toString(),
                        etColor.getText().toString());
                updateItem(e);
                updateElec(e);

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminEditElectronicActivity.this, AdminActivity.class);
                intent.putExtra("tab", 1);
                startActivity(intent);
                finish();
            }
        });
    }

    public void updateItem(final Electronics b) {
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
                Toast.makeText(AdminEditElectronicActivity.this, "Edit failed!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void updateElec(final Electronics b) {
        final DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().child("electronics");
        ref1.orderByChild("itemID").equalTo(b.getId()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists()) {
                    ref1.child(dataSnapshot.getKey()).updateChildren(Utils.creatElectronic(b));
                    Toast.makeText(AdminEditElectronicActivity.this, "Edit success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminEditElectronicActivity.this, AdminActivity.class);
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
                Toast.makeText(AdminEditElectronicActivity.this, "Edit failed!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
