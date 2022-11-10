package com.masai.model;

public class BatchWiseDetails {
    private int batchId;
    private String facultyName;
    private int facultyID;
    private int courseID;
    private String courseName;
    private int fee;
    private String batchStartDate;
    private  int numberOfStudent;
    private String courseDesc;


    public BatchWiseDetails() {
    }

    public BatchWiseDetails(int batchId, String facultyName, int facultyID, int courseID, String courseName, int fee, String batchStartDate, int numberOfStudent, String courseDesc) {
        this.batchId = batchId;
        this.facultyName = facultyName;
        this.facultyID = facultyID;
        this.courseID = courseID;
        this.courseName = courseName;
        this.fee = fee;
        this.batchStartDate = batchStartDate;
        this.numberOfStudent = numberOfStudent;
        this.courseDesc = courseDesc;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public int getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(int facultyID) {
        this.facultyID = facultyID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getBatchStartDate() {
        return batchStartDate;
    }

    public void setBatchStartDate(String batchStartDate) {
        this.batchStartDate = batchStartDate;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    @Override
    public String toString() {
        return "BatchWiseDetails{" +
                "batchId=" + batchId +
                ", facultyName='" + facultyName + '\'' +
                ", facultyID=" + facultyID +
                ", courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", fee=" + fee +
                ", batchStartDate='" + batchStartDate + '\'' +
                ", numberOfStudent=" + numberOfStudent +
                ", courseDesc='" + courseDesc + '\'' +
                '}';
    }
}
