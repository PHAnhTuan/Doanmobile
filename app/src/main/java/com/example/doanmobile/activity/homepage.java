package com.example.doanmobile.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanmobile.R;

public class homepage extends AppCompatActivity {
    Button btntheory, btnminigame, btnquit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        btntheory = findViewById(R.id.btnTheory);
        btnminigame = findViewById(R.id.btnGame);
        btnquit = findViewById(R.id.btnQuit);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.buttonclick);

        btntheory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent = new Intent(homepage.this, theory.class);
                startActivity(intent);
            }
        });

        btnminigame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent1 = new Intent(homepage.this, minigame_correct.class);
                startActivity(intent1);
            }
        });

        btnquit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent2 = new Intent(homepage.this, MainActivity.class);
                startActivity(intent2);
                finish();
            }
        });
    }
}
