package com.example.doanmobile.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.doanmobile.R;
import com.example.doanmobile.data.player2;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class correct_result extends Activity {
    Button PlayAgain, Quit;
    TextView Result;
    ArrayList<player2> list = new ArrayList<player2>();
    int Score, Sentence;
    private PieChart PieChart;
    String Name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correct_result);
        Result = (TextView)findViewById(R.id.txtResult);
        PlayAgain = (Button)findViewById(R.id.btnagain);
        Quit = (Button)findViewById(R.id.btnQuit);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.buttonclick);
        final MediaPlayer music = MediaPlayer.create(this,R.raw.winning);
        music.start();
        Intent callerIntent=getIntent();
        Bundle packageFromCaller= callerIntent.getBundleExtra("package");
        Name = packageFromCaller.getString("name");
        Score = packageFromCaller.getInt("KQ");
        Sentence = packageFromCaller.getInt("num");

        readFromFile();

        Result.setText("Number of responses: " + Score + "/" + Sentence);
        PieChart = findViewById(R.id.PieChart);
        setupPieChart();
        loadPieChartData();
        kiemtra();

        Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent = new Intent(correct_result.this, minigame_correct.class);
                startActivity(intent);
                finish();
            }
        });

        PlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent1 = new Intent(correct_result.this, player.class);
                startActivity(intent1);
                finish();
            }
        });
    }

    public void saveToFile(ArrayList<player2> list)
    {
        try
        {
            FileOutputStream outputStream = this.openFileOutput("player2.csv", Context.MODE_PRIVATE);
            PrintWriter pw = new PrintWriter(outputStream);
            for (player2 in:list)
                pw.println(in);
            pw.close();
            outputStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void readFromFile() {
        try{
            String splitBy = ",";
            FileInputStream in = this.openFileInput("player2.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while (br != null) {
                String line = br.readLine();
                String[] value = line.split(splitBy);
                list.add(new player2(value[0], Integer.parseInt(value[1])));
            }
            br.close();
        }
        catch (Exception e){
            System.out.println(""+e.getMessage());
        }
    }

    private void kiemtra() {
        player2 temp = searchnguoichoi(Name);
        if(temp == null) {
            player2 a=new player2(Name,Score);
            list.add(a);
            saveToFile(list);
        }
        else{
            if(temp.getDiem()<Score){
                temp.setdiem(Score);
                saveToFile(list);
            }
        }
    }

    protected player2 searchnguoichoi(String code)
    {
        for (player2 in:list)
        {
            if (in.getName().equalsIgnoreCase(code))
            {
                return in;
            }
        }
        return null;
    }

    private void setupPieChart() {
        PieChart.setDrawHoleEnabled(true);
        PieChart.setUsePercentValues(true);
        PieChart.setEntryLabelTextSize(12);
        PieChart.setEntryLabelColor(Color.BLACK);
        PieChart.setCenterText("Total number of sentences");
        PieChart.setCenterTextSize(24);
        PieChart.getDescription().setEnabled(false);

        Legend l = PieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void loadPieChartData() {
        ArrayList<PieEntry> entries = new ArrayList<>();

        float temp = (float) Score/Sentence;
        entries.add(new PieEntry(temp,"Number of correct sentences"));
        entries.add(new PieEntry(1.0f-temp,"Number of incorrect sentences"));

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet2 = new PieDataSet(entries, "Note");
        dataSet2.setColors(colors);

        PieData data2 = new PieData(dataSet2);
        data2.setDrawValues(true);
        data2.setValueFormatter(new PercentFormatter(PieChart));
        data2.setValueTextSize(12f);
        data2.setValueTextColor(Color.BLACK);

        PieChart.setData(data2);
        PieChart.invalidate();

        PieChart.animateY(1400, Easing.EaseInOutQuad);
    }
}

