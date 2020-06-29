package com.example.classassistantproject;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by donghwan on 2020.06.06...
 * 강의정보를 파이어스토어에 저장하기 위한 클래스
 */
public class InsertInfoActivity extends AppCompatActivity {

    private static final String TAG = "InsertInfoActivity";

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    //Views
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
    Button btn_save;

    //progress dialog
    ProgressDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_info);

        //activity_insert_info.sml의 View들 활성화
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

        //파이어베이스 파이어스토어
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        //progress dialog
        pd = new ProgressDialog(this);

        //강의목록 저장버튼 클릭리스너
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //input data
                String year = ed_insert_year.getText().toString().trim(); //해당연도
                String term = ed_insert_term.getText().toString().trim(); //해당학기
                String univ = ed_insert_univ.getText().toString().trim(); //학적구분
                String grade = ed_insert_grade.getText().toString().trim(); //해당학년
                String area = ed_insert_area.getText().toString().trim(); //신청구분

                String major = ed_insert_major.getText().toString().trim(); //개설학과
                String title = ed_insert_title.getText().toString().trim(); //교과목명
                String cid = ed_insert_cid.getText().toString().trim(); //과목번호
                String div = ed_insert_div.getText().toString().trim(); //분반
                String credit = ed_insert_credit.getText().toString().trim(); //학점

                String time = ed_insert_time.getText().toString().trim(); //강의시간
                String prof = ed_insert_prof.getText().toString().trim(); //담당교수
                String room = ed_insert_room.getText().toString().trim(); //강의실
                String psl = ed_insert_psl.getText().toString().trim(); //제한인원

                //function call to upload data
                uploadData(year, term, univ, grade, area, major, time, cid, div, credit, time, prof, room, psl);
            }


        }) ;
    }

    //파이어스토어에 강의정보를 올려주는 메소드
    private void uploadData(String year, String term, String univ, String grade, String area, String major, String title, String cid, String div, String credit, String time, String prof, String room, String psl) {

        //set title of progress bar
        pd.setTitle("강의정보 등록중...");
        //show progress bar when user clike save button
        pd.show();
        //random id for each data to be stored
        String id = UUID.randomUUID().toString();

        //빈칸 여부 체크
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

        //강의정보 해쉬맵
        Map<String, Object> infoMap = new HashMap<>();
        Map<String, Object> userMap2 = new HashMap<>();
        infoMap.put("courseYear", year);
        infoMap.put("courseTerm", term);
        infoMap.put("courseUniversity", univ);
        infoMap.put("courseGrade", grade);
        infoMap.put("courseArea", area);

        infoMap.put("courseMajor", major);
        infoMap.put("courseTitle", title);
        infoMap.put("courseID", cid);
        infoMap.put("courseDivide", div);
        infoMap.put("courseCredit", credit);

        infoMap.put("courseTime", time);
        infoMap.put("courseProfessor", prof);
        infoMap.put("courseRoom", room);
        infoMap.put("coursePersonal", psl);

        //경쟁률 기능 데이터 추가
        userMap2.put("courseProfessor", prof);
        userMap2.put("courseTitle", title);
        userMap2.put("coursePersonal", psl);
        userMap2.put("courseOccupancy", 24);

        fStore.collection("elective").document(title)
                .set(infoMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //검색에 성공하였을 경우 실행
                        pd.dismiss();
                        Toast.makeText(InsertInfoActivity.this, "등록되었습니다!",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //검색에 실페하였을 경우 실행
                        pd.dismiss();
                        //오류메시지 get
                        Log.d("error",e.getMessage());
                        Toast.makeText(InsertInfoActivity.this, "오류가 발생했습니다!",Toast.LENGTH_SHORT).show();
                    }
                });

        //경쟁률 테이블에도 데이터 추가
        fStore.collection("competition").document(title)
                .set(userMap2)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(InsertInfoActivity.this, "경쟁률정보 등록 성공",Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("error",e.getMessage());
                        Toast.makeText(InsertInfoActivity.this, "경쟁률정보 등록 실패, 오류 발생",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}