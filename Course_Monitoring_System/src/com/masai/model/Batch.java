package com.masai.model;

public class Batch {
    private int batchID;
    private int courseID;
    private int facultyID;
    private int numberOfStudent;
    private String batchStartDate;
    private String duration;

    public Batch() {
    }

    public Batch(int batchID, int courseID, int facultyID, int numberOfStudent, String batchStartDate, String duration) {
        this.batchID = batchID;
        this.courseID = courseID;
        this.facultyID = facultyID;
        this.numberOfStudent = numberOfStudent;
        this.batchStartDate = batchStartDate;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "batchID=" + batchID +
                ", courseID=" + courseID +
                ", facultyID=" + facultyID +
                ", numberOfStudent=" + numberOfStudent +
                ", batchStartDate='" + batchStartDate + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }

    public int getBatchID() {
        return batchID;
    }

    public void setBatchID(int batchID) {
        this.batchID = batchID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(int facultyID) {
        this.facultyID = facultyID;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public String getBatchStartDate() {
        return batchStartDate;
    }

    public void setBatchStartDate(String batchStartDate) {
        this.batchStartDate = batchStartDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
