package com.example.classassistantproject;

public class Rating {


    private String CourseProfessor;
    private String CourseTitle;

    public Rating(String courseProfessor, String courseTitle) {
        this.CourseProfessor = courseProfessor;
        this.CourseTitle = courseTitle;
    }

    public String getCourseProfessor() {
        return CourseProfessor;
    }

    public void setCourseProfessor(String courseProfessor) {
        CourseProfessor = courseProfessor;
    }

    public String getCourseTitle() {
        return CourseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        CourseTitle = courseTitle;
    }
}
