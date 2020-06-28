package com.example.classassistantproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
//FirestoreRecyclerAdapter
    private final List<Course> mDataList;

    public interface MyRecyclerViewClickListener{
        void onItemClicked(int position);
        void onAddButtonClicked(int position);
    }

    private MyRecyclerViewClickListener mListener;  //위의 인터페이스를 내부에서 들고있기 위함

    public void setOnClickListener(MyRecyclerViewClickListener listener){   //외부에서 호출하여 리스너 지정
        mListener = listener;   //연결이 되었으면 onBindViewHolder로
    }

    public CourseAdapter(List<Course> datalist){
        mDataList = datalist;  //데이터를 외부에서 들고옴
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course, parent, false);
        return new ViewHolder(view); //view를 ViewHolder에 넣어서 onBindViewHolder로 보냄
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course item = mDataList.get(position);
        holder.courseGrade.setText(item.getCourseGrade());
        holder.courserTitle.setText(item.getCourserTitle());
        holder.courseProfessor.setText(item.getCourseProfessor());
        holder.courseCredit.setText(String.valueOf(item.getCourseCredit()));
        holder.courseDivide.setText(String.valueOf(item.getCourseDivide()));
        holder.coursePersonal.setText(String.valueOf(item.getCoursePersonal()));
        holder.courseTime.setText(item.getCourseTime());
        holder.courseRoom.setText(item.getCourseRoom());

        if(mListener!= null){   //외부에서 리스너를 연결했다면
            final int pos = position;   //pos에 현재위치인 position을 넣어라
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClicked(pos);   //여기로 포지션값 던져주기
                }
            });
            holder.addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onAddButtonClicked(pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() { // 어댑터가 가지고 있는 아이템의 개수를 지정
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView addButton;
        TextView courseGrade;
        TextView courserTitle;
        TextView courseProfessor;
        TextView courseCredit;
        TextView courseDivide;
        TextView coursePersonal;
        TextView courseRealPersonal;
        TextView courseTime;
        TextView courseRoom;

        public ViewHolder(@NonNull View itemView) { //itemView는 전체 레이아웃이 들어오는곳
            super(itemView);

            addButton = itemView.findViewById(R.id.addButton);
            courseGrade = itemView.findViewById(R.id.courseGrade);
            courserTitle = itemView.findViewById(R.id.courseTitle);
            courseProfessor = itemView.findViewById(R.id.courseProfessor);
            courseCredit = itemView.findViewById(R.id.courseCredit);
            courseDivide = itemView.findViewById(R.id.courseDivide);
            coursePersonal = itemView.findViewById(R.id.coursePersonal);
            courseRealPersonal = itemView.findViewById(R.id.courseRealPersonal);
            courseTime = itemView.findViewById(R.id.courseTime);
            courseRoom = itemView.findViewById(R.id.courseRoom);
        }
    }
}
