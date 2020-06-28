package com.example.classassistantproject;

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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RatingActivity extends AppCompatActivity implements RatingAdapter.ListItemClickListener {

    private List<Rating> ratingList = new ArrayList<>();
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;

    private FirestoreRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        recyclerView=findViewById(R.id.recycleview);
        firebaseFirestore = FirebaseFirestore.getInstance();

        Query query = firebaseFirestore.collection("rating");
        FirestoreRecyclerOptions<Rating> options = new FirestoreRecyclerOptions
                .Builder<Rating>().setQuery(query,Rating.class)
                .build();

         adapter = new FirestoreRecyclerAdapter<Rating, RatingViewHolder>(options) {
            @NonNull
            @Override
            public RatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.rateitem,parent,false);
                return new RatingViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull RatingViewHolder holder, int position, @NonNull Rating model) {
                holder.txt_lecture.setText(model.getCourseTitle());
                holder.txt_professor.setText(model.getCourseProfessor());
            }
        };

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



    }

    @Override
    public void listItemClick(int position) {
        Toast.makeText(this,ratingList.get(position)
                        .getCourseTitle()+"\n"+ ratingList.get(position)
                .getCourseProfessor(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RatingActivity.this, CommentWriteActivity.class);
        startActivity(intent);

    }

    private class RatingViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_lecture ;
        private TextView txt_professor;
        public RatingViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_lecture = itemView.findViewById(R.id.txt_lecture);
            txt_professor = itemView.findViewById(R.id.txt_professor);
        }
    }
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    protected void onStart() {
        super.onStart();
       adapter.startListening();
    }
}
