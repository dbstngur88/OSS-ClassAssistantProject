package com.example.classassistantproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    FirebaseUser mUser;   // 파이어베이스 user 확인할 변수

    Button addButton;
    TextView courseTime;
    TextView courseTitle;
    TextView courseProfessor;
    TextView courseRoom;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        addButton = findViewById(R.id.addButton);
        courseTime = findViewById(R.id.courseTime);
        courseTitle = findViewById(R.id.courseTitle);
        courseProfessor = findViewById(R.id.courseProfessor);
        courseRoom = findViewById(R.id.courseRoom);

        mUser = FirebaseAuth.getInstance().getCurrentUser();

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

        EditText editText = findViewById(R.id.majorText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text) {
        List<Course> filteredList = new ArrayList<>();

        for (Course course : dataList) {
            if (course.getCourseTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(course);
            }
        }
        adapter.filterList(filteredList);
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
                case R.id.addButton:

                    String title = courseTitle.getText().toString().trim(); //교과목명
                    String time = courseTime.getText().toString().trim(); //강의시간
                    String professor = courseProfessor.getText().toString().trim(); //담당교수
                    String room = courseRoom.getText().toString().trim(); //강의실

                    //function call to upload data
                    uploadData( time, title, professor, room);
                    break;

            }
        }
    };

    private void SearchWithSub(){

        pd.setTitle("검색중...");
        pd.show();

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

        pd.setTitle("전체강의 로딩중...");
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

                //adapter
                adapter = new CourseAdapter(ClassActivity.this, dataList, context);
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
    }//showdata()

    //파이어스토어에 강의정보를 올려주는 메소드
    private void uploadData(String time, String title, String professor, String room) {

        final String id = UUID.randomUUID().toString();


        //강의정보 해쉬맵
        final Map<String, Object> lectureMap = new HashMap<>();
        lectureMap.put("courseProfessor", time);
        lectureMap.put("courseTitle", title);
        lectureMap.put("coursePersonal", professor);
        lectureMap.put("coursRoom", room);

        //해당 유저 이메일 확인후 본인만의 myLecture 생성 후 강의 추가
        mUser.getIdToken(true)
                .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                    public void onComplete(@NonNull Task<GetTokenResult> task) {
                        if (task.isSuccessful()) {
                            String idToken = task.getResult().getToken();
                            if(db.collection("myLecture").document(idToken) == null) {
                                // 현재 id로 만들어진 document가 없을때
                                db.collection("myLecture").document(idToken).set(lectureMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(ClassActivity.this, "로딩 성공!", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("error", e.getMessage());
                                        Toast.makeText(ClassActivity.this, "로딩 실패, 오류 발생", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{

                            }
                        } else {
                            task.getException();
                        }
                    }
                });
    }//updata()

    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }



}