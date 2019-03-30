/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anmpout.fcddashboard.controller;

import com.anmpout.fcddashboard.model.FilterData;
import com.anmpout.fcddashboard.model.Path;
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
import javax.annotation.PostConstruct;
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
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

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
    private String pathJSONString="";
    private String monthYearSelection;
    private Calendar statisticsDate;
    private Date month;
    private Date day;
    private BarChartModel count;
    private LineChartModel speed;
    private LineChartModel time;
    private static final SimpleDateFormat MONTH_YEAR_FORMAT = new SimpleDateFormat("MM/yyyy");
    private boolean showDailyStatistics = false;
    private boolean showMonthlyStatistics = false;
     private boolean enableSelectDayButton = false;
     List<String> dayLabels; 
     List<Number> daySpeedvalues;
     List<Number> dayTimevalues;
     List<Number> dayCountvalues;
     private List<FilterData> dayData;
     private String detactionDayColor = "rgb(242,109,33)";
     private String speedDayColor = "rgb(75, 192, 192)";
     private String travelTimeDayColor = "rgb(154, 38, 23)";
     //private String travelTimeDayColor ="rgb (154,38,23)";
       
          @PostConstruct
    public void init() {
        showDailyStatistics = false;
        showMonthlyStatistics = false;
        enableSelectDayButton = false;
        dayLabels = new ArrayList<>();
        daySpeedvalues =new ArrayList<>();
        dayTimevalues = new ArrayList<>();
        dayCountvalues = new ArrayList<>();
        pathDistance="";
        region="";
        pathJSONString="";
        statisticsDate = Calendar.getInstance();
        monthYearSelection = MONTH_YEAR_FORMAT.format(statisticsDate.getTime());
        try{
       pathId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        }catch(Exception ex){pathId =1;}
       path = service1.getPath(pathId);
       setupFields();
       pathJSONString = Utils.createPathJSONString(path.getPoints());
         speed = new LineChartModel();
         time = new LineChartModel();
         count = new BarChartModel();
    }

    public boolean isEnableSelectDayButton() {
        return enableSelectDayButton;
    }

    public void setEnableSelectDayButton(boolean enableSelectDayButton) {
        this.enableSelectDayButton = enableSelectDayButton;
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

    public BarChartModel getCount() {
        return count;
    }

    public void setCount(BarChartModel count) {
        this.count = count;
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

    public LineChartModel getSpeed() {
        return speed;
    }

    public void setSpeed(LineChartModel speed) {
        this.speed = speed;
    }

    public LineChartModel getTime() {
        return time;
    }

    public void setTime(LineChartModel time) {
        this.time = time;
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
    
    public void createBarModelDayCount(List<String> labels,List<Number> values) {
    count = new org.primefaces.model.charts.bar.BarChartModel();
    ChartData data = new ChartData();

    org.primefaces.model.charts.bar.BarChartDataSet barDataSet = new org.primefaces.model.charts.bar.BarChartDataSet();
    barDataSet.setLabel("Detactions");

    barDataSet.setData(values);

    List<String> bgColor = new ArrayList<>();
    List<String> borderColor = new ArrayList<>();
    for(int i=0;i<values.size();i++){
    bgColor.add(detactionDayColor);
    borderColor.add(detactionDayColor);
    }
    // 

    

        barDataSet.setBackgroundColor(bgColor);
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

    data.addChartDataSet(barDataSet);
    data.setLabels(labels);
    count.setData(data);

    //Options
    org.primefaces.model.charts.bar.BarChartOptions options = new org.primefaces.model.charts.bar.BarChartOptions();
    CartesianScales cScales = new CartesianScales();
    CartesianLinearAxes linearAxes = new CartesianLinearAxes();
    CartesianLinearTicks ticks = new CartesianLinearTicks();
    ticks.setBeginAtZero(true);
    linearAxes.setTicks(ticks);
    cScales.addYAxesData(linearAxes);
    options.setScales(cScales);

//    Title title = new Title();
//    title.setDisplay(true);
//    title.setText("Bar Chart");
//    options.setTitle(title);

//    Legend legend = new Legend();
//    legend.setDisplay(true);
//    legend.setPosition("top");
//    LegendLabel legendLabels = new LegendLabel();
//    legendLabels.setFontStyle("bold");
//    legendLabels.setFontColor("#2980B9");
//    legendLabels.setFontSize(24);
//    legend.setLabels(legendLabels);
//    options.setLegend(legend);

    count.setOptions(options);
    }

    public void buttonAction() {
        if(day==null){
            PrimeFaces.current().executeScript("hideBar();");  
       Utils.addDetailMessage("Please select a day!",FacesMessage.SEVERITY_ERROR);
        }else{
      //PrimeFaces.current().executeScript("showBar();");
      Long timestampFrom = (Long) day.getTime()/1000;
      Long timestampTo  = (Long) Utils.addDays(day, 1).getTime()/1000;
      dayData = service1.getDayData(100,timestampFrom,timestampTo);
     
      if(dayData.isEmpty()){
      Utils.addDetailMessage("There are not available data for this path and  this time window!",FacesMessage.SEVERITY_WARN);
      return;
      }
      dayLabels = prepareDayLabels(dayData);
      dayCountvalues = prepareDayCountValues(dayData);
      daySpeedvalues =  prepareDaySpeedValues(dayData);
      dayTimevalues = prepareDayTimeValues(dayData);
      createBarModelDayCount(dayLabels,dayCountvalues);
      createLineModelDaySpeed(dayLabels,daySpeedvalues);
      createLineModelDayTime(dayLabels,dayTimevalues);
      PrimeFaces.current().executeScript("hideBar();"); 
      showDailyStatistics = true;
    }
    }
    
    public void onDaySelect(SelectEvent event) {
        enableSelectDayButton = false;

    }
            
   public void createLineModelDaySpeed(List<String> labels,List<Number>values) {
        speed = new LineChartModel();
        ChartData data = new ChartData();
         
        LineChartDataSet dataSet = new LineChartDataSet();
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Speed");
        dataSet.setBorderColor(speedDayColor);
        dataSet.setBackgroundColor(speedDayColor);
        dataSet.setLineTension(0.1);
        data.addChartDataSet(dataSet);
         
        data.setLabels(labels);
         
        //Options
        LineChartOptions options = new LineChartOptions();        
//        Title title = new Title();
//        title.setDisplay(true);
//        title.setText("Line Chart");
//        options.setTitle(title);
         
        speed.setOptions(options);
        speed.setData(data);
       
    }
   
   public void createLineModelDayTime(List<String> labels,List<Number>values) {
        time = new LineChartModel();
        ChartData data = new ChartData();
         
        LineChartDataSet dataSet = new LineChartDataSet();
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Travel Time");
        dataSet.setBorderColor(travelTimeDayColor);
        dataSet.setBackgroundColor(travelTimeDayColor);
        dataSet.setLineTension(0.1);
        data.addChartDataSet(dataSet);
         
        data.setLabels(labels);
         
        //Options
        LineChartOptions options = new LineChartOptions();        
//        Title title = new Title();
//        title.setDisplay(true);
//        title.setText("Line Chart");
//        options.setTitle(title);
         
        time.setOptions(options);
        time.setData(data);
       
    }

    private List<String> prepareDayLabels(List<FilterData> dayData) {
              List<String> labels = new ArrayList<>();
              for(FilterData fd :dayData){
              Date date=new Date(fd.getTimestamp()*1000);
                 DateFormat df2 = new SimpleDateFormat("HH:mm");
                 String label = df2.format(date);
                 labels.add(label);
              
              }
        return labels;
    }

    private List<Number> prepareDayCountValues(List<FilterData> dayData) {
              List<Number> counts = new ArrayList<>();
              for(FilterData fd :dayData){
                 counts.add(fd.getCount());
              
              }
        return counts;

    }

    private List<Number> prepareDaySpeedValues(List<FilterData> dayData) {
              List<Number> speeds = new ArrayList<>();
              for(FilterData fd :dayData){
                 speeds.add(fd.getSpeed());
              
              }
        return speeds; 
    }

    private List<Number> prepareDayTimeValues(List<FilterData> dayData) {
              List<Number> times = new ArrayList<>();
              for(FilterData fd :dayData){
                 times.add(fd.getTime());
              
              }
        return times;

    }
}
