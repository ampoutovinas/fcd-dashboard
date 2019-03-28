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
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
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
       
          @PostConstruct
    public void init() {
        showDailyStatistics = false;
        showMonthlyStatistics = false;
        enableSelectDayButton = false;
        pathId=1;
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
         createBarModel2();
        // createLineModel();
         speed = new LineChartModel();
         time = new LineChartModel();
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
    
    public void createBarModel2() {
    count = new org.primefaces.model.charts.bar.BarChartModel();
    ChartData data = new ChartData();

    org.primefaces.model.charts.bar.BarChartDataSet barDataSet = new org.primefaces.model.charts.bar.BarChartDataSet();
    barDataSet.setLabel("My First Dataset");

    List<Number> values = new ArrayList<>();
    values.add(65);
    values.add(59);
    values.add(80);
    values.add(81);
    values.add(56);
    values.add(55);
    values.add(40);
    values.add(65);
    values.add(59);
    values.add(80);
    values.add(81);
    values.add(56);
    values.add(55);
    values.add(40);
    values.add(65);
    values.add(59);
    values.add(80);
    values.add(81);
    values.add(56);
    values.add(55);
    values.add(40);
    values.add(56);
    values.add(55);
    values.add(40);
    barDataSet.setData(values);

    List<String> bgColor = new ArrayList<>();
    bgColor.add("rgb(255, 99, 132, 0.8)");
    // barDataSet.setBackgroundColor(bgColor);

    List<String> borderColor = new ArrayList<>();
    borderColor.add("rgb(255, 99, 132)");
    // barDataSet.setBorderColor(borderColor);
    barDataSet.setBorderWidth(1);

    data.addChartDataSet(barDataSet);

    List<String> labels = new ArrayList<>();
    labels.add("00");
    labels.add("01");
    labels.add("02");
    labels.add("03");
    labels.add("04");
    labels.add("05");
    labels.add("06");
    labels.add("07");
    labels.add("08");
    labels.add("09");
    labels.add("10");
    labels.add("11");
    labels.add("12");
    labels.add("13");
    labels.add("14");
    labels.add("15");
    labels.add("16");
    labels.add("17");
    labels.add("18");
    labels.add("19");
    labels.add("20");
    labels.add("21");
    labels.add("22");
    labels.add("23");
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

    Title title = new Title();
    title.setDisplay(true);
    title.setText("Bar Chart");
    options.setTitle(title);

    Legend legend = new Legend();
    legend.setDisplay(true);
    legend.setPosition("top");
    LegendLabel legendLabels = new LegendLabel();
    legendLabels.setFontStyle("bold");
    legendLabels.setFontColor("#2980B9");
    legendLabels.setFontSize(24);
    legend.setLabels(legendLabels);
    options.setLegend(legend);

    count.setOptions(options);
    }

    public void buttonAction() {
      showDailyStatistics = true;
    }
    public void onDaySelect(SelectEvent event) {
        enableSelectDayButton = false;

    }
            
        public void createLineModel() {
        speed = new LineChartModel();
         time = new LineChartModel();
        ChartData data = new ChartData();
         
        LineChartDataSet dataSet = new LineChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(65);
        values.add(59);
        values.add(80);
        values.add(81);
        values.add(56);
        values.add(55);
        values.add(40);
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("My First Dataset");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setLineTension(0.1);
        data.addChartDataSet(dataSet);
         
        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);
         
        //Options
        LineChartOptions options = new LineChartOptions();        
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Line Chart");
        options.setTitle(title);
         
        speed.setOptions(options);
        speed.setData(data);
        time.setOptions(options);
        time.setData(data);
       
    }
}
