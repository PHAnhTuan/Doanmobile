package com.example.doanmobile.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanmobile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    EditText edtName, edtBirtday, edtPhone, edtLevel,edtNation;
    EditText edtEmail, edtpassword;
    Button btnregister, btnback;
    RadioGroup radioGroup;
    RadioButton rdbMale, rdbFenale;
    private FirebaseAuth Authentication;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Authentication = (FirebaseAuth)FirebaseAuth.getInstance();
        edtName = findViewById(R.id.edtName);
        edtBirtday = findViewById(R.id.edtBirth);
        rdbMale = findViewById(R.id.rdbMale);
        rdbFenale = findViewById(R.id.rdbFemale);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        edtLevel = findViewById(R.id.edtLevel);
        edtNation = findViewById(R.id.edtNation);
        edtpassword = findViewById(R.id.edtPass);
        btnregister = findViewById(R.id.btnSubmit);
        btnback = findViewById(R.id.btnback);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.buttonclick);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                register();
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent = new Intent(register.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void register() {
        String name,birth,phone, email,level,nation, pass;
        name = edtName.getText().toString();
        birth = edtBirtday.getText().toString();
        phone = edtPhone.getText().toString();
        email = edtEmail.getText().toString();
        level = edtLevel.getText().toString();
        nation = edtNation.getText().toString();
        pass = edtpassword.getText().toString();
        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(birth)||TextUtils.isEmpty(phone)||
                TextUtils.isEmpty(email)||TextUtils.isEmpty(level)||TextUtils.isEmpty(nation)||
                TextUtils.isEmpty(pass)) {
            Toast.makeText(this,"Please enter complete information!", Toast.LENGTH_SHORT).show();
            return;
        }else if (edtpassword.length() < 9){
            Toast.makeText(this,"Please enter least 9 number!", Toast.LENGTH_SHORT).show();
        }

        Authentication.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Account successfully created!", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent (register.this, MainActivity.class);
                    startActivity(intent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Account creation failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
