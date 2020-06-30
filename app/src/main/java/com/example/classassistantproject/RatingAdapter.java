package com.example.classassistantproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.Option;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<RatingViewHolder> {
    RatingActivity ratingActivity;
    List<Rating> ratingList;
    Context context;
    RatingBar ratingBar;
    TextView CourseTitle1, CourseProfessor1;

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

        final RatingViewHolder ratingViewHolder = new RatingViewHolder(itemView);

        ratingViewHolder.setOnClickListener(new RatingViewHolder.ClickListener(){
            @Override
            public void onItemClick(View view, int position){


                TextView CourseTitleTextView = view.findViewById(R.id.CourseTitle);
                TextView CourseProfessorTextView = view.findViewById(R.id.CourseProfessor);

                String title1 = ratingList.get(position).getCourseTitle();
                String professor1 = ratingList.get(position).getCourseProfessor();

                String title = ratingList.get(position).getCourseTitle();
                String professor = ratingList.get(position).getCourseProfessor();
                Toast.makeText(ratingActivity, title + "\n" + professor,
                        Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(view.getContext(),CommentWriteActivity.class);
                intent.putExtra("CourseTitle",title1);
                intent.putExtra("CourseProfessor",professor1);
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
