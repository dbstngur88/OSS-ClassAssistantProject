package com.example.classassistantproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.MenuItemHoverListener;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.RowId;
import java.util.ArrayList;
import java.util.List;

/**
 * created by donghwan from 2020.06.29...
 */

public class ClassActivity extends AppCompatActivity {

    List<Course> dataList = new ArrayList<>();
    RecyclerView mRecyclerView;
    Context context;

    //버튼
    Button btn_search;
    Button btn_add;

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

        //show data in recyclerview
        showData();
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
    }

    private void searchData(String s) {
        //프로그레스바 세팅
        pd.setTitle("검색 중입니다...");
        //save button클릭 시 프로그레스바 보여줌
        pd.show();

        db.collection("elective").whereEqualTo("search", s.toLowerCase())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //검색 성공 시 호출
                dataList.clear();
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
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //오류 발생 시 호출
                        pd.dismiss();
                        //해당 오류메시지 출력
                        Toast.makeText(ClassActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    //menu

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //menu_main.xml을 띄워줌
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        //SearchView
//        MenuItem item = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                //검색버튼 누를 시 호출
//
//                searchData(s); //function call with String entered in searchview as parameter
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                //한 글자라도 입력 시 호출
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        //다른 메뉴아이템 처리
//
//        if (item.getItemId() == R.id.action_settings) {
//            Toast.makeText(this, "Settings...", Toast.LENGTH_SHORT).show();
//        }
//        return super.onOptionsItemSelected(item);
//    }
}