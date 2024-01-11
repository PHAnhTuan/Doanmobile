package com.example.doanmobile.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.doanmobile.R;

public class player extends Activity {
    EditText NhapTen;
    Button BatDau, TroVe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);

        NhapTen = findViewById(R.id.btnusername);
        BatDau= findViewById(R.id.btnstart);
        TroVe = findViewById(R.id.Back);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.buttonclick);

        BatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                String name;
                name = NhapTen.getText().toString().trim();
                Intent intent = new Intent(player.this, correct_test.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });

        TroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent1 = new Intent(player.this, minigame_correct.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}
