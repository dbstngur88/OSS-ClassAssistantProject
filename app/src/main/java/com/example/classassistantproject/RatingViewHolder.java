package com.example.classassistantproject;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RatingViewHolder extends RecyclerView.ViewHolder {

    TextView CourseTitleTextView,CourseTitle1;
    TextView CourseProfessorTextView,CourseProfessor1;
    private RatingViewHolder.ClickListener clickListener;

    private View view;

    public RatingViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(view, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });
        CourseTitle1 = view.findViewById(R.id.CourseTitle1);
        CourseProfessor1 = view.findViewById(R.id.CourseProfessor1);
        CourseTitleTextView = itemView.findViewById(R.id.CourseTitle);
        CourseProfessorTextView = itemView.findViewById(R.id.CourseProfessor);
    }
    public interface ClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public void setOnClickListener(RatingViewHolder.ClickListener clicker) {
        clickListener = clicker;
    }

}
