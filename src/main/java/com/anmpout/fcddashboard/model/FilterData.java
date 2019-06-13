/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anmpout.fcddashboard.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 *
 * @author anmpout
 */
@Entity
@IdClass(FilterDataKey.class)
@Table(name = "FILTER_DATA")
public class FilterData implements Serializable{
@Id
@Column(name = "PATH_ID")
private Integer pathId;
@Id
@Column(name = "TIMESTAMP")
private Long  timestamp;

@Column(name = "COUNT")
private Integer  count;

@Column(name = "SPEED")
private Double  speed;

@Column(name = "MEDIAN_SPEED")
private Double  medianSpeed;

@Column(name = "MAX_SPEED")
private Double  maxSpeed;

@Column(name = "MIN_SPEED")
private Double  minSpeed;

@Column(name = "TIME")
private Integer  time;

@Column(name = "DAY")
private Integer  day;

@Column(name = "MONTH")
private Integer  month;

@Column(name = "YEAR")
private Integer  year;

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
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param timestamp
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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
    public Double getMedianSpeed() {
        return medianSpeed;
    }

    /**
     *
     * @param medianSpeed
     */
    public void setMedianSpeed(Double medianSpeed) {
        this.medianSpeed = medianSpeed;
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


    
}
