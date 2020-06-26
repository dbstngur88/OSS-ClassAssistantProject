package com.example.classassistantproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class CompetsubSchActivity extends AppCompatActivity {

    private static final String TAG = "SubSearchProcess";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);

        findViewById(R.id.SearchBtn).setOnClickListener(onClickListener);
        findViewById(R.id.GoBackBtn).setOnClickListener(onClickListener);
        findViewById(R.id.GoEvaluationBtn).setOnClickListener(onClickListener);

    }


    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent;
            switch(v.getId()){
                case R.id.SearchBtn:
                    startToast("검색을 시도합니다..");
                    schSub();
                    break;
                case R.id.GoBackBtn:
                    //onBackPressed();
                    intent = new Intent(CompetsubSchActivity.this, InsertCompetDataActivity.class); //임시코드
                    startActivity(intent); //임시코드
                    break;
                case R.id.GoEvaluationBtn:
                    intent = new Intent(CompetsubSchActivity.this, RatingActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    private void schSub(){
        String getSub = ((EditText)findViewById(R.id.SchSubField)).getText().toString();

        if(getSub.length() >0 ){
            startToast(getSub+"로 검색합니다...");
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            final FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("courseList")     //TestCode, 수정필요(courseList ->competition)
                    .whereEqualTo("courseTitle", getSub)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    TextView SubName = (TextView) findViewById(R.id.SubNameView) ;              //과목 명
                                    TextView SubProfessor = (TextView) findViewById(R.id.SubProfessorView) ;    //교수 명
                                    TextView SubPersonal = (TextView) findViewById(R.id.SubPersonalView) ;      //수강 총원
                                    TextView SubOccupancy = (TextView) findViewById(R.id.SubOccupancyView) ;    //신청 인원
                                    TextView SubRate = (TextView) findViewById(R.id.SubRateView) ;              //과목 경쟁률

                                    String subName = (String) document.get("courseTitle");
                                    String subProfessor = (String) document.get("courseProfessor");
                                    Long subOccupancy = (Long) document.get("coursePersonal") + 10;    //TestCode, 수정필요(("coursePersonal") + 10 -> (("courseOccupancy"))
                                    Long subPersonal = (Long) document.get("coursePersonal");
                                    Double subRate = (double)(subOccupancy) / (double)(subPersonal);

                                    SubName.setText(subName) ;
                                    SubProfessor.setText(subProfessor);
                                    SubPersonal.setText(Long.toString(subPersonal));
                                    SubOccupancy.setText(Long.toString(subOccupancy));
                                    SubRate.setText("1 : "+ subRate);

                                }
                            } else {
                                    startToast("일치하는 결과가 없습니다.");
                            }
                        }
                    });


        }else startToast("검색할 과목을 입력해주세요.");

    }

    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}