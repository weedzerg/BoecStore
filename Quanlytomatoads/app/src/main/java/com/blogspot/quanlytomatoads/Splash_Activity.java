package com.blogspot.quanlytomatoads;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.quanlytomatoads.view.MainActivity_Login;
import com.blogspot.quanlytomatoads.view.MainActivity_QL;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash_Activity extends AppCompatActivity {
    private TextView txt;
    private TextView login;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String email, pass;
    private EditText email_, pass_;
    private LinearLayout ln;
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        txt = (TextView) findViewById(R.id.txt1);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        boolean check = getdatafrom_share();
        if (!check) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Splash_Activity.this, MainActivity_Login.class);
                    startActivity(intent);
                    finish();
                }
            }, 500);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
    }

    public boolean getdatafrom_share() {
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        email = sharedPreferences.getString("email", "");
        pass = sharedPreferences.getString("pass", "");
        if (email != "" && pass != "") {
            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("login email", "createUserWithEmail:success");
                                Intent intent = new Intent(Splash_Activity.this, MainActivity_QL.class);
                                startActivity(intent);
                                finish();
                            } else {
                            }
                        }
                    });

            return true;
        } else {
            Toast.makeText(this, "Email/Mật khẩu trống", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
