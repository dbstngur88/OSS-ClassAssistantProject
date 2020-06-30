package com.example.classassistantproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentListHolder> {
    CommentListActivity commentListActivity;
    List<ListModel> listModel;
    Context context;
    public CommentAdapter(CommentListActivity commentListActivity, List<ListModel> listModel)
    {
        this.commentListActivity = commentListActivity;
        this.listModel = listModel;
    }

    @NonNull
    @Override
    public CommentListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listmodel, parent, false);

     final CommentListHolder commentListHolder = new CommentListHolder(itemView);

     commentListHolder.setOnClickListener(new CommentListHolder.ClickListener() {
         @Override
         public void onItemClick(View view, int position) {

             String comment = listModel.get(position).getComment();
             String rateScore = listModel.get(position).getRateScore();
             Toast.makeText(commentListActivity, comment+"\n"+rateScore,Toast.LENGTH_SHORT).show();
         }

         @Override
         public void onItemLongClick(View view, final int position) {

             AlertDialog.Builder builder = new AlertDialog.Builder(commentListActivity);
             String[] options = {"수정","삭제"};
             builder.setItems(options, new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     if(which == 0) {
                         String id = listModel.get(position).getId();
                         String rateScore = listModel.get(position).getRateScore();
                         String comment = listModel.get(position).getComment();

                         Intent intent = new Intent(commentListActivity, CommentWriteActivity.class);
                        intent.putExtra("pId",id);
                        intent.putExtra("pRateScore",rateScore);
                        intent.putExtra("pComment",comment);

                        commentListActivity.startActivity(intent);
                     }
                     if(which == 1) {
                         commentListActivity.deleteData(position);
                     }
                 }
             });
         }
     });
        return commentListHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentListHolder holder, int position) {

        holder.txt_rateScore.setText(listModel.get(position).getRateScore());
        holder.txt_comment.setText(listModel.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return listModel.size();
    }
}
