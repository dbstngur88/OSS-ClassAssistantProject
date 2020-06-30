package com.example.classassistantproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * created by donghwan from 2020.06.29...
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseViewHolder> {

    ClassActivity classActivity;
    List<Course> datalist;
    Context context;

    public CourseAdapter(ClassActivity classActivity, List<Course> datalist, Context context) {
        this.classActivity = classActivity;
        this.datalist = datalist;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //course 레이아웃 생성
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.course, viewGroup, false);

        CourseViewHolder courseViewHolder = new CourseViewHolder(itemView);
        //handle item clicks here
        courseViewHolder.setOnClickListener(new CourseViewHolder.ClickListener() {


            @Override
            public void onItemClick(View view, int position) {
                /**
                 * 사용자가 아이템을(해당강의칸) 클릭할 때 동작
                 * 누르면 해당정보 출력
                 */

                //show data in toast in clicking
                String grade = datalist.get(position).getCourseGrade();
                String title = datalist.get(position).getCourseTitle();
                String credit = datalist.get(position).getCourseCredit();
                String divide = datalist.get(position).getCourseDivide();
                String personal = datalist.get(position).getCoursePersonal();
                String professor = datalist.get(position).getCourseProfessor();
                String time = datalist.get(position).getCourseTime();
                String room = datalist.get(position).getCourseRoom();
                Toast.makeText(classActivity, title + "\n" + grade + "\n" + credit + "\n" + divide + "\n" + personal + "\n" + professor + "\n" + time + "\n" + room + "\n", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //사용자가 아이템을 길게 누를 때 동작

            }

            @Override
            public void onAddClick(View view, int position) {

            }

            //@Override
            public void onAddButtonClick(View view, int position) {
                //사용자가 추가버튼을 누를 때 동작

                String title = datalist.get(position).getCourseTitle(); //교과목명
                String time = datalist.get(position).getCourseTime(); //강의시간
                String professor = datalist.get(position).getCourseProfessor(); //담당교수
                String room = datalist.get(position).getCourseRoom(); //강의실
            }
        });
        return courseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder courseViewHolder, int position) {
        //position에 해당하는 데이터를 아이템뷰에 표시
        //Course item = datalist.get(position);
        courseViewHolder.courseGradeTextView.setText(datalist.get(position).getCourseGrade());
        courseViewHolder.courseTitleTextView.setText(datalist.get(position).getCourseTitle());
        courseViewHolder.courseCreditTextView.setText(datalist.get(position).getCourseCredit());
        courseViewHolder.courseDivideTextView.setText(datalist.get(position).getCourseDivide());
        courseViewHolder.coursePersonalTextView.setText(datalist.get(position).getCoursePersonal());
        courseViewHolder.courseProfessorTextView.setText(datalist.get(position).getCourseProfessor());
        courseViewHolder.courseTimeTextView.setText(datalist.get(position).getCourseTime());
        courseViewHolder.courseRoomTextView.setText(datalist.get(position).getCourseRoom());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public void filterList(List<Course> filterdList) {
        datalist = filterdList;
        notifyDataSetChanged();
    }
}
