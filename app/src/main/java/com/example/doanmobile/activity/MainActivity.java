package com.example.doanmobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doanmobile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button btnlogin, btnregister;
    EditText edtemail, edtpassword;
    FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtemail = findViewById(R.id.edtEmail);
        edtpassword = findViewById(R.id.edtPass);
        btnregister = findViewById(R.id.btnRegister);
        btnlogin = findViewById(R.id.btnLogin);

        authentication = FirebaseAuth.getInstance();
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.buttonclick);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                login();
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                register();
            }
        });
    }

    private void register() {
        Intent intent = new Intent(MainActivity.this, register.class);
        startActivity(intent);
    }

    private void login() {
        String email, pass;
        email = edtemail.getText().toString();
        pass = edtpassword.getText().toString();

        if (TextUtils.isEmpty(email)||TextUtils.isEmpty(pass)) {
            Toast.makeText(this,"Please enter complete information!", Toast.LENGTH_SHORT).show();
            return;
        }

        authentication.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Login successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(MainActivity.this, homepage.class);
                    startActivity(intent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Login failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}