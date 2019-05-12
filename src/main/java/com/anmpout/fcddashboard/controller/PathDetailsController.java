/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anmpout.fcddashboard.controller;

import com.anmpout.fcddashboard.model.FilterData;
import com.anmpout.fcddashboard.model.MonthData;
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
@ManagedBean(name="pathDetailsController")
@ViewScoped
public class PathDetailsController implements Serializable {
    @EJB
    private PathService service1;
    private Path path;
    private Integer pathId;
    private String pathDistance="";
    private String region="";
    private String pathJSONString="";
    private String monthYearSelection;
    private Calendar statisticsDate;
    private Date month;
    private Date day;
    private BarChartModel count;
    private BarChartModel minMax;
    private LineChartModel speed;
    private LineChartModel time;
    private LineChartModel speedMedian;
    private BarChartModel countMonthly;
    private BarChartModel minMaxCountMonthly;
    private LineChartModel speedMonthly;
    private BarChartModel minMaxSpeedMonthly;
    private LineChartModel timeMonthly;
    private BarChartModel minMaxTMonthly;
    private static final SimpleDateFormat MONTH_YEAR_FORMAT = new SimpleDateFormat("MM/yyyy");
    private boolean showDailyStatistics = false;
    private boolean showMonthlyStatistics = false;
     private boolean enableSelectDayButton = false;
     private boolean enableSelectMonthButton = false;
     
     List<String> dayLabels; 
     List<Number> daySpeedValues;
     List<Number> dayTimeValues;
     List<Number> dayCountValues;
     List<Number> dayMedianSpeedValues;
     List<Number> daySpeedMinValues;
     List<Number> daySpeedMaxValues;
     List<String> monthLabels; 
     List<Number> monthSpeedValues;
     List<Number> monthSpeedMinValues;
     List<Number> monthSpeedMaxValues;
     List<Number> monthTimeValues;
     List<Number> monthTimeMinValues;
     List<Number> monthTimeMaxValues;
     List<Number> monthCountValues;
     List<Number> monthCountMinValues;
     List<Number> monthCountMaxValues;

     private List<FilterData> dayData;
     private List<MonthData> monthData;
     private String detactionDayColor = "rgb(242,109,33)";
     private String speedDayColor = "rgb(75, 192, 192)";
     private String travelTimeDayColor = "rgb(182, 29, 69)";
     private String speedMedianDayColor = "rgb(210, 216, 63)";
     
     //private String travelTimeDayColor ="rgb (154,38,23)";
       
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
        dayLabels = new ArrayList<>();
        daySpeedValues =new ArrayList<>();
        dayTimeValues = new ArrayList<>();
        dayCountValues = new ArrayList<>();
        dayMedianSpeedValues = new ArrayList<>();
        daySpeedMinValues = new ArrayList<>();
        daySpeedMaxValues = new ArrayList<>();
        monthLabels = new ArrayList<>();
        monthSpeedValues = new ArrayList<>();
        monthSpeedMinValues = new ArrayList<>();
        monthSpeedMaxValues = new ArrayList<>();
        monthTimeValues = new ArrayList<>();
        monthTimeMinValues = new ArrayList<>();
        monthTimeMaxValues = new ArrayList<>();
        monthCountValues = new ArrayList<>();
        monthCountMinValues = new ArrayList<>();
        monthCountMaxValues = new ArrayList<>();
        monthData = new ArrayList<>();
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
       setupFields();
       pathJSONString = Utils.createPathJSONString(path.getPoints());
         speed = new LineChartModel();
         time = new LineChartModel();
         count = new BarChartModel();
         minMax = new BarChartModel();
         speedMedian = new LineChartModel();
        countMonthly =  new BarChartModel();
        minMaxCountMonthly =  new BarChartModel();
        speedMonthly= new LineChartModel();
        timeMonthly= new LineChartModel();
        minMaxSpeedMonthly = new BarChartModel();
        minMaxTMonthly = new BarChartModel();
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

    public BarChartModel getCountMonthly() {
        return countMonthly;
    }

