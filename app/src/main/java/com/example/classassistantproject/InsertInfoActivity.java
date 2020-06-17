package com.example.classassistantproject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class InsertInfoActivity extends AppCompatActivity {
    Button btn_save;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    EditText ed_insert_year;
    EditText ed_insert_term;
    EditText ed_insert_univ;
    EditText ed_insert_grade;
    EditText ed_insert_area;

    EditText ed_insert_major;
    EditText ed_insert_title;
    EditText ed_insert_cid;
    EditText ed_insert_div;
    EditText ed_insert_credit;

    EditText ed_insert_time;
    EditText ed_insert_prof;
    EditText ed_insert_room;
    EditText ed_insert_psl;

    private static final String TAG = "InsertInfoActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_info);

        // component
        btn_save = (Button) findViewById(R.id.btn_save);
        ed_insert_year = (EditText) findViewById(R.id.ed_insert_year);
        ed_insert_term = (EditText) findViewById(R.id.ed_insert_term);
        ed_insert_univ = (EditText) findViewById(R.id.ed_insert_univ);
        ed_insert_grade = (EditText) findViewById(R.id.ed_insert_grade);
        ed_insert_area = (EditText) findViewById(R.id.ed_insert_area);

        ed_insert_major = (EditText) findViewById(R.id.ed_insert_major);
        ed_insert_title = (EditText) findViewById(R.id.ed_insert_title);
        ed_insert_cid = (EditText) findViewById(R.id.ed_insert_cid);
        ed_insert_div = (EditText) findViewById(R.id.ed_insert_div);
        ed_insert_credit = (EditText) findViewById(R.id.ed_insert_credit);

        ed_insert_time = (EditText) findViewById(R.id.ed_insert_time);
        ed_insert_prof = (EditText) findViewById(R.id.ed_insert_prof);
        ed_insert_room = (EditText) findViewById(R.id.ed_insert_room);
        ed_insert_psl = (EditText) findViewById(R.id.ed_insert_psl);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser()!= null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        // 저장 버튼 클릭
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String year = ed_insert_year.getText().toString().trim();
                String term = ed_insert_term.getText().toString().trim();
                String univ = ed_insert_univ.getText().toString().trim();
                String grade = ed_insert_grade.getText().toString().trim();
                String area = ed_insert_area.getText().toString().trim();

                String major = ed_insert_major.getText().toString().trim();
                String title = ed_insert_title.getText().toString().trim();
                String cid = ed_insert_cid.getText().toString().trim();
                String div = ed_insert_div.getText().toString().trim();
                String credit = ed_insert_credit.getText().toString().trim();

                String time = ed_insert_time.getText().toString().trim();
                String prof = ed_insert_prof.getText().toString().trim();
                String room = ed_insert_room.getText().toString().trim();
                String psl = ed_insert_psl.getText().toString().trim();
            }

        });
    }
}