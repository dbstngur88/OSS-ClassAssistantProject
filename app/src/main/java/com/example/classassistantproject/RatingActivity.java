package com.example.classassistantproject;

import android.content.Intent;
import android.os.Bundle;
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

public class RatingActivity extends AppCompatActivity {

    private static final String TAG = "SubSearchProcess";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        findViewById(R.id.SearchBtn).setOnClickListener(onClickListener);
        findViewById(R.id.GoBackBtn).setOnClickListener(onClickListener);

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
                    onBackPressed();
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
                                    startToast("일치하는 결과를 찾았습니다.");
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
