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

@Column(name = "TIME")
private Integer  time;


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


    
}
