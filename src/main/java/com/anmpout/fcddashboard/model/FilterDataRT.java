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
@Table(name = "FILTER_DATA_RT")
public class FilterDataRT implements Serializable{
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

    public Integer getPathId() {
        return pathId;
    }

    public void setPathId(Integer pathId) {
        this.pathId = pathId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
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

    public Double getMedianSpeed() {
        return medianSpeed;
    }

    public void setMedianSpeed(Double medianSpeed) {
        this.medianSpeed = medianSpeed;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Double getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(Double minSpeed) {
        this.minSpeed = minSpeed;
    }


    
}

