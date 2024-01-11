package com.example.doanmobile.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanmobile.R;

public class minigame_correct extends AppCompatActivity {
    Button BatDau, DiemCao, TroVe, HuongDan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame_correct);

        BatDau = (Button)findViewById(R.id.btnstart);
        DiemCao = (Button)findViewById(R.id.btnhighscore);
        TroVe = (Button)findViewById(R.id.btnQuit);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.buttonclick);

        BatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent = new Intent(minigame_correct.this, player.class);
                startActivity(intent);
            }
        });

        DiemCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent1 = new Intent(minigame_correct.this, correct_highscore.class);
                startActivity(intent1);
            }
        });

        TroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent3 = new Intent(minigame_correct.this, homepage.class);
                startActivity(intent3);
                finish();
            }
        });
    }
}
