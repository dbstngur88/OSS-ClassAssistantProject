package com.example.classassistantproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RatingActivity extends AppCompatActivity  {

    private List<Rating> ratingList = new ArrayList<>();
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;
    private Context context;
    private RecyclerView.LayoutManager layoutManager;
    private RatingAdapter adapter; //RatingAdapter 인스턴스
    private ProgressDialog pd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        findViewById(R.id.SearchBtn).setOnClickListener(onClickListener);

        //파이어베이스 초기화
        firebaseFirestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.recycleview);

        recyclerView.setHasFixedSize(true);
        adapter = new RatingAdapter(RatingActivity.this, ratingList, context);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        showData();
    }
    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent;
            switch(v.getId()){
                case R.id.SearchBtn:
                    String getProName = ((EditText)findViewById(R.id.SchProfessorField)).getText().toString();
                    if(getProName == ""){
                        startToast("교수명을 입력하세요.");
                    }else {
                        SearchWithPro();
                    }
                    break;

            }
        }
    };

    private void SearchWithPro(){
        String getProName = ((EditText)findViewById(R.id.SchProfessorField)).getText().toString();
        recyclerView.setAdapter(adapter);
        ratingList.removeAll(ratingList);

        firebaseFirestore.collection("rating")
                .whereEqualTo("CourseProfessor", getProName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        pd.dismiss();

                        for (DocumentSnapshot doc : task.getResult()) {
                            Rating rating = new Rating(
                                    doc.getString("CourseTitle"),
                                    doc.getString("CourseProfessor"));
                            ratingList.add(rating);

                        }
                        recyclerView.setAdapter(adapter);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();

                Toast.makeText(RatingActivity.this, e.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void showData() {
            pd  = new ProgressDialog(this);
            pd.setTitle("목록을 불러오는중...");

                    firebaseFirestore.collection("rating").get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    pd.dismiss();

                                    for (DocumentSnapshot doc : task.getResult()) {
                                        Rating rating = new Rating(
                                                doc.getString("CourseTitle"),
                                                doc.getString("CourseProfessor"));
                                        ratingList.add(rating);

                                    }
                                    recyclerView.setAdapter(adapter);
                                }
                            }).addOnFailureListener(new OnFailureListener() {

                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();

                    Toast.makeText(RatingActivity.this, e.getMessage(),
                            Toast.LENGTH_SHORT).show();

                }
            });





    }

    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
