package com.example.doanmobile.activity;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanmobile.R;

public class minigame_quiz extends AppCompatActivity {
    Button Start, HighScore, Back, Tutorial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame_quiz);

        Start = (Button)findViewById(R.id.btnstart);
        HighScore = (Button)findViewById(R.id.btnhighscore);
        Back = (Button)findViewById(R.id.btnQuit);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.buttonclick);

        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent = new Intent(minigame_quiz.this, player_name.class);
                startActivity(intent);
            }
        });

        HighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent1 = new Intent(minigame_quiz.this, quiz_highscore.class);
                startActivity(intent1);
            }
        });


        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent3 = new Intent(minigame_quiz.this, homepage.class);
                startActivity(intent3);
                finish();
            }
        });
    }
}
