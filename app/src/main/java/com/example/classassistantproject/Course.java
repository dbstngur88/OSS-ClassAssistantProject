package com.example.classassistantproject;

public class Course {
    //강의정보처리 클래스
    //Long courseID; // 강의 고유 번호
    //Long courseYear;// 해당 년도
    //String courseTerm;// 해당 학기
    //String courseArea;   // 강의 영역(교양? 전공?)
    //String courseMajor;         // 해당 학과
    String courseGrade;         // 해당 학년
    String courserTitle;        // 강의 제목
    Long courseCredit;           // 강의 학점
    Long courseDivide;           // 강의 분반
    Long coursePersonal;        // 강의 제한 인원
    Long courseRealPersonal;    // 강의 신청 인원
    String courseProfessor;     // 강의 교수
    String courseTime;          // 강의 시간
    String courseRoom;          // 강의실

    public Course(String courseGrade, String courserTitle, Long courseCredit, Long courseDivide, Long coursePersonnel, Long courseRealPersonnel, String courseProfessor, String courseTime, String courseRoom) {
        //this.courseID = courseID;
        //this.courseYear = courseYear;
        //this.courseTerm = courseTerm;
        //this.courseArea = courseArea;
        this.courseGrade = courseGrade;
        //this.courseMajor = courseMajor;
        this.courserTitle = courserTitle;
        this.courseCredit = courseCredit;
        this.courseDivide = courseDivide;
        this.coursePersonal = coursePersonal;
        this.courseRealPersonal = courseRealPersonal;
        this.courseProfessor = courseProfessor;
        this.courseTime = courseTime;
        this.courseRoom = courseRoom;
    }

    /*public Long getCourseID() { return courseID;}
    public void setCourseID(Long courseID) {this.courseID = courseID;}
    public Long getCourseYear() {return courseYear;}
    public void setCourseYear(Long courseYear) {this.courseYear = courseYear;}
    public String getCourseTerm() {return courseTerm;}
    public void setCourseTerm(String courseTerm) {this.courseTerm = courseTerm;}
    public String getCourseArea() {return courseArea;}
    public void setCourseArea(String courseArea) {this.courseArea = courseArea;}
    public String getCourseMajor() {return courseMajor;}
    public void setCourseMajor(String courseMajor) {this.courseMajor = courseMajor;}*/
    public String getCourseGrade() {return courseGrade;}
    public void setCourseGrade(String courseGrade) {this.courseGrade = courseGrade;}
    public String getCourserTitle() {return courserTitle;}
    public void setCourserTitle(String courserTitle) {this.courserTitle = courserTitle;}
    public Long getCourseCredit() {return courseCredit;}
    public void setCourseCredit(Long courseCredit) {this.courseCredit = courseCredit;}
    public Long getCourseDivide() {return courseDivide;}
    public void setCourseDivide(Long courseDivide) {this.courseDivide = courseDivide;}
    public Long getCoursePersonnel() {return coursePersonal;}
    public void setCoursePersonnel(Long coursePersonnel) {this.coursePersonal = coursePersonnel;}
    public Long getCourseRealPersonnel() {return courseRealPersonal;}
    public void setCourseRealPersonnel(Long courseRealPersonnel) {this.courseRealPersonal = courseRealPersonnel;}
    public String getCourseProfessor() {return courseProfessor;}
    public void setCourseProfessor(String courseProfessor) {this.courseProfessor = courseProfessor;}
    public String getCourseTime() {return courseTime;}
    public void setCourseTime(String courseTime) {this.courseTime = courseTime;}
    public String getCourseRoom() {return courseRoom;}
    public void setCourseRoom(String courseRoom) {this.courseRoom = courseRoom;}
}