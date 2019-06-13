/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anmpout.fcddashboard.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author anmpout
 */
@Entity
@Table(name = "PATH")
public class Path implements Serializable{
@Id
@Column(name = "PATH_ID")
private Integer pathId;

@Column(name = "PATH_NAME")
private String pathName;

@Column(name = "PATH_ORIGIN_DEV_ID")
private String pathOriginDevId;

@Column(name = "PATH_DEST_DEV_ID")
private String pathDestDevId;

@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
@JoinColumn(name = "PATH_ID")
private List<Point> points = new ArrayList<>();

@Column(name = "PATH_DISTANCE")
private Double pathDistance;

@Column(name = "REGION_ID")
private Integer regionId;

    /**
     *
     * @return
     */
    public String getPathOriginDevId() {
        return pathOriginDevId;
    }

    /**
     *
     * @param pathOriginDevId
     */
    public void setPathOriginDevId(String pathOriginDevId) {
        this.pathOriginDevId = pathOriginDevId;
    }

    /**
     *
     * @return
     */
    public String getPathDestDevId() {
        return pathDestDevId;
    }

    /**
     *
     * @param pathDestDevId
     */
    public void setPathDestDevId(String pathDestDevId) {
        this.pathDestDevId = pathDestDevId;
    }

    /**
     *
     * @return
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     *
     * @param points
     */
    public void setPoints(List<Point> points) {
        this.points = points;
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
    public String getPathName() {
        return pathName;
    }

    /**
     *
     * @param pathName
     */
    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    /**
     *
     * @return
     */
    public Double getPathDistance() {
        return pathDistance;
    }

    /**
     *
     * @param pathDistance
     */
    public void setPathDistance(Double pathDistance) {
        this.pathDistance = pathDistance;
    }

    /**
     *
     * @return
     */
    public Integer getRegionId() {
        return regionId;
    }

    /**
     *
     * @param regionId
     */
    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
    
    

    
}
