package com.example.classassistantproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.view.View;
import android.widget.Button;
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

public class CommentListActivity extends AppCompatActivity {

    List<ListModel> modelList = new ArrayList<>();
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    Button btn_add;
    //firesotre
    FirebaseFirestore db;
    CommentAdapter adapter;
    ProgressDialog pd;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.recycler_listview);
        btn_add = findViewById(R.id.btn_add);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        pd = new ProgressDialog(this);

        showData();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CommentListActivity.this,CommentWriteActivity.class));
                finish();
            }
        });

    }

    private void showData() {
        pd.setTitle("목록을 불러오는 중입니다...");
        pd.show();
        db.collection("comments")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                pd.dismiss();
                for (DocumentSnapshot doc: task.getResult()){
                    ListModel model = new ListModel(doc.getString("id"),
                            doc.getString("comment"),
                            doc.getString("rateScore"));
                    modelList.add(model);
                }
                adapter = new CommentAdapter(CommentListActivity.this, modelList);
                recyclerView.setAdapter(adapter);
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(CommentListActivity.this, e.getMessage()
                                ,Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
