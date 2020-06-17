package com.example.classassistantproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button btn_sign_in;
    TextView txt_sign_up;
    TextView txt_insert_info;

    EditText ed_email;
    EditText ed_pw;


    // 파이어베이스 인스턴스
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
        txt_sign_up = (TextView) findViewById(R.id.txt_sign_up);
        txt_insert_info = (TextView) findViewById(R.id.txt_insert_info);
        ed_email = (EditText) findViewById(R.id.ed_email);
        ed_pw = (EditText) findViewById(R.id.ed_pw);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();


        // 로그인 버튼 클릭
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signInWithEmailAndPassword(ed_email.getText().toString(), ed_pw.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            db.getReference("User").child(ed_email.getText().toString().replace('.', ' ')).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    // dataSnapshot.chile(키).getValue 로 학과, 학번, 이름 등 가져옴
                                    Log.d(TAG, "onDataChange: " + dataSnapshot.child("num").getValue());

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        } else
                            Toast.makeText(MainActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // 회원가입 버튼 클릭
        txt_sign_up.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        // 선문대 로고 클릭
        txt_insert_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InsertInfoActivity.class);
                startActivity(intent);
            }
        });




        //commit and push test code
    }

    private static final String TAG = "MainActivity";
}