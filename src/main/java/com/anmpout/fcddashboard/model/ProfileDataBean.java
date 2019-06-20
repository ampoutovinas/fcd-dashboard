/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anmpout.fcddashboard.model;

import java.io.Serializable;

/**
 *
 * @author anmpout
 */

public class ProfileDataBean implements Serializable{

private Integer pathId;

private Long  currentTimestamp;

private String  oldTimestamp; 

private String  difSpeed;

private String  difCount;

private String  difTime; 

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

    public String getOldTimestamp() {
        return oldTimestamp;
    }

    public void setOldTimestamp(String oldTimestamp) {
        this.oldTimestamp = oldTimestamp;
    }

    public String getDifSpeed() {
        return difSpeed;
    }

    public void setDifSpeed(String difSpeed) {
        this.difSpeed = difSpeed;
    }

    public String getDifCount() {
        return difCount;
    }

    public void setDifCount(String difCount) {
        this.difCount = difCount;
    }

    public String getDifTime() {
        return difTime;
    }

    public void setDifTime(String difTime) {
        this.difTime = difTime;
    }

}
