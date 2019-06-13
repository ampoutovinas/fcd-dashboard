/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anmpout.fcddashboard.controller;

import com.anmpout.fcddashboard.model.Path;
import com.anmpout.fcddashboard.service.PathService;
import com.anmpout.fcddashboard.utils.Utils;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author anmpout
 */
@ManagedBean(name="rtpathController")
@ViewScoped
public class RtPathController  implements Serializable {
      @EJB
private PathService service;
private List<Path> paths;
private String currentDate;

      @PostConstruct
    public void init() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Calendar cal = Calendar.getInstance();
                cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR)-1);
	 //2016/11/16 12:08:43
        currentDate = dateFormat.format(cal.getTime());
        System.out.println(currentDate);
         Long timestamp = (Long) cal.getTimeInMillis()/1000;
        timestamp = Long.parseLong("1559192400");
//      Long timestampTo  = (Long) Utils.addDays(day, 1).getTime()/1000;
        paths = service.getAllPathsForRT(timestamp);
      //  paths = new ArrayList<>();
      //  paths.add(new Path());
   
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }
    
      public void onRowSelect(SelectEvent event) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/FCDDashboard/path-analytics_rt.xhtml?id="+((Path)event.getObject()).getPathId().toString());
           // FacesContext.getCurrentInstance().getExternalContext().redirect("/FCDDashboard/path-analytics.xhtml?id=100");
        } catch (IOException ex) {
            Logger.getLogger(PathController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }  

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }
      
      
}
