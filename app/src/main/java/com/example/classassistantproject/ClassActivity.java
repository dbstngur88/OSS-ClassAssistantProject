package com.example.classassistantproject;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * created by donghwan from 6.27...
 *
 */
public class ClassActivity extends AppCompatActivity implements CourseAdapter.MyRecyclerViewClickListener {

    private static final String TAG = "SubSearchProcess";

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference courseRef = rootRef.collection("courseList");
    //private FirebaseAuth.AuthStateListener.authStateListener;

    private FirestoreRecyclerAdapter<Course, CourseViewHolder> firestoreRecyclerAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        final RecyclerView recyclerView = findViewById(R.id.recycler_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final TextView emptyView = findViewById(R.id.empty_view);
        final ProgressBar progressBar = findViewById(R.id.progress_bar);
        findViewById(R.id.searchButton).setOnClickListener(onClickListener);

        Query query = courseRef.orderBy("courseTitle", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<Course> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Course>()
                .setQuery(query, Course.class)
                .build();

        firestoreRecyclerAdapter =
                new FirestoreRecyclerAdapter<Course, CourseViewHolder>(firestoreRecyclerOptions) {
                    @Override
                    protected void onBindViewHolder(@NonNull CourseViewHolder holder, int position, @NonNull Course model) {
                        holder.setCourseList(getApplicationContext(), model);
                    }

                    @NonNull
                    @Override
                    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course, parent, false);
                        return new CourseViewHolder(view);
                    }

                    @Override
                    public void onDataChanged() {
                        if (progressBar != null) {
                            progressBar.setVisibility(View.GONE);
                        }

                        if (getItemCount() == 0) {
                            recyclerView.setVisibility(View.GONE);
                        } else {
                            recyclerView.setVisibility(View.VISIBLE);
                            emptyView.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public int getItemCount() {
                        return super.getItemCount();
                    }
                };

        recyclerView.setAdapter(firestoreRecyclerAdapter);

        startToast("로딩중...");
        try {
            rootRef.collection("courseList")
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
                                    courseRef.add(new Course(grade, title, credit, divide, personal, professor, time, room));
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
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.searchButton:
                    startToast("검색을 시도합니다..");
                    schSub();
                    break;
                case R.id.addButton:

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

            rootRef.collection("courseList")
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
    protected void onStart() {
        super.onStart();
        firestoreRecyclerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (firestoreRecyclerAdapter != null) {
            firestoreRecyclerAdapter.stopListening();
        }

    }


    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onAddButtonClicked(int position) {

    }
}
