package com.example.classassistantproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

public class RatingActivity extends AppCompatActivity  {

    private List<Rating> ratingList = new ArrayList<>();
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;
    private Context context;
    private RecyclerView.LayoutManager layoutManager;
    RatingAdapter adapter; //RatingAdapter 인스턴스
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        //파이어베이스 초기화
        firebaseFirestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        showData();
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
                                doc.getString("CourseTitle");
                                doc.getString("CourseProfessor");
                            }
                            adapter = new RatingAdapter(RatingActivity.this, ratingList, context);
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

}
