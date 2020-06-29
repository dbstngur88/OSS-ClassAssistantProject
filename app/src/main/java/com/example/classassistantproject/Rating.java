package com.example.classassistantproject;

public class Rating {


    private String CourseProfessor;
    private String CourseTitle;

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
    public Rating () {
        //빈 생성자 필요...
    }

    public Rating(String Title, String Professor){
        this.CourseTitle = Title;
        this.CourseProfessor = Professor;
    }

}
