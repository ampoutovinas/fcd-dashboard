package com.anmpout.fcddashboard.controller;



import com.anmpout.fcddashboard.model.Path;
import com.anmpout.fcddashboard.service.PathService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;


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
    
//      public void onRowSelect(SelectEvent event) {
//        FacesMessage msg = new FacesMessage("Car Selected", ((Path) event.getObject()).getPathId().toString());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
    
    
}
