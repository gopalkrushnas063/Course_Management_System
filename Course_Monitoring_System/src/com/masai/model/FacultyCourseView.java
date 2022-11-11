package com.masai.model;

public class FacultyCourseView {
    private int facultyID;
    private String facultyName;
    private int batchID;
    private String batchStartDay;
    private String topic;
    private String status;


    public FacultyCourseView() {
    }

    public FacultyCourseView(int facultyID, String facultyName, int batchID, String batchStartDay, String topic, String status) {
        this.facultyID = facultyID;
        this.facultyName = facultyName;
        this.batchID = batchID;
        this.batchStartDay = batchStartDay;
        this.topic = topic;
        this.status = status;
    }

    @Override
    public String toString() {
        return "FacultyCourseView{" +
                "facultyID=" + facultyID +
                ", facultyName='" + facultyName + '\'' +
                ", batchID=" + batchID +
                ", batchStartDay='" + batchStartDay + '\'' +
                ", topic='" + topic + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public int getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(int facultyID) {
        this.facultyID = facultyID;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public int getBatchID() {
        return batchID;
    }

    public void setBatchID(int batchID) {
        this.batchID = batchID;
    }

    public String getBatchStartDay() {
        return batchStartDay;
    }

    public void setBatchStartDay(String batchStartDay) {
        this.batchStartDay = batchStartDay;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
