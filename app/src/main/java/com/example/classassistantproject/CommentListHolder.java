package com.example.classassistantproject;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommentListHolder extends RecyclerView.ViewHolder {
    TextView txt_rateScore,txt_comment;
    View mView;
    public CommentListHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;
        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());

            }
        });
        //item long click listener
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });

        //initialize view with listmodel.xml
        txt_comment = itemView.findViewById(R.id.txt_comment);
        txt_rateScore = itemView.findViewById(R.id.txt_rateScore);
    }
    private CommentListHolder.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public void setOnClickListener(CommentListHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
