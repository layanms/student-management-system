package com.mycompany.studentmanagementsystem.model;

public class Student  {

    private String name;
    private String ID;
    private String major;
    private double score;

    public Student(String name, String ID, String major) {
        this.name = name;
        this.ID = ID;
        this.major = major;
        this.score = 0; // Default score
    }
    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
