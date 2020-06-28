package com.example.classassistantproject;

import android.content.Intent;
import android.os.Bundle;
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

public class RatingActivity extends AppCompatActivity implements RatingAdapter.ListItemClickListener {

    private List<Rating> ratingList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RatingAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        recyclerView=findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for(int i = 0; i<20; i++) {
           ratingList.add(new Rating("교수"+i,"강의"+i));
        }
        adapter = new RatingAdapter(ratingList, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void listItemClick(int position) {
        Toast.makeText(this,ratingList.get(position)
                        .getLecture()+"\n"+ ratingList.get(position)
                .getProfessor(), Toast.LENGTH_SHORT).show();

    }
}
