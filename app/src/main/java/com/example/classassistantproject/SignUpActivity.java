package com.example.classassistantproject;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {
    Button btn_finish;

    EditText ed_sign_name;
    EditText ed_sign_email;
    EditText ed_sign_pw;
    EditText ed_sign_num;
    EditText ed_sign_phone;

    Spinner spinner;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseDatabase database;
    DatabaseReference table_user;

    private static final String TAG = "SignUpActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // component
        btn_finish = (Button) findViewById(R.id.btn_finish);
        ed_sign_name = (EditText) findViewById(R.id.ed_sign_name);
        ed_sign_num = (EditText) findViewById(R.id.ed_sign_num);
        ed_sign_email = (EditText) findViewById(R.id.ed_sign_email);
        ed_sign_pw = (EditText) findViewById(R.id.ed_sign_pw);
        ed_sign_phone = (EditText) findViewById(R.id.ed_sign_phone);
        spinner = (Spinner) findViewById(R.id.spinner);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser()!= null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed_sign_name.getText().toString().trim();
                String num = ed_sign_num.getText().toString().trim();
                String email = ed_sign_email.getText().toString().trim();
                String pw = ed_sign_pw.getText().toString().trim();
                String phone = ed_sign_phone.getText().toString().trim();

                // 빈칸 여부와 비밀번호 최소길이 확
                if (TextUtils.isEmpty(email)) {
                    ed_sign_email.setError("이메일을 입력해주세요.");
                    return;
                }

                if (TextUtils.isEmpty(pw)) {
                    ed_sign_pw.setError("비밀번호를 입력해주세요.");
                    return;
                }

                if (pw.length() < 6 ) {
                    ed_sign_pw.setError("비밀번호는 6자리 이상입니다.");
                    return;
                }


                fAuth.createUserWithEmailAndPassword(email, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task .isSuccessful()) {
                             Toast.makeText(SignUpActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                             startActivity(new Intent(getApplicationContext(),MainActivity.class));
                         } else{
                             Toast.makeText(SignUpActivity.this, "회원가입에 실패했습니다." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                         }
                    }
                });
            }
        });
    }
}
