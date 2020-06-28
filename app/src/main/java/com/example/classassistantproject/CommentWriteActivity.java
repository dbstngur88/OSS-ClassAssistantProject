package com.example.classassistantproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CommentWriteActivity extends AppCompatActivity {
    TextView rateCount, showRating;
    RatingBar ratingBar;
    EditText comment;
    float rateValue; String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        rateCount = (TextView)findViewById(R.id.rateCount);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        comment = (EditText)findViewById(R.id.comment);
        showRating = (TextView)findViewById(R.id.showRating);
        Button btn_save = (Button)findViewById(R.id.btn_save);


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
                temp = rateCount.getText().toString();
                showRating.setText("사용자의 평가 : "+temp+"\n"+ comment.getText());
                comment.setText("한줄평 : ");
                ratingBar.setRating(0);
                rateCount.setText("");
            }
        });

        Intent intent = getIntent();
        processIntent(intent) ;
    }
    private void processIntent(Intent intent)
    {
        if(intent != null) {
            float rating = intent.getFloatExtra("rating",0.0f);
            ratingBar.setRating(rating);
        }
    }


}
