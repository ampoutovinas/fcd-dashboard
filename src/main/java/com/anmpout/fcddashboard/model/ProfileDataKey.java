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
public class ProfileDataKey implements Serializable{
    private Integer pathId;
    private Long currentTimestamp;
    private Long oldTimestamp;
    
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


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    
    

    
}
