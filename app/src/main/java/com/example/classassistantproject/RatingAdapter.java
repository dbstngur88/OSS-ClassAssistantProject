package com.example.classassistantproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<RatingViewHolder> {
    RatingActivity ratingActivity;
    List<Rating> ratingList;
    Context context;
    RatingBar ratingBar;
    public RatingAdapter(RatingActivity ratingActivity, List<Rating> ratingList, Context context) {
        this.ratingActivity = ratingActivity;
        this.ratingList = ratingList;
        this.context = context;
    }

    @NonNull
    @Override
    public RatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int option) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rateitem,parent,false);

        RatingViewHolder ratingViewHolder = new RatingViewHolder(itemView);

        ratingViewHolder.setOnClickListener(new RatingViewHolder.ClickListener(){
            @Override
            public void onItemClick(View view, int position){

                String title = ratingList.get(position).getCourseTitle();
                String professor = ratingList.get(position).getCourseProfessor();
                Toast.makeText(ratingActivity, title + "\n" + professor,
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ratingActivity, CommentWriteActivity.class);
                ratingActivity.startActivity(intent);


            }

            @Override
            public void onItemLongClick(View view, int option) {

            }
        });
        return ratingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RatingViewHolder holder, int position) {
        holder.CourseTitleTextView.setText(ratingList.get(position).getCourseTitle());
        holder.CourseProfessorTextView.setText(ratingList.get(position).getCourseProfessor());

    }

    @Override
    public int getItemCount() {
        return ratingList.size();
    }
}
