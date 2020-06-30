package com.example.classassistantproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CompetsubSchActivity extends AppCompatActivity {

    private static final String TAG = "SubSearchProcess";
    boolean SchPro = false;
    Switch SchSelectSwitch;
    CharSequence[] items;
    String ProfessorselectSubject;  //교수명 검색 후 선택한 과목 값 저장
    String getPro;


    TextView SubName;              //과목 명
    TextView SubProfessor;    //교수 명
    TextView SubPersonal;      //수강 총원
    TextView SubOccupancy;    //신청 인원
    TextView SubRate;              //과목 경쟁률

    String subName = null;
    String subProfessor = null;
    String subOccupancy = null;
    String subPersonal = null;
    Double subRate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);


        findViewById(R.id.SearchBtn).setOnClickListener(onClickListener);
        findViewById(R.id.GoBackBtn).setOnClickListener(onClickListener);
        findViewById(R.id.GoEvaluationBtn).setOnClickListener(onClickListener);
        findViewById(R.id.ActivateBtn).setOnClickListener(onClickListener);


        //스위치의 값에 따라 검색하는 종류가 달라지게 설정
        SchSelectSwitch = (Switch)findViewById(R.id.SchSwitch);
        CheckState();

        SchSelectSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckState();
            }

        });

    }

    private void CheckState(){
        TextView SchOptionView = (TextView)findViewById(R.id.Sch);
        EditText SchOptionText = (EditText)findViewById(R.id.SchSubField);
        if(SchSelectSwitch.isChecked()) {
            SchOptionView.setText("교 수 검 색");
            SchOptionText.setHint("교수명을 입력하세요");
            SchPro = true;
        }
        else{
            SchOptionView.setText("과 목 검 색");
            SchOptionText.setHint("과목명을 입력하세요");
            SchPro = false;
        }

    }
    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent;
            switch(v.getId()){
                case R.id.SearchBtn:
                    //검색 전 시각화 칸 초기화(내용 제거)
                    ((TextView)findViewById(R.id.SubNameView)).setText(null);
                    ((TextView)findViewById(R.id.SubProfessorView)).setText(null);
                    ((TextView)findViewById(R.id.SubPersonalView)).setText(null);
                    ((TextView)findViewById(R.id.SubOccupancyView)).setText(null);
                    ((TextView)findViewById(R.id.SubRateView)).setText(null);

                    if(SchPro == true){
                        startToast("교수명으로 검색을 시도합니다..");
                        schPro();
                    }else {
                        startToast("과목명으로 검색을 시도합니다..");
                        schSub();
                    }
                    break;

                case R.id.GoBackBtn:
                    onBackPressed();

                    //경쟁률 테이블 데이터 추가용 코드, 필요 시 위 코드와 주석을 변경해서 사용
                    //intent = new Intent(CompetsubSchActivity.this, InsertCompetDataActivity.class);
                    //startActivity(intent);

                    break;
                case R.id.GoEvaluationBtn:
                    intent = new Intent(CompetsubSchActivity.this, RatingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.ActivateBtn:
                    schProtoSub();
                    break;
            }
        }
    };

    private void schSub(){
        String getSub = ((EditText)findViewById(R.id.SchSubField)).getText().toString();

        if(getSub.length() >0 ){
            startToast(getSub+"과목으로 검색합니다...");
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            final FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("competition")     //TestCode, 수정필요(courseList ->competition)
                    .whereEqualTo("courseTitle", getSub)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    SubName = (TextView) findViewById(R.id.SubNameView) ;              //과목 명
                                    SubProfessor = (TextView) findViewById(R.id.SubProfessorView) ;    //교수 명
                                    SubPersonal = (TextView) findViewById(R.id.SubPersonalView) ;      //수강 총원
                                    SubOccupancy = (TextView) findViewById(R.id.SubOccupancyView) ;    //신청 인원
                                    SubRate = (TextView) findViewById(R.id.SubRateView) ;              //과목 경쟁률

                                    subName = (String) document.get("courseTitle");
                                    subProfessor = (String) document.get("courseProfessor");
                                    subOccupancy = (String) document.get("courseOccupancy");
                                    subPersonal = (String) document.get("coursePersonal");

                                    Double subRate = Double.parseDouble(subOccupancy) / Double.parseDouble(subPersonal);

                                    SubName.setText(subName) ;
                                    SubProfessor.setText(subProfessor);
                                    SubPersonal.setText(subPersonal);
                                    SubOccupancy.setText(subOccupancy);
                                    SubRate.setText("1 : "+ subRate);

                                }
                            } else {
                                    startToast("일치하는 결과가 없습니다.");
                            }
                        }
                    });


        }else startToast("검색할 과목을 입력해주세요.");

    }

    private void schPro(){
        getPro = ((EditText)findViewById(R.id.SchSubField)).getText().toString();
        if(getPro.length() >0 ){
            startToast(getPro+"교수님으로 검색합니다...");
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            final FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("competition")
                    .whereEqualTo("courseProfessor", getPro)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                List<String> titleList = new ArrayList<>();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    titleList.add(document.get("courseTitle").toString());  //과목 명들의 정보를 titleList 배열에 입력
                                    items =  titleList.toArray(new String[ titleList.size()]); //아이템들의 정보를 배열에 입력

                                    SubName = (TextView) findViewById(R.id.SubNameView) ;              //과목 명
                                    SubProfessor = (TextView) findViewById(R.id.SubProfessorView) ;    //교수 명
                                    SubPersonal = (TextView) findViewById(R.id.SubPersonalView) ;      //수강 총원
                                    SubOccupancy = (TextView) findViewById(R.id.SubOccupancyView) ;    //신청 인원
                                    SubRate = (TextView) findViewById(R.id.SubRateView) ;              //과목 경쟁률

                                    SubName.setText(null) ;
                                    SubProfessor.setText(null);
                                    SubPersonal.setText(null);
                                    SubOccupancy.setText(null);
                                    SubRate.setText(null);

                                    if(titleList.size()>1){
                                        subName = showSubject();
                                        startToast("선택 완료 이후\n검색 결과 적용을 눌러주세요.");
                                    }else{
                                        subName = (String) document.get("courseTitle");
                                        subProfessor = (String) document.get("courseProfessor");
                                        subOccupancy = (String) document.get("courseOccupancy");
                                        subPersonal = (String) document.get("coursePersonal");
                                        subRate = Double.parseDouble(subOccupancy) / Double.parseDouble(subPersonal);


                                        SubName.setText(subName) ;
                                        SubProfessor.setText(subProfessor);
                                        SubPersonal.setText(subPersonal);
                                        SubOccupancy.setText(subOccupancy);
                                        SubRate.setText("1 : "+ subRate);
                                    }
                                }
                            } else {
                                startToast("일치하는 결과가 없습니다.");
                            }
                        }
                    });


        }else startToast("검색할 과목을 입력해주세요.");

    }


    private void schProtoSub(){
        getPro = ((EditText)findViewById(R.id.SchSubField)).getText().toString();
        if(getPro.length() >0 ){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            final FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("competition")
                    .whereEqualTo("courseProfessor", getPro)
                    .whereEqualTo("courseTitle", ProfessorselectSubject)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    SubName = (TextView) findViewById(R.id.SubNameView) ;              //과목 명
                                    SubProfessor = (TextView) findViewById(R.id.SubProfessorView) ;    //교수 명
                                    SubPersonal = (TextView) findViewById(R.id.SubPersonalView) ;      //수강 총원
                                    SubOccupancy = (TextView) findViewById(R.id.SubOccupancyView) ;    //신청 인원
                                    SubRate = (TextView) findViewById(R.id.SubRateView) ;              //과목 경쟁률

                                    SubName.setText(null) ;
                                    SubProfessor.setText(null);
                                    SubPersonal.setText(null);
                                    SubOccupancy.setText(null);
                                    SubRate.setText(null);

                                        subName = (String) document.get("courseTitle");
                                        subProfessor = (String) document.get("courseProfessor");
                                        subOccupancy = (String) document.get("courseOccupancy");
                                        subPersonal = (String) document.get("coursePersonal");
                                        subRate = Double.parseDouble(subOccupancy) / Double.parseDouble(subPersonal);

                                        SubName.setText(subName) ;
                                        SubProfessor.setText(subProfessor);
                                        SubPersonal.setText(subPersonal);
                                        SubOccupancy.setText(subOccupancy);
                                        SubRate.setText("1 : "+ subRate);
                                }
                            } else {
                                startToast("일치하는 결과가 없습니다.");
                            }
                        }
                    });


        }else startToast("검색할 과목을 입력해주세요.");

    }

    String showSubject()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("조회 희망 과목 선택");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int pos) {
                String selectedText = items[pos].toString();
                ProfessorselectSubject = selectedText;
                startToast(selectedText);
                dialog.dismiss();
            }
        });
        builder.show();
        return ProfessorselectSubject;
    }



    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}