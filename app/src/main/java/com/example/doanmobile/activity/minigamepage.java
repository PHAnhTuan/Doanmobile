package com.example.doanmobile.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanmobile.R;

public class minigamepage extends AppCompatActivity {
    Button Quiz, Correct, Quit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame);
        Quiz = findViewById(R.id.btnQuiz);
        Correct = findViewById(R.id.btncorrect);
        Quit = findViewById(R.id.btnQuit);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.buttonclick);

        Quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent = new Intent(minigamepage.this, theory.class);
                startActivity(intent);
            }
        });

        Correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent1 = new Intent(minigamepage.this, minigame_correct.class);
                startActivity(intent1);
            }
        });

        Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent2 = new Intent(minigamepage.this, MainActivity.class);
                startActivity(intent2);
                finish();
            }
        });
    }
}
