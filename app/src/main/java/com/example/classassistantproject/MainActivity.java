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
import com.google.firebase.firestore.FirebaseFirestore;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button btn_sign_in;
    TextView txt_sign_up;

    EditText ed_email;
    EditText ed_pw;

    // 파이어베이스 인스턴스
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
        txt_sign_up = (TextView) findViewById(R.id.txt_sign_up);
        ed_email = (EditText) findViewById(R.id.ed_email);
        ed_pw = (EditText) findViewById(R.id.ed_pw);

        mAuth = FirebaseAuth.getInstance();

        // 로그인 버튼 클릭
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signInWithEmailAndPassword(ed_email.getText().toString(), ed_pw.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            startBasicScreenActivity();
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

    }

    private static final String TAG = "MainActivity";

    private void startBasicScreenActivity(){
        Intent intent = new Intent(this, BasicScreenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //로그인한 상태에서 뒤로가기하면 로그인화면이 다시안뜨고 바로 종료되버리게
        startActivity(intent);
    }

}