/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anmpout.fcddashboard.model;

/**
 *
 * @author anmpout
 */
public class MonthData {
    private Integer pathId;
    private Integer day;
    private Integer month;
    private Integer year;
    private Integer time;
    private Integer minTime;
    private Integer maxTime;
    private Integer count;
    private Integer minCount;
    private Integer maxCount;
    private Double speed;
    private Double minSpeed;
    private Double maxSpeed;

    public MonthData() {
    }

    public MonthData(Integer pathId, Integer day, Integer month, Integer year, Integer time, Integer minTime, Integer maxTime, Integer count, Integer minCount, Integer maxCount, Double speed, Double minSpeed, Double maxSpeed) {
        this.pathId = pathId;
        this.day = day;
        this.month = month;
        this.year = year;
        this.time = time;
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.count = count;
        this.minCount = minCount;
        this.maxCount = maxCount;
        this.speed = speed;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
    }


    

    public Integer getPathId() {
        return pathId;
    }

    public void setPathId(Integer pathId) {
        this.pathId = pathId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getMinTime() {
        return minTime;
    }

    public void setMinTime(Integer minTime) {
        this.minTime = minTime;
    }

    public Integer getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Integer maxTime) {
        this.maxTime = maxTime;
    }

    public Integer getMinCount() {
        return minCount;
    }

    public void setMinCount(Integer minCount) {
        this.minCount = minCount;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public Double getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(Double minSpeed) {
        this.minSpeed = minSpeed;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

 
    
}
