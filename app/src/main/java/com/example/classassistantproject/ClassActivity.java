package com.example.classassistantproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

/**
 * created by donghwan from 2020.06.29...
 */

public class ClassActivity extends AppCompatActivity {

    List<Course> dataList = new ArrayList<>();
    RecyclerView mRecyclerView;
    Context context;

    //layout manager for recyclerview
    RecyclerView.LayoutManager layoutManager;

    FirebaseFirestore db; //파이어베이스 인스턴
    CourseAdapter adapter; //CourseAdapter 인스턴스스
    ProgressDialog pd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        //파이어베이스 초기화
        db = FirebaseFirestore.getInstance();

        //view 활성화
        mRecyclerView = findViewById(R.id.recycler_View);

        //set recyclerview properties
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        //adapter
        adapter = new CourseAdapter(ClassActivity.this, dataList, context);

        //show data in recyclerview
        showData();

        //검색 버튼 활성화(Action Listener 설치)
        findViewById(R.id.searchButton).setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent;
            switch(v.getId()){
                case R.id.searchButton:
                    String getSubName = ((EditText)findViewById(R.id.majorText)).getText().toString();
                    if(getSubName == ""){
                        startToast("검색할 과목을 입력하세요.");
                    }else {
                        SearchWithSub();
                    }
                    break;

            }
        }
    };

    private void SearchWithSub(){
        String getSubName = ((EditText)findViewById(R.id.majorText)).getText().toString();
        mRecyclerView.setAdapter(adapter);
        dataList.removeAll(dataList);
        db.collection("elective")
                .whereEqualTo("courseTitle", getSubName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        //called when data is retrived
                        pd.dismiss();

                        //show data
                        for (DocumentSnapshot doc : task.getResult()) {
                            Course course = new Course (
                                    doc.getString("courseGrade"),
                                    doc.getString("courseTitle"),
                                    doc.getString("courseCredit"),
                                    doc.getString("courseDivide"),
                                    doc.getString("coursePersonal"),
                                    doc.getString("courseProfessor"),
                                    doc.getString("courseTime"),
                                    doc.getString("courseRoom"));
                            dataList.add(course);
                        }
                        //set adapter to recyclerview
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //called when there is any error while retriving
                        pd.dismiss();

                        Toast.makeText(ClassActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
    private void showData() {

        pd = new ProgressDialog(this);

        pd.setTitle("검색중...");
        pd.show();

        db.collection("elective")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //called when data is retrived
                  pd.dismiss();

                //show data
                for (DocumentSnapshot doc : task.getResult()) {
                    Course course = new Course (
                        doc.getString("courseGrade"),
                        doc.getString("courseTitle"),
                        doc.getString("courseCredit"),
                        doc.getString("courseDivide"),
                        doc.getString("coursePersonal"),
                        doc.getString("courseProfessor"),
                        doc.getString("courseTime"),
                        doc.getString("courseRoom"));
                        dataList.add(course);
                    }
                //set adapter to recyclerview
                mRecyclerView.setAdapter(adapter);
                }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //called when there is any error while retriving
                        pd.dismiss();

                        Toast.makeText(ClassActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}