package com.example.classassistantproject;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * created by donghwan from 2020.06.29...
 */

public class CourseViewHolder extends RecyclerView.ViewHolder {
    //TextView addButton;
   TextView courseGradeTextView;
   TextView courseTitleTextView;
   TextView courseProfessorTextView;
   TextView courseCreditTextView;
   TextView courseDivideTextView;
   TextView coursePersonalTextView;
   //TextView courseRealPersonalTextView;
   TextView courseTimeTextView;
   TextView courseRoomTextView;

    private  CourseViewHolder.ClickListener mClickListener;

    View mView;

    public CourseViewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;

        //item click listener
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });

        //item long click listener
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v, getAdapterPosition());
                return true;
            }
        });

        //course.xml에 view들 활성화
        courseGradeTextView = itemView.findViewById(R.id.courseGrade);
        courseTitleTextView = itemView.findViewById(R.id.courseTitle);
        courseProfessorTextView = itemView.findViewById(R.id.courseProfessor);
        courseCreditTextView = itemView.findViewById(R.id.courseCredit);
        courseDivideTextView = itemView.findViewById(R.id.courseDivide);

        coursePersonalTextView = itemView.findViewById(R.id.coursePersonal);
        //courseRealPersonalTextView = itemView.findViewById(R.id.ed_insert_rpsl);
        courseTimeTextView = itemView.findViewById(R.id.courseTime);
        courseRoomTextView = itemView.findViewById(R.id.courseRoom);
    }

    //interface fo click listener
    public  interface ClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public  void setOnClickListener(CourseViewHolder.ClickListener clickListener) {
        mClickListener = clickListener;
    }
}
