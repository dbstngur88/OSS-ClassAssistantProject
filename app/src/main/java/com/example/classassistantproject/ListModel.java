package com.example.classassistantproject;

public class ListModel
{
    String id, comment,rateScore;

    public ListModel(String id, String comment, String rateScore) {
        this.id = id;
        this.comment = comment;
        this.rateScore = rateScore;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRateScore() {
        return rateScore;
    }

    public void setRateScore(String rateScore) {
        this.rateScore = rateScore;
    }
}
