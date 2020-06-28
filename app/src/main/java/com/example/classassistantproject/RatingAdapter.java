package com.example.classassistantproject;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.RatingHolder> {

    private List<Rating> ratingList;
    private ListItemClickListener listItemClickListener;

    public RatingAdapter(List<Rating> ratingList,ListItemClickListener listItemClickListener) {
        this.ratingList = ratingList;
        this.listItemClickListener = listItemClickListener;
    }



    @NonNull
    @Override

    public RatingAdapter.RatingHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rateitem, parent, false);

        return new RatingHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingAdapter.RatingHolder holder, int i) {

        holder.professor.setText(ratingList.get(i).getProfessor());
        holder.lecture.setText(ratingList.get(i).getLecture());
    }

    @Override
    public int getItemCount() {
        return ratingList.size();
    }
    public class RatingHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView professor, lecture;

        public RatingHolder(@NonNull View itemView) {
            super(itemView);
            professor = itemView.findViewById(R.id.txt_professor);
            lecture = itemView.findViewById(R.id.txt_lecture);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listItemClickListener.listItemClick(getAdapterPosition());
        }
    }
    public interface ListItemClickListener {
        void listItemClick (int position);
    }
}
