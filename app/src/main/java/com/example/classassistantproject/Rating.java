package com.example.classassistantproject;

public class Rating {
    private String professor;
    private String lecture;

    public Rating(String professor, String lecture) {
        this.professor = professor;
        this.lecture = lecture;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }
}
