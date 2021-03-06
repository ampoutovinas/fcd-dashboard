/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anmpout.fcddashboard.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author anmpout
 */
@Entity
@Table(name = "POINT")
public class Point implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "POINT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pointId;

    @Column(name = "LATITUDE")
    private Double latitude;
    
    @Column(name = "LONGITUDE")
    private Double longitude;

    /**
     *
     * @param pointId
     */
    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    /**
     *
     * @param latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @param longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     */
    public Integer getPointId() {
        return pointId;
    }

    /**
     *
     * @return
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @return
     */
    public Double getLongitude() {
        return longitude;
    }


}
