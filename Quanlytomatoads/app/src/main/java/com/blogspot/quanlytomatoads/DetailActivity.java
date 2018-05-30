package com.blogspot.quanlytomatoads;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.blogspot.quanlytomatoads.adapter.AppCaNhanAdapter;
import com.blogspot.quanlytomatoads.common.Common;
import com.blogspot.quanlytomatoads.common.InputFilterMinMax;
import com.blogspot.quanlytomatoads.model.TTdev;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TTdev tTdev;
    private ImageView imgavatar;
    private TextView txtname, txtdiem, txtloai;
    private Button btsua;
    private EditText eddiem;
    private AppCaNhanAdapter adapter;
    private ListView lsview;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        fomattoobar();

        tTdev = (TTdev) getIntent().getSerializableExtra("data");
        adapter = new AppCaNhanAdapter(this, tTdev.getMypackage());
        Picasso.with(getBaseContext()).load(tTdev.getLinkavatar()).into(imgavatar);
        txtname.setText(tTdev.getName());
        txtdiem.setText(tTdev.getDiem() + "");
        eddiem.setText(tTdev.getDiem() + "");
        if (tTdev.getLoaicount().equals("[google.com]")) {
            txtloai.setTextColor(Color.RED);
            txtloai.setText("Google");
        } else {
            txtloai.setText("Facebook");
            txtloai.setTextColor(Color.parseColor("#125688"));
        }
        lsview.setAdapter(adapter);
        btsua.setOnClickListener(this);
        Common.data.child("/user/" + tTdev.getUserid() + "/infoUser/diem").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int diem = dataSnapshot.getValue(Integer.class);
                tTdev.setDiem(diem);
                txtdiem.setText(diem + "");
                eddiem.setText(diem + "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imgavatar = (ImageView) findViewById(R.id.imgavatar);
        txtdiem = (TextView) findViewById(R.id.txtdiem);
        txtloai = (TextView) findViewById(R.id.txtloai);
        txtname = (TextView) findViewById(R.id.txtname);
        eddiem = (EditText) findViewById(R.id.eddiem);
        eddiem.setFilters(new InputFilter[]{new InputFilterMinMax(1, Integer.MAX_VALUE)});
        btsua = (Button) findViewById(R.id.btsua);
        lsview = (ListView) findViewById(R.id.lsview);
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Bạn có chắc chắn muốn sửa điểm không?");
        alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                int diem = Integer.parseInt(eddiem.getText().toString());
                Common.data.child("/user/" + tTdev.getUserid() + "/infoUser/diem").setValue(diem);
            }
        });

        alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void fomattoobar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setTitle("Thông tin cá nhân");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
