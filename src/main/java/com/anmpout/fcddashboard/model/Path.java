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

    public String getPathOriginDevId() {
        return pathOriginDevId;
    }

    public void setPathOriginDevId(String pathOriginDevId) {
        this.pathOriginDevId = pathOriginDevId;
    }

    public String getPathDestDevId() {
        return pathDestDevId;
    }

    public void setPathDestDevId(String pathDestDevId) {
        this.pathDestDevId = pathDestDevId;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }




    public Integer getPathId() {
        return pathId;
    }

    public void setPathId(Integer pathId) {
        this.pathId = pathId;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public Double getPathDistance() {
        return pathDistance;
    }

    public void setPathDistance(Double pathDistance) {
        this.pathDistance = pathDistance;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
    
    

    
}
