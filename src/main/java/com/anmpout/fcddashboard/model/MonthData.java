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

    /**
     *
     */
    public MonthData() {
    }

    /**
     *
     * @param pathId
     * @param day
     * @param month
     * @param year
     * @param time
     * @param minTime
     * @param maxTime
     * @param count
     * @param minCount
     * @param maxCount
     * @param speed
     * @param minSpeed
     * @param maxSpeed
     */
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

    /**
     *
     * @return
     */
    public Integer getPathId() {
        return pathId;
    }

    /**
     *
     * @param pathId
     */
    public void setPathId(Integer pathId) {
        this.pathId = pathId;
    }

    /**
     *
     * @return
     */
    public Integer getCount() {
        return count;
    }

    /**
     *
     * @param count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     *
     * @return
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     *
     * @param speed
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     *
     * @return
     */
    public Integer getDay() {
        return day;
    }

    /**
     *
     * @param day
     */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     *
     * @return
     */
    public Integer getMonth() {
        return month;
    }

    /**
     *
     * @param month
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     *
     * @return
     */
    public Integer getYear() {
        return year;
    }

    /**
     *
     * @param year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     *
     * @return
     */
    public Integer getTime() {
        return time;
    }

    /**
     *
     * @param time
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     *
     * @return
     */
    public Integer getMinTime() {
        return minTime;
    }

    /**
     *
     * @param minTime
     */
    public void setMinTime(Integer minTime) {
        this.minTime = minTime;
    }

    /**
     *
     * @return
     */
    public Integer getMaxTime() {
        return maxTime;
    }

    /**
     *
     * @param maxTime
     */
    public void setMaxTime(Integer maxTime) {
        this.maxTime = maxTime;
    }

    /**
     *
     * @return
     */
    public Integer getMinCount() {
        return minCount;
    }

    /**
     *
     * @param minCount
     */
    public void setMinCount(Integer minCount) {
        this.minCount = minCount;
    }

    /**
     *
     * @return
     */
    public Integer getMaxCount() {
        return maxCount;
    }

    /**
     *
     * @param maxCount
     */
    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    /**
     *
     * @return
     */
    public Double getMinSpeed() {
        return minSpeed;
    }

    /**
     *
     * @param minSpeed
     */
    public void setMinSpeed(Double minSpeed) {
        this.minSpeed = minSpeed;
    }

    /**
     *
     * @return
     */
    public Double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     *
     * @param maxSpeed
     */
    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

 
    
}
