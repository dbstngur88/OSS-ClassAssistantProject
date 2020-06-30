package com.example.classassistantproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Comment;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommentWriteActivity extends AppCompatActivity {
    TextView rateCount, showRating;
    RatingBar ratingBar;
    EditText comment;
    ProgressDialog pd;
    float rateValue; String temp;
    FirebaseFirestore db;
    Button btn_save, btn_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        rateCount = (TextView)findViewById(R.id.rateCount);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        comment = (EditText)findViewById(R.id.comment);
        showRating = (TextView)findViewById(R.id.showRating);
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_list = (Button)findViewById(R.id.btn_list);
        //progress dialog
        pd = new ProgressDialog(this);
        //firestore
        db = FirebaseFirestore.getInstance();

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue = ratingBar.getRating();
                if(rateValue<=1 && rateValue>0)
                    rateCount.setText("<아주 별로다>\n("+rateValue+"/5)");
                else if(rateValue<=2 && rateValue>1)
                    rateCount.setText("<조금 별로다>\n("+rateValue+"/5)");
                else if(rateValue<=3 && rateValue>2)
                    rateCount.setText("<보통이다>\n("+rateValue+"/5)");
                else if(rateValue<=4 && rateValue>3)
                    rateCount.setText("<조금 괜찮다>\n("+rateValue+"/5)");
                else if(rateValue<=5 && rateValue>4)
                    rateCount.setText("<아주 괜찮다>\n("+rateValue+"/5)");

            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //input data
                String Comment = comment.getText().toString().trim();
                String RateScore = rateCount.getText().toString().trim();
                uploadData(Comment,RateScore);
            }
        });
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CommentWriteActivity.this, CommentListActivity.class));
                finish();
            }
        });

      //  Intent intent = getIntent();
       // processIntent(intent) ;
    }

    private void uploadData(String comment, String rateScore) {
        pd.setTitle("한줄평 저장중...");
        pd.show();
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id",id);
        doc.put("comment", comment);
        doc.put("rateScore",rateScore);

        //데이터 추가
        db.collection("comments").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //데이터 추가 성공 시
                        pd.dismiss();
                        Toast.makeText(CommentWriteActivity.this, "등록 완료!"
                        ,Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //데이터 추가 에러 시
                        pd.dismiss();
                        Toast.makeText(CommentWriteActivity.this, e.getMessage()
                                ,Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void processIntent(Intent intent)
    {
        if(intent != null) {
            float rating = intent.getFloatExtra("rating",0.0f);
            ratingBar.setRating(rating);
        }
    }


}
