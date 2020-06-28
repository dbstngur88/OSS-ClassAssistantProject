package com.example.classassistantproject;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseViewHolder extends RecyclerView.ViewHolder {
    //private TextView addButton;
    private TextView courseGradeTextView;
    private TextView courseTitleTextView;
    private TextView courseProfessorTextView;
    private TextView courseCreditTextView;
    private TextView courseDivideTextView;
    private TextView coursePersonalTextView;
    //private TextView courseRealPersonaTextView;
    private TextView courseTimeTextView;
    private TextView courseRoomTextView;


    public CourseViewHolder(@NonNull View itemView) {
        super(itemView);

        //addButton = itemView.findViewById(R.id.addButton);
        courseGradeTextView = itemView.findViewById(R.id.courseGrade);
        courseTitleTextView = itemView.findViewById(R.id.courseTitle);
        courseProfessorTextView = itemView.findViewById(R.id.courseProfessor);
        courseCreditTextView = itemView.findViewById(R.id.courseCredit);
        courseDivideTextView = itemView.findViewById(R.id.courseDivide);
        coursePersonalTextView = itemView.findViewById(R.id.coursePersonal);
        //courseRealPersonaTextView = itemView.findViewById(R.id.courseRealPersonal);
        courseTimeTextView = itemView.findViewById(R.id.courseTime);
        courseRoomTextView = itemView.findViewById(R.id.courseRoom);
    }

    public void setCourseList (Context context, Course course) {
        String courseGrade = course.getCourseGrade();
        courseGradeTextView.setText(courseGrade);

        String courseTitle = course.getCourserTitle();
        courseTitleTextView.setText(courseTitle);

        String courseProfessor = course.getCourseProfessor();
        courseProfessorTextView.setText(courseProfessor);

        String courseCredit = course.getCourseCredit();
        courseCreditTextView.setText((courseCredit) + " ");

        String courseDivide = course.getCourseDivide();
        courseDivideTextView.setText((courseDivide) + " ");

        String coursePersonal = course.getCoursePersonal();
        coursePersonalTextView.setText((coursePersonal) + " ");

        //Long courseRealPersonal = course.getCourseRealPersonal();
        //courseRealPersonalTextView.setText(courseRealPersonal);

        String courseTime = course.getCourseTime();
        courseTimeTextView.setText(courseTime);

        String courseRoom = course.getCourseRoom();
        courseRoomTextView.setText(courseRoom);

    }
}
