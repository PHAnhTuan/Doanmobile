package com.example.doanmobile.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanmobile.R;

public class theorypage extends AppCompatActivity {
    Button Grammar, Vocabulary, Quit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theory);
        Grammar = findViewById(R.id.btngrammar);
        Vocabulary = findViewById(R.id.btnvocabulary);
        Quit = findViewById(R.id.btnQuit);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.buttonclick);

        Grammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent = new Intent(theorypage.this, theory_grammar.class);
                startActivity(intent);
            }
        });

        Vocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent1 = new Intent(theorypage.this, theory_vocabulary.class);
                startActivity(intent1);
            }
        });

        Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent2 = new Intent(theorypage.this, MainActivity.class);
                startActivity(intent2);
                finish();
            }
        });
    }
}
