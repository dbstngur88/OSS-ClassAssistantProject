package com.example.classassistantproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class LectureActivity extends AppCompatActivity {

    private TextView mon[] = new TextView[10];
    private TextView tue[] = new TextView[10];
    private TextView wed[] = new TextView[10];
    private TextView thu[] = new TextView[10];
    private TextView fri[] = new TextView[10];
    private Lecture lecture = new Lecture();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);

        //월 ~ 금까지 각각의 ID와 매칭
        mon[0] = findViewById(R.id.monday0);
        mon[1] = findViewById(R.id.monday1);
        mon[2] = findViewById(R.id.monday2);
        mon[3] = findViewById(R.id.monday3);
        mon[4] = findViewById(R.id.monday4);
        mon[5] = findViewById(R.id.monday5);
        mon[6] = findViewById(R.id.monday6);
        mon[7] = findViewById(R.id.monday7);
        mon[8] = findViewById(R.id.monday8);
        mon[9] = findViewById(R.id.monday9);

        tue[0] = findViewById(R.id.tuesday0);
        tue[1] = findViewById(R.id.tuesday1);
        tue[2] = findViewById(R.id.tuesday2);
        tue[3] = findViewById(R.id.tuesday3);
        tue[4] = findViewById(R.id.tuesday4);
        tue[5] = findViewById(R.id.tuesday5);
        tue[6] = findViewById(R.id.tuesday6);
        tue[7] = findViewById(R.id.tuesday7);
        tue[8] = findViewById(R.id.tuesday8);
        tue[9] = findViewById(R.id.tuesday9);

        wed[0] = findViewById(R.id.wednesday0);
        wed[1] = findViewById(R.id.wednesday1);
        wed[2] = findViewById(R.id.wednesday2);
        wed[3] = findViewById(R.id.wednesday3);
        wed[4] = findViewById(R.id.wednesday4);
        wed[5] = findViewById(R.id.wednesday5);
        wed[6] = findViewById(R.id.wednesday6);
        wed[7] = findViewById(R.id.wednesday7);
        wed[8] = findViewById(R.id.wednesday8);
        wed[9] = findViewById(R.id.wednesday9);

        thu[0] = findViewById(R.id.thursday0);
        thu[1] = findViewById(R.id.thursday1);
        thu[2] = findViewById(R.id.thursday2);
        thu[3] = findViewById(R.id.thursday3);
        thu[4] = findViewById(R.id.thursday4);
        thu[5] = findViewById(R.id.thursday5);
        thu[6] = findViewById(R.id.thursday6);
        thu[7] = findViewById(R.id.thursday7);
        thu[8] = findViewById(R.id.thursday8);
        thu[9] = findViewById(R.id.thursday9);

        fri[0] = findViewById(R.id.friday0);
        fri[1] = findViewById(R.id.friday1);
        fri[2] = findViewById(R.id.friday2);
        fri[3] = findViewById(R.id.friday3);
        fri[4] = findViewById(R.id.friday4);
        fri[5] = findViewById(R.id.friday5);
        fri[6] = findViewById(R.id.friday6);
        fri[7] = findViewById(R.id.friday7);
        fri[8] = findViewById(R.id.friday8);
        fri[9] = findViewById(R.id.friday9);

        String time = "월:[1]수:[1][2]";
        String title = "컴퓨터 네트워크";
        String professor = "김봉재";
        String room = "인문405";

        lecture.addlecture(time, title, professor, room);
        lecture.setting(mon,tue,wed,thu,fri,this);


    } //onCreate()

}