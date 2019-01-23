package com.lckj.alarmer.controller.indexobj;

import java.util.Date;

public class MemoryFreeutilization {
    private double max;
    private double min;
    private double average;
    private Date date;
    private String instanceid;
    private String userid;

    public MemoryFreeutilization(String max, String min, String average, String timestamp, String instanceid, String userid) {
        this.max = Double.valueOf(max);
        this.min = Double.valueOf(min);
        this.average = Double.valueOf(average);
        this.date = new Date(new Long(timestamp));
        this.instanceid = instanceid;
        this.userid = userid;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInstanceid() {
        return instanceid;
    }

    public void setInstanceid(String instanceid) {
        this.instanceid = instanceid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
