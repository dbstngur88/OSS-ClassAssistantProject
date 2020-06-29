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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity implements CourseAdapter.MyRecyclerViewClickListener{

    private static final String TAG = "SubSearchProcess";
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    final FirebaseFirestore db = FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        RecyclerView recyclerView = findViewById(R.id.recycler_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(CourseActivity.this));

        final List<Course> datalist = new ArrayList<>();

        final TextView courseGrade = findViewById(R.id.courseGrade);
        final TextView courserTitle = findViewById(R.id.courseTitle);
        final TextView courseProfessor = findViewById(R.id.courseProfessor);
        final TextView courseCredit = findViewById(R.id.courseCredit);
        final TextView courseDivide = findViewById(R.id.courseDivide);
        final TextView coursePersonnel = findViewById(R.id.coursePersonal);
        final TextView courseRealPersonnel = findViewById(R.id.courseRealPersonal);
        final TextView courseTime = findViewById(R.id.courseTime);
        final TextView courseRoom = findViewById(R.id.courseRoom);

        findViewById(R.id.addButton).setOnClickListener(onClickListener);
        findViewById(R.id.searchButton).setOnClickListener(onClickListener);

        startToast("로딩중...");
        try {
            db.collection("courseList")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String grade = (String) document.get("courseGrade");
                                    String title = (String) document.get("courseTitle");
                                    String professor = (String) document.get("courseProfessor");
                                    Long credit = (Long) document.get("courseCredit");
                                    Long divide = (Long) document.get("courseDivide");
                                    Long personal = (Long) document.get("coursePersonal");
                                    String time = (String) document.get("courseTime");
                                    String room = (String) document.get("courseRoom");
                                    datalist.add(new Course(grade, title, credit, divide, personal, professor, time, room));
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                }
                            } else {
                                startToast("일치하는 결과가 없습니다.");
                            }
                        }
                    });
        }catch (Exception e){
            Log.w(TAG, "error!", e);
        }
    }//onCreate()


    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.searchButton:
                    startToast("검색을 시도합니다..");
                    schSub();
                    break;
                case R.id.addButton:
                    Intent intent = new Intent(CourseActivity.this, LectureActivity.class);
                    intent.putExtra("title", "오픈소스");
                    intent.putExtra("professor", "김봉재");
                    intent.putExtra("divide", "11분반");
                    startActivity(intent);
                    break;
            }
        }
    };

    private void schSub(){
        String getSub = ((EditText)findViewById(R.id.majorText)).getText().toString();
        if(getSub.length() >0 ){
            startToast(getSub+"로 검색합니다...");
            //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            //final FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("courseList")
                    .whereEqualTo("courseTitle", getSub)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String title = (String) document.get("courseTitle");  //if the field is String

                                    startToast(title);
                                    Log.d(TAG, document.getId() + " => " + document.getData());
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

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onAddButtonClicked(int position) {

    }
}