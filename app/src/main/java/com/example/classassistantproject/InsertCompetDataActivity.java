package com.example.classassistantproject;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class InsertCompetDataActivity extends AppCompatActivity {

    private static final String TAG = "SubSearchProcess";
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcompetition_info);
        findViewById(R.id.saveBtn).setOnClickListener(onClickListener);

        fStore = FirebaseFirestore.getInstance();

    }


    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent;
            switch(v.getId()){
                case R.id.saveBtn:
                    insertData();
                    break;
            }
        }
    };
    private void insertData(){
        String insProfessor = ((EditText)findViewById(R.id.insertProfessorField)).getText().toString();
        String insSubject = ((EditText)findViewById(R.id.insertSubField)).getText().toString();
        String insPersonal = ((EditText)findViewById(R.id.insertPersonalField)).getText().toString();
        String insOccupancy = ((EditText)findViewById(R.id.insertOccupancyField)).getText().toString();


        //빈칸 여부 확인 구문
        if (TextUtils.isEmpty(insProfessor)) {
            startToast("교수명을 입력하세요..");
            return;
        }
        if (TextUtils.isEmpty(insSubject)) {
            startToast("과목명을 입력하세요..");
            return;
        }
        if (TextUtils.isEmpty(insPersonal)) {
            startToast("수강총원을 입력하세요..");
            return;
        }
        if (TextUtils.isEmpty(insOccupancy)) {
            startToast("수강 신청 인원을 입력하세요..");
            return;
        }

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("courseProfessor", insProfessor);
        userMap.put("courseTitle", insSubject);
        userMap.put("coursePersonal", insPersonal);
        userMap.put("courseOccupancy", insOccupancy);

        fStore.collection("competition").document(insSubject)
                .set(userMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startToast("경쟁률 정보 등록 성공");
                        ((EditText)findViewById(R.id.insertProfessorField)).setText(null);
                        ((EditText)findViewById(R.id.insertSubField)).setText(null);
                        ((EditText)findViewById(R.id.insertPersonalField)).setText(null);
                        ((EditText)findViewById(R.id.insertOccupancyField)).setText(null);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("error",e.getMessage());
                        startToast("경쟁률 정보 등록 실패, 오류발생");
                    }
                });
    }

    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}