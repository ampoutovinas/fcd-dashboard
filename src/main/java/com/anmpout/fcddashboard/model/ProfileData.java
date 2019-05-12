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
@IdClass(ProfileDataKey.class)
@Table(name = "PROFILE_DATA")
public class ProfileData implements Serializable{
 @Id
@Column(name = "PATH_ID")
private Integer pathId;
@Id
@Column(name = "CURRENT_TIMESTAMP")
private Long  currentTimestamp;
@Id
@Column(name = "OLD_TIMESTAMP")
private Long  oldTimestamp; 

@Column(name = "DIF_SPEED")
private Double  difSpeed;

@Column(name = "DIF_COUNT")
private Double  difCount;

@Column(name = "DIF_TIME")
private Double  difTime;

    public Integer getPathId() {
        return pathId;
    }

    public void setPathId(Integer pathId) {
        this.pathId = pathId;
    }

    public Long getCurrentTimestamp() {
        return currentTimestamp;
    }

    public void setCurrentTimestamp(Long currentTimestamp) {
        this.currentTimestamp = currentTimestamp;
    }

    public Long getOldTimestamp() {
        return oldTimestamp;
    }

    public void setOldTimestamp(Long oldTimestamp) {
        this.oldTimestamp = oldTimestamp;
    }

    public Double getDifSpeed() {
        return difSpeed;
    }

    public void setDifSpeed(Double difSpeed) {
        this.difSpeed = difSpeed;
    }

    public Double getDifCount() {
        return difCount;
    }

    public void setDifCount(Double difCount) {
        this.difCount = difCount;
    }

    public Double getDifTime() {
        return difTime;
    }

    public void setDifTime(Double difTime) {
        this.difTime = difTime;
    }



}
