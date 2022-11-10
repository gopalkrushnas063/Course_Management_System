package com.masai.model;

public class DayWiseBatchDetails {
    private int batchID;
    private int dayNumber;
    private String courseName;
    private String facultyName;
    private int numberOfStudents;
    private String status;

    public DayWiseBatchDetails() {
    }

    public DayWiseBatchDetails(int batchID, int dayNumber, String courseName, String facultyName, int numberOfStudents, String status) {
        this.batchID = batchID;
        this.dayNumber = dayNumber;
        this.courseName = courseName;
        this.facultyName = facultyName;
        this.numberOfStudents = numberOfStudents;
        this.status = status;
    }

    public int getBatchID() {
        return batchID;
    }

    public void setBatchID(int batchID) {
        this.batchID = batchID;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DayWiseCourseDetails{" +
                "batchID=" + batchID +
                ", dayNumber=" + dayNumber +
                ", courseName='" + courseName + '\'' +
                ", facultyName='" + facultyName + '\'' +
                ", numberOfStudents=" + numberOfStudents +
                ", status='" + status + '\'' +
                '}';
    }
}
