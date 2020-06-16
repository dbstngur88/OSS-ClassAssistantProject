package com.example.classassistantproject;

public class CompetSubInfo {
    private String occupancy;
    private String personal;
    private String professor;
    private String title;

    public CompetSubInfo(String occupancy, String personal, String professor, String title){
        this.occupancy = occupancy;
        this.personal = personal;
        this.professor = professor;
        this.title = title;
    }
    public String getOccupancy(){
        return this.occupancy;
    }
    public void setOccupancy(String occupancy){
        this.occupancy = occupancy;
    }
    public String getPersonal(){
        return this.personal;
    }
    public void setPersonal(String personal){
        this.personal = personal;
    }
    public String getProfessor(){
        return this.professor;
    }
    public void setProfessor(String professor){
        this.professor = professor;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
}
