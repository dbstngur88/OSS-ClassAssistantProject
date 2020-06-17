package com.example.classassistantproject;

public class Course {
    //강의정보처리 클래스

    int courseID;               // 강의 고유 번호
    int courseYear;             // 해당 년도
    String courseTerm;          // 해당 학기
    String courseArea;          // 강의 영역(교양? 전공?)
    String courseMajor;         // 해당 학과
    String courseGrade;         // 해당 학년
    String courserTitle;        // 강의 제목
    int courseCredit;           // 강의 학점
    int courseDivide;           // 강의 분반
    int coursePersonnel;        // 강의 제한 인원
    String courseProfessor;     // 강의 교수
    String courseTime;          // 강의 시간
    String courseRoom;          // 강의실

    public Course(int courseID, int courseYear, String courseTerm, String courseArea, String courseMajor, String courseGrade, String courserTitle, int courseCredit, int courseDivide, int coursePersonnel, String courseProfessor, String courseTime, String courseRoom) {
        this.courseID = courseID;
        this.courseYear = courseYear;
        this.courseTerm = courseTerm;
        this.courseArea = courseArea;
        this.courseGrade = courseGrade;
        this.courseMajor = courseMajor;
        this.courserTitle = courserTitle;
        this.courseCredit = courseCredit;
        this.courseDivide = courseDivide;
        this.coursePersonnel = coursePersonnel;
        this.courseProfessor = courseProfessor;
        this.courseTime = courseTime;
        this.courseRoom = courseRoom;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }

    public String getCourseTerm() {
        return courseTerm;
    }

    public void setCourseTerm(String courseTerm) {
        this.courseTerm = courseTerm;
    }

    public String getCourseArea() {
        return courseArea;
    }

    public void setCourseArea(String courseArea) {
        this.courseArea = courseArea;
    }

    public String getCourseMajor() {
        return courseMajor;
    }

    public void setCourseMajor(String courseMajor) {
        this.courseMajor = courseMajor;
    }

    public String getCourseGrade() {
        return courseGrade;
    }

    public void setCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
    }

    public String getCourserTitle() {
        return courserTitle;
    }

    public void setCourserTitle(String courserTitle) {
        this.courserTitle = courserTitle;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(int courseCredit) {
        this.courseCredit = courseCredit;
    }

    public int getCourseDivide() {
        return courseDivide;
    }

    public void setCourseDivide(int courseDivide) {
        this.courseDivide = courseDivide;
    }

    public int getCoursePersonnel() {
        return coursePersonnel;
    }

    public void setCoursePersonnel(int coursePersonnel) {
        this.coursePersonnel = coursePersonnel;
    }

    public String getCourseProfessor() {
        return courseProfessor;
    }

    public void setCourseProfessor(String courseProfessor) {
        this.courseProfessor = courseProfessor;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseRoom() {
        return courseRoom;
    }

    public void setCourseRoom(String courseRoom) {
        this.courseRoom = courseRoom;
    }
}

