package com.anmpout.fcddashboard.controller;



import com.anmpout.fcddashboard.model.Path;
import com.anmpout.fcddashboard.service.PathService;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;


@ManagedBean(name="pathController")
@ViewScoped
public class PathController implements Serializable {
    @EJB
    private PathService service;
private List<Path> paths;

      @PostConstruct
    public void init() {
        paths = service.getAllRegionPaths(1);
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
            FacesContext.getCurrentInstance().getExternalContext().redirect("/FCDDashboard/path-analytics.xhtml?id="+((Path)event.getObject()).getPathId().toString());
           // FacesContext.getCurrentInstance().getExternalContext().redirect("/FCDDashboard/path-analytics.xhtml?id=100");
        } catch (IOException ex) {
            Logger.getLogger(PathController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
}
