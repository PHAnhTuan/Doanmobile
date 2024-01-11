package com.example.doanmobile.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;


import com.example.doanmobile.R;
import com.example.doanmobile.data.player1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class quiz_highscore extends Activity {
    ImageButton Back;
    ListView ListPlayer;

    ArrayList<player1> list = new ArrayList<player1>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_highscore);

        Back = (ImageButton) findViewById(R.id.btnreturn);
        ListPlayer = (ListView) findViewById(R.id.ListView);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.buttonclick);

        Print();
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent = new Intent(quiz_highscore.this, minigame_quiz.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void Print() {
        list.clear();
        readFromFile();
        sortDiem();
        ArrayList<String> temp = new ArrayList<String>();
        for (player1 in : list) {
            temp.add(in.toString1());
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, temp);
        ListPlayer.setAdapter(adapter1);
    }

    public void readFromFile() {
        try {
            String splitBy = ",";
            FileInputStream in = this.openFileInput("nguoichoi.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while (br != null) {
                String line = br.readLine();
                String[] value = line.split(splitBy);
                list.add(new player1(value[0], Integer.parseInt(value[1])));
            }
            br.close();
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    public void sortDiem() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            list.sort(new Comparator<player1>() {
                @Override
                public int compare(player1 s, player1 s1) {
                    if (s.getDiem() < s1.getDiem()) return 1;
                    if (s.getDiem() == s1.getDiem()) return 0;
                    return -1;
                }
            });
        }
    }
}
