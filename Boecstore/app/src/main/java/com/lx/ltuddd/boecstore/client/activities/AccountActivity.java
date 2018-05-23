package com.lx.ltuddd.boecstore.client.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;
import com.lx.ltuddd.boecstore.R;
import com.lx.ltuddd.boecstore.client.objects.Address;
import com.lx.ltuddd.boecstore.client.objects.Customer;
import com.lx.ltuddd.boecstore.client.objects.FullName;
import com.lx.ltuddd.boecstore.client.utils.Contants;
import com.lx.ltuddd.boecstore.client.utils.Utils;

public class AccountActivity extends AppCompatActivity {
    private ImageView iv_ava;
    private EditText ed_name, ed_email, ed_number, ed_add1, ed_add2, ed_add3, ed_add4, ed_sex;
    private TextView tv_update;
    private Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        customer = (Customer) getIntent().getSerializableExtra(Contants.DATAINTENT);
        init();
    }

    public void init() {
        iv_ava = (ImageView) findViewById(R.id.iv_ava);
        ed_name = (EditText) findViewById(R.id.ed_name);
        ed_email = (EditText) findViewById(R.id.ed_mail);
        ed_number = (EditText) findViewById(R.id.ed_phone);
        ed_add1 = (EditText) findViewById(R.id.ed_numberhouse);
        ed_add2 = (EditText) findViewById(R.id.ed_street);
        ed_add3 = (EditText) findViewById(R.id.ed_city);
        ed_add4 = (EditText) findViewById(R.id.ed_dist);
        ed_sex = (EditText) findViewById(R.id.ed_sex);
        tv_update = (TextView) findViewById(R.id.tv_update);
        setData();
        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customer.setFullName(new FullName(ed_name.getText().toString(), "", ""));
                customer.setEmail(ed_email.getText().toString());
                customer.setNumber(ed_number.getText().toString());
                customer.setAddress(new Address(ed_add1.getText().toString(),
                        ed_add2.getText().toString(),
                        ed_add3.getText().toString(),
                        ed_add4.getText().toString()));
                customer.setSex(ed_sex.getText().toString());
                if (Utils.upInfCustomer(FirebaseDatabase.getInstance().getReference().child("person/" +
                        Contants.user.getUid()), customer)) {
                    finish();
                } else {
                    Toast.makeText(AccountActivity.this, "erro process", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setData() {
        Glide.with(getBaseContext()).load(customer.getUrlImage()).into(iv_ava);
        ed_name.setText(customer.getFullName().getFirstName());
        ed_email.setText(customer.getEmail());
        ed_email.setText(customer.getEmail());
        if (customer.getNumber() != null) {
            ed_number.setText(customer.getNumber());
        }
        if (customer.getAddress() != null) {
            ed_add1.setText(customer.getAddress().getNumberHouse());
            ed_add2.setText(customer.getAddress().getStreet());
            ed_add3.setText(customer.getAddress().getCity());
            ed_add4.setText(customer.getAddress().getCountry());
        }
        if (customer.getSex() != null) {
            ed_sex.setText(customer.getSex());
        }
    }
}
