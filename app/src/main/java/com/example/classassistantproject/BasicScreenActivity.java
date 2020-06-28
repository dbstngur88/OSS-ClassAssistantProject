package com.example.classassistantproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class BasicScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_screen);

        findViewById(R.id.courseButton).setOnClickListener(onClickListener);
        findViewById(R.id.logoutBtn).setOnClickListener(onClickListener);
        findViewById(R.id.LectureButton).setOnClickListener(onClickListener);
        findViewById(R.id.statisticsButton).setOnClickListener(onClickListener);
        findViewById(R.id.txt_insert_info).setOnClickListener(onClickListener);


        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            //현재유저가 null이면 - 로그인이 안됐을때.
            Intent intent = new Intent(BasicScreenActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }//onCreate()

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {

                case R.id.courseButton:
                    intent = new Intent(BasicScreenActivity.this, ClassActivity.class);
                    startActivity(intent);
                    break;

                case R.id.logoutBtn:
                    //로그아웃
                    FirebaseAuth.getInstance().signOut();
                    //로그인Activity로 돌아가기
                    intent = new Intent(BasicScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;

                case R.id.LectureButton:
                    intent = new Intent(BasicScreenActivity.this, LectureActivity.class);
                    startActivity(intent);
                    break;

                case R.id.statisticsButton:
                    intent = new Intent(BasicScreenActivity.this, CompetsubSchActivity.class);
                    startActivity(intent);
                    break;

                case R.id.txt_insert_info:
                    intent = new Intent(BasicScreenActivity.this, InsertInfoActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}