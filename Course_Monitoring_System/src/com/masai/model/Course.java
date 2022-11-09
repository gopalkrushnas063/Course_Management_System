package com.masai.model;

public class Course {
    private int courseID ;
    private String courseName;
    private int fee ;
    private String courseDescription;


    public Course() {
    }

    public Course(int courseID, String courseName, int fee, String courseDescription) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.fee = fee;
        this.courseDescription = courseDescription;
    }


    @Override
    public String toString() {
        return "[ " +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", fee=" + fee +
                ", courseDescription='" + courseDescription + '\'' +
                ']';
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

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
}
