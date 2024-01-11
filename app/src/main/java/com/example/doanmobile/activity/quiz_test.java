package com.example.doanmobile.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanmobile.R;
import com.example.doanmobile.data.Question;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

class QuestionNare {
    public String ID;
    public String AnswerA, AnswerB, AnswerC, AnswerD, Answer;
}

public class quiz_test extends AppCompatActivity {
    TextView KetQua, CauHoi, ThoiGian;
    ImageView HinhAnh;
    RadioGroup RG;
    Button TraLoi, BoQua;

    ImageButton KetThuc;
    RadioButton A, B, C, D;
    int pos = 0;
    int kq = 0;
    CountDownTimer Time;
    public ArrayList<QuestionNare> list = new ArrayList<>();
    public ArrayList<Question> PList = new ArrayList<>();

    public void countdown() {
        Time = new CountDownTimer(21000, 1000) {

            public void onTick(long millisUntilFinished) {
                ThoiGian.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                pos++;
                if (pos >= list.size()) {
                    Intent callerIntent = getIntent();
                    Bundle packageFromCaller = callerIntent.getBundleExtra("bundle");
                    String name = packageFromCaller.getString("name");
                    Intent intent = new Intent(quiz_test.this, quiz_result.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("kq", kq);
                    bundle.putInt("num", pos);
                    bundle.putString("name", name);
                    intent.putExtra("package", bundle);
                    startActivity(intent);
                    finish();
                } else Display(pos);
            }
        }.start();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_test);

        PList = new ArrayList<>();

        CauHoi = (TextView) findViewById(R.id.qs);
        KetQua = (TextView) findViewById(R.id.Result);
        RG = (RadioGroup) findViewById(R.id.RadioGroup);
        A = (RadioButton) findViewById(R.id.rdbA);
        B = (RadioButton) findViewById(R.id.rdbB);
        C = (RadioButton) findViewById(R.id.rdbC);
        D = (RadioButton) findViewById(R.id.rdbD);
        TraLoi = (Button) findViewById(R.id.Answer);
        HinhAnh = (ImageView) findViewById(R.id.qsimage);

        BoQua = (Button) findViewById(R.id.Skip);
        ThoiGian = (TextView) findViewById(R.id.time);
        KetThuc = (ImageButton) findViewById(R.id.btnreturn);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.buttonclick);

        AddQuestionFromFileTXT();
        CreateQuestion();
        Display(pos);

        KetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(quiz_test.this, minigame_quiz.class);
                startActivity(intent);
                finish();
            }
        });



        BoQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Time.cancel();
                mediaPlayer.start();
                kq = kq + 1;
                pos++;
                Display(pos);
                BoQua.setVisibility(View.INVISIBLE);
            }
        });

        TraLoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Time.cancel();
                mediaPlayer.start();
                int idCheck = RG.getCheckedRadioButtonId();
                if (idCheck == R.id.rdbA) {
                    if (list.get(pos).Answer.compareTo("A") == 0) kq = kq + 1;
                } else if (idCheck == R.id.rdbB) {
                    if (list.get(pos).Answer.compareTo("B") == 0) kq = kq + 1;
                } else if (idCheck == R.id.rdbC) {
                    if (list.get(pos).Answer.compareTo("C") == 0) kq = kq + 1;
                } else if (idCheck == R.id.rdbD) {
                    if (list.get(pos).Answer.compareTo("D") == 0) kq = kq + 1;
                }
                pos++;
                if (pos >= list.size()) {
                    Intent callerIntent = getIntent();
                    Bundle packageFromCaller = callerIntent.getBundleExtra("bundle");
                    String name = packageFromCaller.getString("name");
                    Intent intent1 = new Intent(quiz_test.this, quiz_result.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("kq", kq);
                    bundle.putInt("num", pos);
                    bundle.putString("name", name);
                    intent1.putExtra("package", bundle);
                    startActivity(intent1);
                    pos = 0;
                    kq = 0;
                    Display(pos);
                    finish();
                } else Display(pos);
            }
        });
    }

    void Display(int i) {
        countdown();
        int resID = getResources().getIdentifier(list.get(i).ID, "drawable", getPackageName());
        HinhAnh.setImageResource(resID);
        A.setText(list.get(i).AnswerA);
        B.setText(list.get(i).AnswerB);
        C.setText(list.get(i).AnswerC);
        D.setText(list.get(i).AnswerD);
        KetQua.setText("Right sentence: " + kq);
        RG.clearCheck();
        A.setVisibility(View.VISIBLE);
        B.setVisibility(View.VISIBLE);
        C.setVisibility(View.VISIBLE);
        D.setVisibility(View.VISIBLE);
    }

    public void AddQuestionFromFileTXT() {
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("Question.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String splitBy = ",";
            String line;

            while ((line = br.readLine()) != null) {
                String[] value = line.split(splitBy);
                PList.add(new Question(value[1], Integer.parseInt(value[0])));
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CreateQuestion() {
        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("bundle");
        int number = packageFromCaller.getInt("number");

        for (int i = 0; i <= number -1; i++) {
            QuestionNare Q = new QuestionNare();
            Random generator = new Random();

            // Gán câu trả lời A ngẫu nhiên từ PList
            Q.AnswerA = PList.get(generator.nextInt(PList.size())).getName();

            // Gán câu trả lời B khác với câu trả lời A
            do {
                Q.AnswerB = PList.get(generator.nextInt(PList.size())).getName();
            } while (Q.AnswerA.equals(Q.AnswerB));

            // Gán câu trả lời C khác với câu trả lời A và B
            do {
                Q.AnswerC = PList.get(generator.nextInt(PList.size())).getName();
            } while (Q.AnswerC.equals(Q.AnswerB) || Q.AnswerC.equals(Q.AnswerA));

            // Gán câu trả lời D khác với câu trả lời A, B và C
            do {
                Q.AnswerD = PList.get(generator.nextInt(PList.size())).getName();
            } while (Q.AnswerD.equals(Q.AnswerC) || Q.AnswerD.equals(Q.AnswerB) || Q.AnswerD.equals(Q.AnswerA));

            // Chọn ngẫu nhiên câu trả lời đúng và gán giá trị Answer tương ứng
            int value = generator.nextInt(4);
            int find = 0;

            switch (value) {
                case 0:
                    find = PList.indexOf(searchQuestion(Q.AnswerA));
                    Q.Answer = "A";
                    break;
                case 1:
                    find = PList.indexOf(searchQuestion(Q.AnswerB));
                    Q.Answer = "B";
                    break;
                case 2:
                    find = PList.indexOf(searchQuestion(Q.AnswerC));
                    Q.Answer = "C";
                    break;
                case 3:
                    find = PList.indexOf(searchQuestion(Q.AnswerD));
                    Q.Answer = "D";
                    break;
            }

            // Gán ID cho câu hỏi và thêm vào danh sách
            Q.ID = "a" + PList.get(find).getId();
            list.add(Q);
        }
    }

    public Question searchQuestion(String code) {
        for (Question in : PList) {
            if (in.getName().equalsIgnoreCase(code)) {
                return in;
            }
        }
        return null;
    }
}