/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anmpout.fcddashboard.controller;

import com.anmpout.fcddashboard.model.FilterData;
import com.anmpout.fcddashboard.model.FilterDataRT;
import com.anmpout.fcddashboard.model.MonthData;
import com.anmpout.fcddashboard.model.Path;
import com.anmpout.fcddashboard.model.ProfileData;
import com.anmpout.fcddashboard.service.PathService;
import com.anmpout.fcddashboard.utils.Utils;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Tooltip;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

/**
 *
 * @author anmpout
 */
@ManagedBean(name="pathRTDetailsController")
@ViewScoped
public class PathRTDetailsController implements Serializable {
    @EJB
    private PathService service1;
    private Path path;
    private FilterDataRT dataRT;
    private List<ProfileData> profileData;
    private String currentDate;
    private Integer pathId;
    private String pathDistance="";
    private String region="";
    private String pathJSONString="";
    private String monthYearSelection;
    private Calendar statisticsDate;
    private Date month;
    private Date day;
    private static final SimpleDateFormat MONTH_YEAR_FORMAT = new SimpleDateFormat("MM/yyyy");
    private boolean showDailyStatistics = false;
    private boolean showMonthlyStatistics = false;
     private boolean enableSelectDayButton = false;
     private boolean enableSelectMonthButton = false;
     
     
       
          @PostConstruct
    public void init() {
        if (FacesContext.getCurrentInstance().isPostback()) {
            
            System.out.println("test");
                  // FacesContext context = FacesContext.getCurrentInstance();
                   
                   // pathId = Integer.valueOf((String) context.getExternalContext().getSessionMap().get("pathId")); 
          
             //path = service1.getPath(pathId);
        //setupFields();
       //pathJSONString = Utils.createPathJSONString(path.getPoints());
        }else{
        showDailyStatistics = false;
        showMonthlyStatistics = false;
        enableSelectDayButton = false;
        enableSelectMonthButton = false;
        pathDistance="";
        region="";
        pathJSONString="";
        statisticsDate = Calendar.getInstance();
        monthYearSelection = MONTH_YEAR_FORMAT.format(statisticsDate.getTime());
      
        // charge combos....
   
        try{
           String value =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
    
       pathId = Integer.parseInt(value);
       FacesContext context = FacesContext.getCurrentInstance();
context.getExternalContext().getSessionMap().put("pathId", pathId);
  
   
        }catch(Exception ex){
            pathId =1;
        }
      
       path = service1.getPath(pathId);
       dataRT = service1.getRTData(pathId,getCurrentTimestamp());
       profileData = service1.getProfileData(pathId,getCurrentTimestamp());
       setupFields();
       pathJSONString = Utils.createPathJSONString(path.getPoints());
        }
    }
        @PreDestroy
        public void saveState() {
        
        
        }
    public boolean isEnableSelectDayButton() {
        return enableSelectDayButton;
    }

    public void setEnableSelectDayButton(boolean enableSelectDayButton) {
        this.enableSelectDayButton = enableSelectDayButton;
    }

    public boolean isEnableSelectMonthButton() {
        return enableSelectMonthButton;
    }

    public void setEnableSelectMonthButton(boolean enableSelectMonthButton) {
        this.enableSelectMonthButton = enableSelectMonthButton;
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

    public String getPathJSONString() {
        return pathJSONString;
    }

    public void setPathJSONString(String pathJSONString) {
        this.pathJSONString = pathJSONString;
    }

    public String getMonthYearSelection() {
        return monthYearSelection;
    }

    public void setMonthYearSelection(String monthYearSelection) {
        this.monthYearSelection = monthYearSelection;
    }

    public Calendar getStatisticsDate() {
        return statisticsDate;
    }

    public void setStatisticsDate(Calendar statisticsDate) {
        this.statisticsDate = statisticsDate;
    }

  
        public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    private void setupFields() {
        if(path.getRegionId()==1){
        region="Tessaloniki";
        }else{
        region="";
        }
        pathDistance = Double.toString(Utils.convertMetersToKm(path.getPathDistance()))+" Km";
    }

   
    public Integer getInitialStatisticsYear() {
        List<Integer> yearsWithStatistics = new ArrayList<>();     
        if(yearsWithStatistics == null || yearsWithStatistics.isEmpty()) {
            return Calendar.getInstance().get(Calendar.YEAR);
        }
        return yearsWithStatistics.get(0);
}
    
     public void updateStatistics() throws ParseException {

}   

    public boolean isShowDailyStatistics() {
        return showDailyStatistics;
    }

    public void setShowDailyStatistics(boolean showDailyStatistics) {
        this.showDailyStatistics = showDailyStatistics;
    }

    public boolean isShowMonthlyStatistics() {
        return showMonthlyStatistics;
    }

    public void setShowMonthlyStatistics(boolean showMonthlyStatistics) {
        this.showMonthlyStatistics = showMonthlyStatistics;
    }
    
    
    public FilterDataRT getDataRT() {
        return dataRT;
    }

    public void setDataRT(FilterDataRT dataRT) {
        this.dataRT = dataRT;
    }

    public List<ProfileData> getProfileData() {
        return profileData;
    }

    public void setProfileData(List<ProfileData> profileData) {
        this.profileData = profileData;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }
    
    
    
    public Long getCurrentTimestamp() {
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
        return timestamp;
}
    


    public void buttonAction() {
//        if(day==null){
//            PrimeFaces.current().executeScript("hideBar();");  
//       Utils.addDetailMessage("Please select a day!",FacesMessage.SEVERITY_ERROR);
//        }else{
//      //PrimeFaces.current().executeScript("showBar();");
//      Long timestampFrom = (Long) day.getTime()/1000;
//      Long timestampTo  = (Long) Utils.addDays(day, 1).getTime()/1000;
//                         FacesContext context = FacesContext.getCurrentInstance();
//                   
//                    pathId = (Integer) context.getExternalContext().getSessionMap().get("pathId"); 
//     // dayData = service1.getDayData(pathId,timestampFrom,timestampTo);
//     
//      if(dayData.isEmpty()){
//      PrimeFaces.current().executeScript("hideBar();"); 
//      Utils.addDetailMessage("There are not available data for this path and  this time window!",FacesMessage.SEVERITY_WARN);
//      return;
//      }
//
//      PrimeFaces.current().executeScript("hideBar();"); 
//      showDailyStatistics = true;
//    }
    }
    
    public void onDaySelect(SelectEvent event) {
        enableSelectDayButton = false;

    }
            
       
    public void buttonActionMonth(){
//      if(month==null){
//            PrimeFaces.current().executeScript("hideBar();");  
//       Utils.addDetailMessage("Please select a month!",FacesMessage.SEVERITY_ERROR);
//               
//        }else{
//        FacesContext context = FacesContext.getCurrentInstance();
//        pathId = (Integer) context.getExternalContext().getSessionMap().get("pathId"); 
//      //PrimeFaces.current().executeScript("showBar();");
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(month);
//        Integer monthPart = cal.get(Calendar.MONTH);
//        Integer yearPart = cal.get(Calendar.YEAR);
//     // Integer year  = (Integer) Utils.addDays(day, 1).getTime()/1000;
//      monthData = service1.getMonthData(pathId,monthPart+1,yearPart);
//     
//      if(monthData.isEmpty()){
//      PrimeFaces.current().executeScript("hideBar();");  
//      Utils.addDetailMessage("There are not available data for this path and  this time window!",FacesMessage.SEVERITY_WARN);
//      return;
//      }
//
//      PrimeFaces.current().executeScript("hideBar();"); 
//      showMonthlyStatistics = true;
//    }
       }




}
