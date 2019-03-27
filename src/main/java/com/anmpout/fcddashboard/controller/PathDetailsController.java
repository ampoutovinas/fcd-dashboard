/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anmpout.fcddashboard.controller;

import com.anmpout.fcddashboard.model.Path;
import com.anmpout.fcddashboard.service.PathService;
import com.anmpout.fcddashboard.utils.Utils;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author anmpout
 */
@ManagedBean(name="pathDetailsController")
@ViewScoped
public class PathDetailsController implements Serializable {
    @EJB
    private PathService service1;
    private Path path;
    private int pathId=0;
    private String pathDistance="";
    private String region="";
   // private String pathJSONString;
    
          @PostConstruct
    public void init() {
        pathId=1;
        pathDistance="";
        region="";
       pathId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
       path = service1.getPath(pathId);
       setupFields();
     //  pathJSONString = Utils.createPathJSONString();
       
    }



    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public int getPathId() {
        return pathId;
    }

    public void setPathId(int pathId) {
        this.pathId = pathId;
    }

    public String getPathDistance() {
        return pathDistance;
    }

    public void setPathDistance(String pathDistance) {
        this.pathDistance = pathDistance;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    private void setupFields() {
        if(path.getRegionId()==1){
        region="Tessaloniki";
        }else{
        region="";
        }
        pathDistance = Double.toString(Utils.convertMetersToKm(path.getPathDistance()))+" Km";
    }


    
    
    
    
}
