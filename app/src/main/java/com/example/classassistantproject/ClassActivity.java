package com.example.classassistantproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by donghwan from 2020.06.29...
 */

public class ClassActivity extends AppCompatActivity {

    List<Course> dataList = new ArrayList<>();
    RecyclerView mRecyclerView;
    Context context;

    //layout manager for recyclerview
    RecyclerView.LayoutManager layoutManager;

    FirebaseFirestore db; //파이어베이스 인스턴스
    CourseAdapter adapter; //CourseAdapter 인스턴스
    ProgressDialog pd;
    FirebaseUser mUser;   // 파이어베이스 user 확인할 변수
    String[] times;

    TextView courseTime;
    TextView courseTitle;
    TextView courseProfessor;
    TextView courseRoom;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        courseTime = findViewById(R.id.courseTime);
        courseTitle = findViewById(R.id.courseTitle);
        courseProfessor = findViewById(R.id.courseProfessor);
        courseRoom = findViewById(R.id.courseRoom);

        //mUser = FirebaseAuth.getInstance().getCurrentUser();

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
    protected void uploadData(final String time, final String title, String professor, String room) {

     //   startToast(title +"(을)를 추가합니다.");
        //set title of progress bar
        //pd.setTitle("등록중...");
        //show progress bar when user clike save button
        //pd.show();
        final List<String> timeList = new ArrayList<>();
        final Lecture checkLectureTime = new Lecture();


        final Map<String, Object> putMap = new HashMap<>();
        putMap.put("courseTime", time);
        putMap.put("courseTitle", title);
        putMap.put("courseProfessor", professor);
        putMap.put("CourseRoom", room);

        //해당 유저 이메일 확인후 본인만의 myLecture 생성 후 강의 추가
        /**
         * 해당 로직 동작 방식
         * 1. 사용자 프로필 Firestore에서 가져오기
         * 2. 이메일을 참조
         * 3. 강의를 담을 때 '담긴강의' 컬렉션에서 겹치는 강의 여부 확인
         */
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mUser != null) {
            final String email = mUser.getEmail();
            db.collection("장바구니").document("사용자리스트").collection(email)    //TestCode, 수정필요(courseList ->competition)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    timeList.add(document.get("courseTime").toString());  //기존 자신의 파이어베이스 내 데이터 중 시간에 해당하는 데이터를 String타입 arraylist에 저장
                                    times =  timeList.toArray(new String[ timeList.size()]); //아이템들의 정보를 배열에 입력
                                    for(int i = 0; i<timeList.size();i++){
                                        checkLectureTime.addlecture(times[i]);
                                    }
                                }
                            } else {
                                startToast("일치하는 결과가 없습니다.");
                            }
                        }
                    });
            if(checkLectureTime.validate(time) != true){
                startToast("담기 실패, 시간대가 중복입니다.");

            }else if(checkLectureTime.validate(time) == true){
                db.collection("장바구니").document("사용자리스트").collection(email).document(title).set(putMap);
                startToast(title +"(을)를 추가에 성공했습니다.");
            }

            //우선 과목을 장바구니에 담는다
//                    db.collection("장바구니").document("사용자리스트").collection(email).document(title).set(putMap);
/*
                    DocumentReference doRef = db.collection("장바구니").document("사용자리스트")
                            .collection(email).document(title);
*/
                    /*
                    //추가할 과목과 미리 담겨있는 과목의 title 일치 여부 확인
                    if (db.collection("장바구니").document("사용자리스트").collection(email).document(title) == doRef) {
                        //일치하지 않는다면 해당과목 myLecture에 저장
                        db.collection("myLecture").document("사용자리스트")
                                .collection(email).document(title).set(putMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ClassActivity.this, "담기 성공!",Toast.LENGTH_SHORT).show();
                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("error", e.getMessage());
                                        Toast.makeText(ClassActivity.this, "로딩 실패, 오류 발생", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        // 일치한다

                    }

                    */
                }
            }
    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}