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
import com.lx.ltuddd.boecstore.client.objects.Clothes;
import com.lx.ltuddd.boecstore.client.utils.Utils;

public class AdminEditClothesActivity extends AppCompatActivity {
    private TextView tvTitle;
    private EditText etName, etPrice, etSaleOff, etDescription, etUrlImage, etBrand, etType, etMaterial, etColor, etPattern;
    private Button btnOK, btnCancel;
    private Clothes clothes;

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

        clothes = (Clothes) getIntent().getSerializableExtra("Clothes");

        tvTitle.setText("Edit Clothes #" + clothes.getId());
        etName.setText(clothes.getName());
        etPrice.setText(clothes.getPrice() + "");
        etSaleOff.setText(clothes.getSaleOff() + "");
        etDescription.setText(clothes.getDescription());
        etUrlImage.setText(clothes.getUrlImage()[0]);
        etBrand.setText(clothes.getBrand());
        etType.setText(clothes.getType());
        etMaterial.setText(clothes.getMaterial());
        etColor.setText(clothes.getColor());
        etPattern.setText(clothes.getPattern());

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clothes c = new Clothes("",
                        etName.getText().toString(),
                        Double.parseDouble("0" + etPrice.getText().toString()),
                        Float.parseFloat("0" + etSaleOff.getText().toString()),
                        etDescription.getText().toString(),
                        new String[]{etUrlImage.getText().toString()},
                        etBrand.getText().toString(),
                        etType.getText().toString(),
                        etMaterial.getText().toString(),
                        etColor.getText().toString(),
                        etPattern.getText().toString());
                updateItem(c);
                updateClothes(c);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminEditClothesActivity.this, AdminActivity.class);
                intent.putExtra("tab", 2);
                startActivity(intent);
                finish();
            }
        });
    }

    public void updateItem(final Clothes b) {
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
                Toast.makeText(AdminEditClothesActivity.this, "Edit failed!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void updateClothes(final Clothes b) {
        final DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().child("clothes");
        ref1.orderByChild("itemID").equalTo(b.getId()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists()) {
                    ref1.child(dataSnapshot.getKey()).updateChildren(Utils.creatClothes(b));
                    Toast.makeText(AdminEditClothesActivity.this, "Edit success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminEditClothesActivity.this, AdminActivity.class);
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
                Toast.makeText(AdminEditClothesActivity.this, "Edit failed!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