    public void setCountMonthly(BarChartModel countMonthly) {
        this.countMonthly = countMonthly;
    }


    public LineChartModel getSpeedMonthly() {
        return speedMonthly;
    }

    public void setSpeedMonthly(LineChartModel speedMonthly) {
        this.speedMonthly = speedMonthly;
    }

    public LineChartModel getTimeMonthly() {
        return timeMonthly;
    }

    public void setTimeMonthly(LineChartModel timeMonthly) {
        this.timeMonthly = timeMonthly;
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
    
    
    public LineChartModel getSpeedMedian() {
        return speedMedian;
    }

    public void setSpeedMedian(LineChartModel speedMedian) {
        this.speedMedian = speedMedian;
    }

    public BarChartModel getMinMax() {
        return minMax;
    }

    public void setMinMax(BarChartModel minMax) {
        this.minMax = minMax;
    }

    public BarChartModel getMinMaxCountMonthly() {
        return minMaxCountMonthly;
    }

    public void setMinMaxCountMonthly(BarChartModel minMaxCountMonthly) {
        this.minMaxCountMonthly = minMaxCountMonthly;
    }

    public BarChartModel getMinMaxSpeedMonthly() {
        return minMaxSpeedMonthly;
    }

    public void setMinMaxSpeedMonthly(BarChartModel minMaxSpeedMonthly) {
        this.minMaxSpeedMonthly = minMaxSpeedMonthly;
    }

    public BarChartModel getMinMaxTMonthly() {
        return minMaxTMonthly;
    }

    public void setMinMaxTMonthly(BarChartModel minMaxTMonthly) {
        this.minMaxTMonthly = minMaxTMonthly;
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
                         FacesContext context = FacesContext.getCurrentInstance();
                   
                    pathId = (Integer) context.getExternalContext().getSessionMap().get("pathId"); 
      dayData = service1.getDayData(pathId,timestampFrom,timestampTo);
     
      if(dayData.isEmpty()){
      PrimeFaces.current().executeScript("hideBar();"); 
      Utils.addDetailMessage("There are not available data for this path and  this time window!",FacesMessage.SEVERITY_WARN);
      return;
      }
      dayLabels = prepareDayLabels(dayData);
      dayCountValues = prepareDayCountValues(dayData);
      daySpeedValues =  prepareDaySpeedValues(dayData);
      dayTimeValues = prepareDayTimeValues(dayData);
      dayMedianSpeedValues = prepareDaySpeedMedianValues(dayData);
       daySpeedMinValues = prepareDaySpeedMinValues(dayData);
       daySpeedMaxValues = prepareDaySpeedMaxValues(dayData);
      createBarModelDayCount(dayLabels,dayCountValues);
      createLineModelDaySpeed(dayLabels,daySpeedValues);
      createLineModelDayTime(dayLabels,dayTimeValues);
      createLineModelDayMedianSpeed(dayLabels,dayMedianSpeedValues);
      createminMaxBarModel(dayLabels,daySpeedMinValues,daySpeedMaxValues);
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

    private List<Number> prepareDaySpeedMedianValues(List<FilterData> dayData) {
              List<Number> medianSpeedValues = new ArrayList<>();
              for(FilterData fd :dayData){
                 medianSpeedValues.add(fd.getMedianSpeed());
              
              }
        return medianSpeedValues; 
    }
    
    private List<Number> prepareDaySpeedMinValues(List<FilterData> dayData) {
              List<Number> minSpeeds = new ArrayList<>();
              for(FilterData fd :dayData){
                 minSpeeds.add(fd.getMinSpeed());
              
              }
        return minSpeeds;
    
        }

    private List<Number> prepareDaySpeedMaxValues(List<FilterData> dayData) {
              List<Number> maxSpeeds = new ArrayList<>();
              for(FilterData fd :dayData){
                 maxSpeeds.add(fd.getMaxSpeed());
              
              }
        return maxSpeeds;
    }

    private void createLineModelDayMedianSpeed(List<String> labels, List<Number> values) {
        speedMedian = new LineChartModel();
        ChartData data = new ChartData();
         
        LineChartDataSet dataSet = new LineChartDataSet();
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Median");
        dataSet.setBorderColor(speedMedianDayColor);
        dataSet.setBackgroundColor(speedMedianDayColor);
        dataSet.setLineTension(0.1);
        data.addChartDataSet(dataSet);
         
        data.setLabels(labels);
         
        //Options
        LineChartOptions options = new LineChartOptions();        
//        Title title = new Title();
//        title.setDisplay(true);
//        title.setText("Line Chart");
//        options.setTitle(title);
         
        speedMedian.setOptions(options);
        speedMedian.setData(data); 
    }
    
    public void createminMaxBarModel(List<String> labels, List<Number> valuesMin, List<Number> valuesMax) {
        minMax = new BarChartModel();
        ChartData data = new ChartData();
         
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Min Speed");
        barDataSet.setBackgroundColor("rgb(211, 22, 32)");
        barDataSet.setStack("Stack 0");
        barDataSet.setData(valuesMin);
         
         
        BarChartDataSet barDataSet3 = new BarChartDataSet();
        barDataSet3.setLabel("Max Speed");
        barDataSet3.setBackgroundColor("rgb(19, 136, 8)");
        barDataSet3.setStack("Stack 1");
        List<Number> dataVal3 = new ArrayList<>();
        dataVal3.add(-45);
        dataVal3.add(73);
        dataVal3.add(-25);
        dataVal3.add(65);
        dataVal3.add(49);
        dataVal3.add(-18);
        dataVal3.add(46);
        barDataSet3.setData(valuesMax);
         
        data.addChartDataSet(barDataSet);
        data.addChartDataSet(barDataSet3);
         

        data.setLabels(labels);
        minMax.setData(data);
         
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setStacked(true);    
        cScales.addXAxesData(linearAxes);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
         
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Minimum & Maximum Speed");
        options.setTitle(title);
         
//        Tooltip tooltip = new Tooltip();
//        tooltip.setMode("index");
//        tooltip.setIntersect(false);
//        options.setTooltip(tooltip);  
         
        minMax.setOptions(options);
    }
        
    public void buttonActionMonth(){
      if(month==null){
            PrimeFaces.current().executeScript("hideBar();");  
       Utils.addDetailMessage("Please select a month!",FacesMessage.SEVERITY_ERROR);
               
        }else{
        FacesContext context = FacesContext.getCurrentInstance();
        pathId = (Integer) context.getExternalContext().getSessionMap().get("pathId"); 
      //PrimeFaces.current().executeScript("showBar();");
        Calendar cal = Calendar.getInstance();
        cal.setTime(month);
        Integer monthPart = cal.get(Calendar.MONTH);
        Integer yearPart = cal.get(Calendar.YEAR);
     // Integer year  = (Integer) Utils.addDays(day, 1).getTime()/1000;
      monthData = service1.getMonthData(pathId,monthPart+1,yearPart);
     
      if(monthData.isEmpty()){
      PrimeFaces.current().executeScript("hideBar();");  
      Utils.addDetailMessage("There are not available data for this path and  this time window!",FacesMessage.SEVERITY_WARN);
      return;
      }
      monthLabels = prepareMonthLabels(monthData);
      monthCountValues = prepareMonthCountValues(monthData);
      monthCountMinValues = prepareMonthMinMaxCountValues(monthData,1);
      monthCountMaxValues = prepareMonthMinMaxCountValues(monthData,2);
      monthSpeedValues =  prepareMonthSpeedValues(monthData);
      monthSpeedMinValues = prepareMonthSpeedMinMaxValues(monthData,1);
      monthSpeedMaxValues = prepareMonthSpeedMinMaxValues(monthData,2);
      monthTimeValues = prepareMonthTimeValues(monthData);
      monthTimeMinValues = prepareMonthTimeMinMaxValues(monthData,1);
      monthTimeMaxValues = prepareMonthTimeMinMaxValues(monthData,2);

         createBarModelMonthCount(monthLabels,monthCountValues);
         createMinMaxCountBarModel(monthLabels,monthCountMinValues,monthCountMaxValues);
        createLineModelMonthSpeed(monthLabels,monthSpeedValues);
        createMinMaxBarSpeedModel(monthLabels,monthSpeedMinValues,monthSpeedMaxValues);
        createLineModelMonthTime(monthLabels,monthTimeValues);
        createMinMaxBarTimeModel(monthLabels,monthTimeMinValues,monthTimeMaxValues);
      PrimeFaces.current().executeScript("hideBar();"); 
      showMonthlyStatistics = true;
    }
       }

    private List<String> prepareMonthLabels(List<MonthData> monthData) {
                  List<String> labels = new ArrayList<>();
                  monthData.stream().map((md) -> md.getDay()+"/"+md.getMonth()).forEachOrdered((label) -> {
                      labels.add(label);
        });
        return labels;
    }

    private List<Number> prepareMonthCountValues(List<MonthData> monthData) {
        List<Number> counts = new ArrayList<>();
        monthData.forEach((md) -> {
            counts.add(md.getCount());
        });
        return counts;
    }

    private List<Number> prepareMonthMinMaxCountValues(List<MonthData> monthData, int minMax) {
              List<Number> minMaxList = new ArrayList<>();
        for(MonthData md :monthData){
        if(minMax==1){  
        minMaxList.add(md.getMinCount());
        }else{
        minMaxList.add(md.getMaxCount());
        }
        }
        return minMaxList;
    }

    private List<Number> prepareMonthSpeedValues(List<MonthData> monthData) {
        List<Number> speed = new ArrayList<>();
        monthData.forEach((md) -> {
            speed.add(md.getSpeed());
        });
        return speed;   
    }
    
    private List<Number> prepareMonthSpeedMinMaxValues(List<MonthData> monthData, int minMax) {
              List<Number> minMaxList = new ArrayList<>();
        for(MonthData md :monthData){
        if(minMax==1){  
        minMaxList.add(md.getMinSpeed());
        }else{
        minMaxList.add(md.getMaxSpeed());
        }
        }
        return minMaxList;
    }

    private List<Number> prepareMonthTimeValues(List<MonthData> monthData) {
            List<Number> time = new ArrayList<>();
        for(MonthData md :monthData){
        time.add(md.getTime());
        }
        return time;   
    }

        private List<Number> prepareMonthTimeMinMaxValues(List<MonthData> monthData, int minMax) {
              List<Number> minMaxList = new ArrayList<>();
              monthData.forEach((md) -> {
                  if(minMax==1){
                      minMaxList.add(md.getMinTime());
                  }else{
                      minMaxList.add(md.getMaxTime());
                  }
        });
        return minMaxList;
    }

    private void createBarModelMonthCount(List<String> labels, List<Number> values) {
    countMonthly = new org.primefaces.model.charts.bar.BarChartModel();
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
    countMonthly.setData(data);

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

    countMonthly.setOptions(options);

    }

    private void createMinMaxCountBarModel(List<String> labels, List<Number> valuesMin, List<Number> valuesMax) {
        minMaxCountMonthly = new BarChartModel();
        ChartData data = new ChartData();
         
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Min Detactions");
        barDataSet.setBackgroundColor("rgb(211, 22, 32)");
        barDataSet.setStack("Stack 0");
        barDataSet.setData(valuesMin);
         
         
        BarChartDataSet barDataSet3 = new BarChartDataSet();
        barDataSet3.setLabel("Max Detactions");
        barDataSet3.setBackgroundColor("rgb(19, 136, 8)");
        barDataSet3.setStack("Stack 1");
        List<Number> dataVal3 = new ArrayList<>();
        dataVal3.add(-45);
        dataVal3.add(73);
        dataVal3.add(-25);
        dataVal3.add(65);
        dataVal3.add(49);
        dataVal3.add(-18);
        dataVal3.add(46);
        barDataSet3.setData(valuesMax);
         
        data.addChartDataSet(barDataSet);
        data.addChartDataSet(barDataSet3);
         

        data.setLabels(labels);
        minMaxCountMonthly.setData(data);
         
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setStacked(true);    
        cScales.addXAxesData(linearAxes);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
         
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Minimum & Maximum Detactions");
        options.setTitle(title);
         
//        Tooltip tooltip = new Tooltip();
//        tooltip.setMode("index");
//        tooltip.setIntersect(false);
//        options.setTooltip(tooltip);  
         
        minMaxCountMonthly.setOptions(options);    }

    private void createLineModelMonthSpeed(List<String> labels, List<Number> values) {
        speedMonthly = new LineChartModel();
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
         
        speedMonthly.setOptions(options);
        speedMonthly.setData(data);
           }

    private void createMinMaxBarSpeedModel(List<String> labels, List<Number> valuesMin, List<Number> valuesMax) {
 minMaxSpeedMonthly = new BarChartModel();
        ChartData data = new ChartData();
         
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Min Speed");
        barDataSet.setBackgroundColor("rgb(211, 22, 32)");
        barDataSet.setStack("Stack 0");
        barDataSet.setData(valuesMin);
         
         
        BarChartDataSet barDataSet3 = new BarChartDataSet();
        barDataSet3.setLabel("Max Speed");
        barDataSet3.setBackgroundColor("rgb(19, 136, 8)");
        barDataSet3.setStack("Stack 1");
        List<Number> dataVal3 = new ArrayList<>();
        dataVal3.add(-45);
        dataVal3.add(73);
        dataVal3.add(-25);
        dataVal3.add(65);
        dataVal3.add(49);
        dataVal3.add(-18);
        dataVal3.add(46);
        barDataSet3.setData(valuesMax);
         
        data.addChartDataSet(barDataSet);
        data.addChartDataSet(barDataSet3);
         

        data.setLabels(labels);
        minMaxSpeedMonthly.setData(data);
         
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setStacked(true);    
        cScales.addXAxesData(linearAxes);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
         
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Minimum & Maximum Speed");
        options.setTitle(title);
         
//        Tooltip tooltip = new Tooltip();
//        tooltip.setMode("index");
//        tooltip.setIntersect(false);
//        options.setTooltip(tooltip);  
         
        minMaxSpeedMonthly.setOptions(options); 

    }

    private void createLineModelMonthTime(List<String> labels, List<Number> values) {
  timeMonthly = new LineChartModel();
        ChartData data = new ChartData();
         
        LineChartDataSet dataSet = new LineChartDataSet();
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Travel time");
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
         
        timeMonthly.setOptions(options);
        timeMonthly.setData(data); 
    }

    private void createMinMaxBarTimeModel(List<String> labels, List<Number> valuesMin, List<Number> valuesMax) {
minMaxTMonthly = new BarChartModel();
        ChartData data = new ChartData();
         
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Min Travel Time");
        barDataSet.setBackgroundColor("rgb(211, 22, 32)");
        barDataSet.setStack("Stack 0");
        barDataSet.setData(valuesMin);
         
         
        BarChartDataSet barDataSet3 = new BarChartDataSet();
        barDataSet3.setLabel("Max Travel Time");
        barDataSet3.setBackgroundColor("rgb(19, 136, 8)");
        barDataSet3.setStack("Stack 1");
        List<Number> dataVal3 = new ArrayList<>();
        dataVal3.add(-45);
        dataVal3.add(73);
        dataVal3.add(-25);
        dataVal3.add(65);
        dataVal3.add(49);
        dataVal3.add(-18);
        dataVal3.add(46);
        barDataSet3.setData(valuesMax);
         
        data.addChartDataSet(barDataSet);
        data.addChartDataSet(barDataSet3);
         

        data.setLabels(labels);
        minMaxTMonthly.setData(data);
         
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setStacked(true);    
        cScales.addXAxesData(linearAxes);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
         
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Minimum & Maximum Travel Time");
        options.setTitle(title);
         
//        Tooltip tooltip = new Tooltip();
//        tooltip.setMode("index");
//        tooltip.setIntersect(false);
//        options.setTooltip(tooltip);  
         
        minMaxTMonthly.setOptions(options); 
    
    }


}
