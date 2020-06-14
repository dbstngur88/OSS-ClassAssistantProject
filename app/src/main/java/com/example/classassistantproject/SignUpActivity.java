package com.example.classassistantproject;
import android.os.Bundle;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {
    Button btn_finish;

    EditText ed_sign_name;
    EditText ed_sign_email;
    EditText ed_sign_pw;
    EditText ed_sign_num;
    EditText ed_sign_phone;

    Spinner spinner;

    private static final String TAG = "SignUpActivity";
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference table_user;


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

        firebaseAuth = FirebaseAuth.getInstance();

        // firebase db
        database = FirebaseDatabase.getInstance();
        table_user = database.getReference("User");


        // 완료 버튼 클릭
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.createUserWithEmailAndPassword(ed_sign_email.getText().toString(), ed_sign_pw.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    // start db
                                    table_user.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.child(ed_sign_email.getText().toString().replace('.', ' ')).exists()) {
                                            } else {
                                                UserDTO user = new UserDTO(
                                                        ed_sign_name.getText().toString(),
                                                        ed_sign_pw.getText().toString(),
                                                        ed_sign_num.getText().toString(),
                                                        spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString(),
                                                        ed_sign_phone.getText().toString()
                                                );

                                                table_user.child(ed_sign_email.getText().toString().replace('.', ' ')).setValue(user);
                                                Toast.makeText(SignUpActivity.this, "회원가입이 완료 되었습니다.", Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            Log.d(TAG, "onCancelled: " + databaseError.getMessage());

                                        }
                                    });
                                    // end db


                                    finish();


                                } else {
                                    Log.d(TAG, "onCancelled: " + task.getException().getMessage());
                                }
                            }
                        });
            }
        });

    }
}
