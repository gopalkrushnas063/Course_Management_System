package com.masai.model;

public class CoursePlan {
    private int planid;
    private int batchid;
    private int daynumber;
    private String topic;
    private String status;

    public CoursePlan() {
    }

    public CoursePlan(int planid, int batchid, int daynumber, String topic, String status) {
        this.planid = planid;
        this.batchid = batchid;
        this.daynumber = daynumber;
        this.topic = topic;
        this.status = status;
    }

    public int getPlanid() {
        return planid;
    }

    public void setPlanid(int planid) {
        this.planid = planid;
    }

    public int getBatchid() {
        return batchid;
    }

    public void setBatchid(int batchid) {
        this.batchid = batchid;
    }

    public int getDaynumber() {
        return daynumber;
    }

    public void setDaynumber(int daynumber) {
        this.daynumber = daynumber;
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


    @Override
    public String toString() {
        return "CoursePlan{" +
                "planid=" + planid +
                ", batchid=" + batchid +
                ", daynumber=" + daynumber +
                ", topic='" + topic + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
