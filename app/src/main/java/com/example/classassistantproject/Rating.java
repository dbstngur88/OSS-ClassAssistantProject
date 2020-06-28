package com.example.classassistantproject;

public class Rating {
    private String CourseProfessor;
    private String CourseTitle;

    private Rating(){
    }
    private Rating(String CourseProfessor, String CourseTitle) {
        this.CourseProfessor = CourseProfessor;
        this.CourseTitle = CourseTitle;
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
