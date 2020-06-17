package com.example.classassistantproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class BasicScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_screen);

        findViewById(R.id.logoutBtn).setOnClickListener(onClickListener);
        findViewById(R.id.statisticsButton).setOnClickListener(onClickListener);


        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            //현재유저가 null이면 - 로그인이 안됐을때.
            startMainActivity();
        }
    }//onCreate()

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.statisticsButton:
                    //통계버튼
                    //경쟁률 기능의 과목 검색기능으로 넘어가기
                    startToast("경쟁률 기능으로 이동합니다.");
                    startstatisticsActivity();
                    break;
                case R.id.logoutBtn:
                    //로그아웃
                    FirebaseAuth.getInstance().signOut();
                    //로그인Activity로 돌아가기
                    startMainActivity();
                    break;

            }
        }
    };

    private void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void startstatisticsActivity(){
        Intent intent = new Intent(this, CompetsubSchActivity.class);
        startActivity(intent);
    }
    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}