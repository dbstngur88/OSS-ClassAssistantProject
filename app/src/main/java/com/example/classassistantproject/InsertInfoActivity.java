package com.example.classassistantproject;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


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

        //
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


                // 빈칸 여부 체크. 추후 타입별 수정 예정...
               if (TextUtils.isEmpty(year)) {
                    ed_insert_year.setError("연도를 입력해주세요.");
                    return;
                }

                if (TextUtils.isEmpty(term)) {
                    ed_insert_term.setError("학기를 입력해주세요.");
                    return;
                }

                if (TextUtils.isEmpty(univ)) {
                    ed_insert_univ.setError("학적구분을 입력해주세요.");
                    return;
                }

                if (TextUtils.isEmpty(grade)) {
                    ed_insert_grade.setError("학년을 입력해주세요.");
                    return;
                }

                if (TextUtils.isEmpty(area)) {
                    ed_insert_area.setError("신청구분을 입력해주세요.");
                    return;
                }

                if (TextUtils.isEmpty(major)) {
                    ed_insert_major.setError("개설학과를 입력해주세요.");
                    return;
                }

                if (TextUtils.isEmpty(title)) {
                    ed_insert_title.setError("교과목명을 입력해주세요.");
                    return;
                }

                if (TextUtils.isEmpty(cid)) {
                    ed_insert_cid.setError("과목번호를 입력해주세요.");
                    return;
                }

                if (TextUtils.isEmpty(div)) {
                    ed_insert_div.setError("분반을 입력해주세요.");
                    return;
                }

                if (TextUtils.isEmpty(credit)) {
                    ed_insert_credit.setError("학점을 입력해주세요.");
                    return;
                }

                if (TextUtils.isEmpty(time)) {
                    ed_insert_time.setError("강의시간을 입력해주세요.");
                    return;
                }

                if (TextUtils.isEmpty(prof)) {
                    ed_insert_prof.setError("담당교수님 성함을 입력해주세요.");
                    return;
                }

                if (TextUtils.isEmpty(room)) {
                    ed_insert_room.setError("강의실을 입력해주세요.");
                    return;
                }

                if (TextUtils.isEmpty(psl)) {
                    ed_insert_psl.setError("제한인원을 입력해주세요.");
                    return;
                }

                Map<String, String> userMap = new HashMap<>();

                userMap.put("courseYear", year);
                userMap.put("courseTerm", term);
                userMap.put("courseUniversity", univ);
                userMap.put("courseGrade", grade);
                userMap.put("courseArea", area);

                userMap.put("courseMajor", major);
                userMap.put("courserTitle", title);
                userMap.put("courseID", cid);
                userMap.put("courseDivide", div);
                userMap.put("courseYear", credit);

                userMap.put("courseTime", time);
                userMap.put("courseProfessor", prof);
                userMap.put("courseRoom", room);
                userMap.put("coursePersonnel", psl);

                fStore.collection("courseList").document(title)
                .set(userMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(InsertInfoActivity.this, "강의정보가 성공적으로 등록되었습니다!",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("errer",e.getMessage());
                                Toast.makeText(InsertInfoActivity.this, "오류가 발생했습니다!",Toast.LENGTH_SHORT).show();
                            }
                        });
            }

        }) ;
    }
}