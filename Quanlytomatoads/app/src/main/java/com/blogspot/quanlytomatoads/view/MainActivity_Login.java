package com.blogspot.quanlytomatoads.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.quanlytomatoads.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity_Login extends AppCompatActivity implements View.OnClickListener {
    private TextView login;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String email, pass;
    private EditText email_, pass_;
    private LinearLayout ln;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        email_ = (EditText) findViewById(R.id.email);
        pass_ = (EditText) findViewById(R.id.pass);
        login = (TextView) findViewById(R.id.login);
        ln = (LinearLayout) findViewById(R.id.pro);
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", "");
        editor.putString("pass", "");
        editor.commit();
        ln.setVisibility(View.INVISIBLE);
        login.setOnClickListener(this);
    }

    public void savemanager() {
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email_.getText().toString());
        editor.putString("pass", pass_.getText().toString());
        editor.commit();
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
                                startintent();
                            } else {
                                Log.w("login email", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity_Login.this, "Tài khoản hoặc mật khẩu không dúng",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            return true;
        } else {
            Toast.makeText(this, "Email/Mật khẩu trống", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void startintent() {
        Intent intent = new Intent(MainActivity_Login.this, MainActivity_QL.class);
        startActivityForResult(intent, 500);
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
    }

    @Override
    public void onClick(View view) {
        email = email_.getText().toString();
        pass = pass_.getText().toString();
        if (!email.equals("") && !pass.equals("")) {
            ln.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                savemanager();
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("login email", "createUserWithEmail:success");
                                startintent();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("login email", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity_Login.this, "Tài khoản hoặc mật khẩu không dúng",
                                        Toast.LENGTH_SHORT).show();
                                ln.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Email/Mật khẩu trống", Toast.LENGTH_SHORT).show();
        }
    }

}
