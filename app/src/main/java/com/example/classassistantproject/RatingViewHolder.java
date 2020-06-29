package com.example.classassistantproject;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RatingViewHolder extends RecyclerView.ViewHolder {

    TextView CourseTitleTextView;
    TextView CourseProfessorTextView;
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

        CourseTitleTextView = itemView.findViewById(R.id.CourseTitle);
        CourseProfessorTextView = itemView.findViewById(R.id.CourseProfessor);
    }
    public interface ClickListener {
        void onItemClick(View view, int option);
        void onItemLongClick(View view, int option);
    }
    public void setOnClickListener(RatingViewHolder.ClickListener clicker) {
        clickListener = clicker;
    }
}
